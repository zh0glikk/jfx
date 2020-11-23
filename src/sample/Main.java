package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MainWindowUI.fxml"));
        Scene scene = new Scene(root ,600, 300);

        stage.setScene(scene);

        stage.setTitle("Конвертер температур");
        stage.setResizable(false);

        stage.show();
    }
}