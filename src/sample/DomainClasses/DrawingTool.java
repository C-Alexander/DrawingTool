package sample.DomainClasses;

import javafx.scene.canvas.Canvas;
import sample.DomainClasses.drawing.javafx.JavaFXPaintable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jasve_29 on 07-Mar-17.
 */
public class DrawingTool {
    private Canvas canvas;
    public Drawing drawing;

    public DrawingTool(Canvas canvas) {

        this.canvas = canvas;
        drawing = new Drawing();
    }

    public void draw() {
        Oval oval = new Oval();
        oval.setColor(Color.GREEN);
        oval.setAnchor(new Point(10, 20));
        oval.setWeight(10);
        oval.setHeight(20);
        oval.setWidth(15);

        Polygon poly = new Polygon();
        poly.setColor(Color.RED);
        poly.setWeight(20);
        List<Point> vertices = new ArrayList<>(Arrays.asList(new Point(15, 18), new Point(14, 18), new Point(70, 90), new Point(80, 90)));
        poly.setVertices(vertices);

        
        drawing.addDrawingItem(poly);
        drawing.addDrawingItem(oval);
        JavaFXPaintable paintable = new JavaFXPaintable(canvas.getGraphicsContext2D());
        drawing.paintUsing(paintable);
    }
}
