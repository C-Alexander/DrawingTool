package sample.DomainClasses;


import sample.DomainClasses.drawing.javafx.JavaFXPaintable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jasve_29 on 19-Feb-17.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Drawing {
    private String name;
    private final List<DrawingItem> items = new ArrayList<>();
    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    public void addDrawingItem(DrawingItem item) {
        items.add(item);
    }

    @SuppressWarnings("unused")
    public void removeDrawingItem(DrawingItem item) {
        items.remove(item);
    }

    public void removeDrawingItem(int item) {
        items.remove(item);
    }
    public List<DrawingItem> getItems() {
        return Collections.unmodifiableList(items);
    }
    public void clearAll() {
        items.clear();
    }

    public void paintUsing(Paintable paintable) {
        for(DrawingItem item: items) {
            item.paintUsing(paintable);
        }
    }
}
