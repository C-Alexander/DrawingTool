package sample.DomainClasses;

/**
 * Created by jasve_29 on 19-Feb-17.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Oval extends DrawingItem {
    private Point anchor;
    private double width;
    private double height;
    private double weight;
    private Oval previousState;

    @SuppressWarnings("unused")
    public Oval(Oval previousState) {
        this.previousState = previousState;
        setPreviousState();
    }

    @Override
    public String toString() {
        return super.toString() +
                "Oval{" +
                "anchor=" + anchor +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    @Override
    void setPreviousState() {
        super.setPreviousState();
        this.anchor = previousState.getAnchor();
        this.width = previousState.getWidth();
        this.height = previousState.getHeight();
        this.weight = previousState.getWeight();
    }

    public Oval() {

    }
    @Override
    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @SuppressWarnings("unused")
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void undo() {
        super.undo();
        setPreviousState();
    }

    @Override
    public double getHeight() {
        return height;
    }

    @SuppressWarnings("unused")
    public void setHeight(double height) {
        this.height = height;
    }

    @SuppressWarnings("WeakerAccess")
    public double getWeight() {
        return weight;
    }

    @SuppressWarnings("unused")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void paintUsing(Paintable paintable) {
        paintable.paint(this);
    }
}
