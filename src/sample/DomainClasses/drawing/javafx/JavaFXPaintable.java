package sample.DomainClasses.drawing.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.DomainClasses.*;
import sample.DomainClasses.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jasve_29 on 07-Mar-17.
 */
public class JavaFXPaintable implements Paintable {

    private final GraphicsContext graphics;

    public JavaFXPaintable(GraphicsContext graphicsContext) {
        graphics = graphicsContext;
    }

    @Override
    public void paint(Oval oval) {
        setColor(oval);
        graphics.setLineWidth(oval.getWeight());
        graphics.strokeOval(oval.getAnchor().getX(), oval.getAnchor().getY(), oval.getWeight(), oval.getHeight());
    }

    @Override
    public void paint(Polygon polygon) {
        setColor(polygon);
        graphics.setLineWidth(polygon.getWeight());
        int pointCount = polygon.getVertices().size();
        double[] xPoints = polygon.getVertices().stream().mapToDouble(Point::getX).toArray();
        double[] yPoints = polygon.getVertices().stream().mapToDouble(Point::getY).toArray();
        graphics.strokePolygon(xPoints, yPoints, pointCount);
    }

    @Override
    public void paint(PaintedText text) {
        setColor(text);
        if (text.getFontName() != null) {
            graphics.setFont(Font.font(text.getFontName()));
        }
        graphics.strokeText(text.getContent(), text.getAnchor().getX(), text.getAnchor().getY());
    }

    @Override
    public void paint(Image image) {
        setColor(image);
        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(image.getFile().getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        javafx.scene.image.Image img = new javafx.scene.image.Image(fileStream);
        graphics.drawImage(img, image.getAnchor().getX(), image.getAnchor().getY(), image.getWidth(), image.getHeight());
    }

    public void setColor(DrawingItem item) {
        Color color;
        switch (item.getColor()) {
            case BLACK:
                color = Color.BLACK;
                break;
            case WHITE:
                color = Color.WHITE;
                break;
            case RED:
                color = Color.RED;
                break;
            case BLUE:
                color = Color.BLUE;
                break;
            case GREEN:
                color = Color.GREEN;
                break;
            default:
                color = Color.DARKCYAN;
                break;
        }
        graphics.setStroke(color);
    }
}
