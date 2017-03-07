package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sample.DomainClasses.DrawingTool;

public class Controller {
    @FXML
    Canvas canvas;


    public void initialize() {
        DrawingTool drawingTool = new DrawingTool(canvas);
        drawingTool.draw();
    }
}
