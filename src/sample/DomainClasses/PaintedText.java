package sample.DomainClasses;

/**
 * Created by jasve_29 on 19-Feb-17.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class PaintedText extends DrawingItem {
    private String content;
    private String fontName;
    private Point anchor;
    private double width;
    private double height;
    private PaintedText previousState;

    @SuppressWarnings("unused")
    public PaintedText(PaintedText previousState) {
        this.previousState = previousState;
        setPreviousState();
    }

    @Override
    public String toString() {
        return super.toString() +
                "PaintedText{" +
                "content='" + content + '\'' +
                ", fontName='" + fontName + '\'' +
                ", anchor=" + anchor +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public PaintedText() {

    }

    @SuppressWarnings("WeakerAccess")
    public String getContent() {
        return content;
    }

    @SuppressWarnings("WeakerAccess")
    public void setContent(String content) {
        this.content = content;
    }

    @SuppressWarnings("WeakerAccess")
    public String getFontName() {
        return fontName;
    }

    @SuppressWarnings("WeakerAccess")
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }

    @Override
    void setPreviousState() {
        super.setPreviousState();
        this.setContent(previousState.getContent());
        this.setFontName(previousState.getFontName());
        this.setAnchor(previousState.getAnchor());
        this.setWidth(previousState.getWidth());
        this.setHeight(previousState.getHeight());
    }

    @Override
    public void undo() {
        super.undo();
        setPreviousState();
        previousState = previousState.previousState;
    }

    public double getWidth() {
        return width;
    }

    @SuppressWarnings("WeakerAccess")
    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    @SuppressWarnings("WeakerAccess")
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void paintUsing(Paintable paintable) {
        paintable.paint(this);
    }
}
