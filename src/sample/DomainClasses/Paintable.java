package sample.DomainClasses;

/**
 * Created by jasve_29 on 07-Mar-17.
 */
public interface Paintable {
    void paint(Oval oval);
    void paint(Polygon polygon);
    void paint(PaintedText text);
    void paint(Image image);
}
