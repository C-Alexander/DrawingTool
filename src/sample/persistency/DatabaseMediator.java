package sample.persistency;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import sample.DomainClasses.Drawing;
import sample.DomainClasses.DrawingItem;
import sample.DomainClasses.Image;
import sample.DomainClasses.Polygon;

import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by jasve_29 on 13-Mar-17.
 */
public class DatabaseMediator implements PersistencyMediator {
    Properties props; //pls...
    Connection con; //this should be two n's at least..
    Kryo kryo = new Kryo();

    @Override

    public Drawing load(String nameDrawing) {

        try {
            initConnection();
            PreparedStatement getExists = con.prepareStatement("SELECT drawing FROM drawing WHERE drawing.name = ?");
            getExists.setString(1, nameDrawing);
            getExists.execute();
            ResultSet r = getExists.getResultSet();
            r.next();
            Blob blub = r.getBlob("drawing");
            return (Drawing)kryo.readObject(new Input(blub.getBinaryStream()), Drawing.class);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Boolean save(Drawing drawing) {
        try {
            initConnection();
            PreparedStatement getExists = con.prepareStatement("SELECT COUNT(1) as count FROM drawing WHERE drawing.name = ?");
            getExists.setString(1, drawing.getName());
            getExists.execute();
            ResultSet r = getExists.getResultSet();
            r.next();
            Blob blub = getBlob(drawing);
            if (r.getInt("count") > 0) {
                PreparedStatement updateRow = con.prepareStatement("UPDATE drawing SET drawing.drawing = ? WHERE drawing.name = ?");
                updateRow.setObject(1, blub);
                updateRow.setObject(2, drawing.getName());
                updateRow.executeUpdate();
            } else {
                PreparedStatement updateRow = con.prepareStatement("INSERT INTO drawing (name, drawing) VALUES (?, ?)");
                updateRow.setObject(1, drawing.getName());
                updateRow.setObject(2, blub);
                updateRow.executeUpdate();
            }

        } catch (SQLException e) {
            Log.error("MySQL", e);
            Log.error("MySQL", e.getMessage());
            return false;
        } finally {
            closeConnection();
        }
        closeConnection();
        return true;
    }

    @Override
    public Boolean init(Properties props) {
        this.props = props;
        try {
            initConnection();
        } catch (SQLException e) {
            Log.error("MySQL", "Can not make DB Connection: " + e);
        return false;
        }
        return true;
    }

    public void closeConnection() {
//        try {
//            con.rollback();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public Blob getBlob(Drawing drawing) {
        OutputStream os = null;
        os = new ByteOutputStream();
        Output o = new Output(os);
        List<DrawingItem> list = drawing.getItems().stream().filter(p -> p.getClass() == Image.class || p.getClass() == Polygon.class).collect(Collectors.toList()); //sure I can do a foreach loop but that aint fun.
        drawing.setList(list);
        kryo.writeObject(o, drawing);
        Blob blub = null;
        try {
            blub = con.createBlob();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            blub.setBytes(1, o.toBytes());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        o.close();
        return blub;

    }

    public void initConnection() throws SQLException {
        this.con = DriverManager.getConnection(props.getProperty("ConnectionString"), props.getProperty("userName"), props.getProperty("passWord"));

    }
}
