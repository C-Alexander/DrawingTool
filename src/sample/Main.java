package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene drawing = new Scene(root, 645, 451);
        primaryStage.setScene(drawing);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
