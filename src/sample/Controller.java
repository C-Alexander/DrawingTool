package sample;

import com.esotericsoftware.minlog.Log;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import sample.DomainClasses.DrawingTool;
import sample.persistency.SerializationMediator;

import java.io.*;
import java.util.Properties;

public class Controller {
    @FXML
    Canvas canvas;


    public void initialize() {
        DrawingTool drawingTool = new DrawingTool(canvas);
        drawingTool.draw();

        drawingTool.save(new SerializationMediator(), PropertiesTool.getProperties());
    }
}
