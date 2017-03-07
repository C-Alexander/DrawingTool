package sample.DomainClasses;

/**
 * Created by jasve_29 on 19-Feb-17.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public abstract class DrawingItem {
    private Color color;
    private DrawingItem previousState;

    DrawingItem(DrawingItem previousState) {
        this.previousState = previousState;
    }

    DrawingItem() {
    }

    @SuppressWarnings("unused")
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract Point getAnchor();

    @SuppressWarnings("unused")
    public abstract double getWidth();

    @SuppressWarnings("unused")
    public abstract double getHeight();

    void setPreviousState() {
        this.color = previousState.color;
    }
    void undo() {
        setPreviousState();
        if (previousState.previousState != null) {
            this.previousState = previousState.previousState;
        }
    }

    @Override
    public String toString() {
        return "DrawingItem{" +
                "color=" + color +
                '}';
    }

    public void paintUsing(Paintable paintable) {

    }
}
