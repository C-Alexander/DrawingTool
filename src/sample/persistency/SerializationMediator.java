package sample.persistency;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import sample.DomainClasses.Drawing;

import java.io.*;
import java.util.Properties;

/**
 * Created by jasve_29 on 13-Mar-17.
 */
public class SerializationMediator  implements PersistencyMediator {
    Properties props;
    Kryo kryo;
    File f;

    public SerializationMediator() {
        kryo = new Kryo();
        //should use kryo.register on everything for efficiency but #lazy
//
//        kryo.register(File.class, new Serializer<File>() {
//            @Override
//            public void write(Kryo kryo, Output output, File object) {
//                output.writeString(object.getPath());
//            }
//
//            @Override
//            public File read(Kryo kryo, Input input, Class type) {
//                return new File(input.readString());
//            }
//        });
    }

    @Override
    public Drawing load(String nameDrawing) {
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            Log.error("De-Serialization", e);
            return null;
        }
        Input i = new Input(is);
        Drawing d = (Drawing)kryo.readObject(i, Drawing.class);
        i.close();
        return d;
    }


    @Override
    public Boolean save(Drawing drawing) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            Log.error("Serialization", e);
            return false;
        }
        Output o = new Output(os);
        kryo.writeObject(o, drawing);
        o.close();
        return true;
    }

    @Override
    public Boolean init(Properties props) {
        this.props = props;
        f = new File(props.getProperty("file"));
        return true;
    }
}
