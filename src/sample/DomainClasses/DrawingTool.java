package sample.DomainClasses;

import javafx.scene.canvas.Canvas;
import sample.DomainClasses.drawing.javafx.JavaFXPaintable;
import sample.PropertiesTool;
import sample.persistency.DatabaseMediator;
import sample.persistency.PersistencyMediator;
import sample.persistency.SerializationMediator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by jasve_29 on 07-Mar-17.
 */
public class DrawingTool {
    private Canvas canvas;
    private Drawing drawing;

    public DrawingTool(Canvas canvas) {

        this.canvas = canvas;
        drawing = new Drawing();
    }

    public void draw() {
//        Oval oval = new Oval();
//        oval.setColor(Color.GREEN);
//        oval.setAnchor(new Point(10, 20));
//        oval.setWeight(10);
//        oval.setHeight(20);
//        oval.setWidth(15);
//
//        Polygon poly = new Polygon();
//        poly.setColor(Color.RED);
//        poly.setWeight(20);
//        List<Point> vertices = new ArrayList<>(Arrays.asList(new Point(19, 29), new Point(17, 30), new Point(70, 90), new Point(80, 90)));
//        poly.setVertices(vertices);
//
//        PaintedText text = new PaintedText();
//        text.setFontName("Roboto");
//        text.setAnchor(new Point(30, 10));
//        text.setColor(Color.BLUE);
//        text.setContent("Since when does text have a height and width? Fixing fonts to work nicely with that is a year-long project.");
//
//        Image image = new Image();
//        image.setFile(new File("C:\\Users\\jasve_29\\Documents\\Tacticsoft\\DrawingTool\\src\\hqdefault.jpg"));
//        image.setColor(Color.BLACK);
//        image.setHeight(360);
//        image.setWidth(480);
//        image.setAnchor(new Point(100, 30));
//
//        drawing.addDrawingItem(poly);
//        drawing.addDrawingItem(oval);
//        drawing.addDrawingItem(text);
//        drawing.addDrawingItem(image);
        //drawing = load(new SerializationMediator(), PropertiesTool.getProperties());
        drawing = loadByName(new DatabaseMediator(), "Kanna");
        Image img = (Image)(drawing.getItems().stream().filter(o -> o.getClass().equals(Image.class)).findFirst().get());
        img.setFile(new File("C:\\Users\\jasve_29\\Documents\\Tacticsoft\\DrawingTool\\src\\945.gif"));
        img.setWidth(550);
        drawing.setName("Kanna");
        save(new DatabaseMediator(), PropertiesTool.getProperties());
        JavaFXPaintable paintable = new JavaFXPaintable(canvas.getGraphicsContext2D());
        drawing.paintUsing(paintable);
    }

    private Drawing load(PersistencyMediator mediator, Properties properties) {
    mediator.init(properties);
    return mediator.load(properties.getProperty("file"));
    }

    private Drawing loadByName(PersistencyMediator mediator, String name) {
        mediator.init(PropertiesTool.getProperties());
        return mediator.load(name);
    }

    public void save(PersistencyMediator mediator, Properties props) {
        mediator.init(props);
        mediator.save(drawing);
    }
}
