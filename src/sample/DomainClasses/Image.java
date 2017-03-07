package sample.DomainClasses;

import java.io.File;

/**
 * Created by jasve_29 on 19-Feb-17.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Image extends DrawingItem {
    private File file;
    private Point anchor;
    private double width;
    private double height;
    private Image previousState;

    @SuppressWarnings("unused")
    public Image(Image previousState) {
        this.previousState = previousState;
        setPreviousState();
    }

    public Image() {

    }

    @Override
    public String toString() {
        return super.toString() +
                "Image{" +
                "file=" + file +
                ", anchor=" + anchor +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public double getHeight() {
        return height;
    }

    @SuppressWarnings("WeakerAccess")
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    @SuppressWarnings("WeakerAccess")
    public void setWidth(double width) {
        this.width = width;
    }

    public Point getAnchor() {
        return anchor;
    }

    @Override
    void setPreviousState() {
        super.setPreviousState();
        setHeight(previousState.getHeight());
        setWidth(previousState.getWidth());
        setAnchor(previousState.getAnchor());
        setFile(previousState.getFile());
    }

    @Override
    public void undo() {
        super.undo();
        setPreviousState();
        previousState = previousState.previousState;
    }

    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }

    @SuppressWarnings("WeakerAccess")
    public File getFile() {
        return file;
    }

    @SuppressWarnings("WeakerAccess")
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void paintUsing(Paintable paintable) {
        paintable.paint(this);
    }
}
