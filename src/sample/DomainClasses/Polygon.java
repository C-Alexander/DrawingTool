package sample.DomainClasses;

import java.util.Collections;
import java.util.List;

/**
 * Created by jasve_29 on 19-Feb-17.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Polygon extends DrawingItem {
    private List<Point> vertices;
    private double weight;
    private Polygon previousState;

    public Polygon(Polygon p) {
        super(p);
        previousState = new Polygon();
        previousState.previousState = p.previousState;
        setPreviousState();
    }
    @Override
    void setPreviousState() {
        super.setPreviousState();
        vertices = previousState.vertices;
        weight = previousState.weight;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Polygon{" +
                "vertices=" + vertices.toString() +
                ", weight=" + weight +
                '}';
    }

    public Polygon() {

    }

    @Override
    public void undo() {
        super.undo();
        setPreviousState();
        previousState = previousState.previousState;
    }

    public Polygon(Color red, int i) {
        super();
        setColor(red);
        setWeight(i);
    }

    @SuppressWarnings("unused")
    public List<Point> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    public void setVertices(List<Point> vertices) {
        this.vertices = vertices;
    }
    @SuppressWarnings("unused")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Point getAnchor() {
        return Point.getRandom();
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @SuppressWarnings("unused")
    public Polygon getPreviousState() {
        return previousState;
    }

    @Override
    public void paintUsing(Paintable paintable) {
        paintable.paint(this);
    }
}
