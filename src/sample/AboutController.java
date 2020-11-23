package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AboutController implements Initializable {

    @FXML
    private Button closeBtn;
    @FXML
    private Text timeText;



    public void close(ActionEvent event) throws IOException {
        Scene scene = closeBtn.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        Parent root = FXMLLoader.load(getClass().getResource("MainWindowUI.fxml"));
        Scene sceneTmp = new Scene(root ,600, 300);

        stage.setScene(sceneTmp);

        stage.setTitle("Конвертер температур");
        stage.setResizable(false);

        stage.show();
//        Scene scene = new Scene(root ,600, 300);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        this.timeText.setText(this.timeText.getText() + formatter.format(date));
    }
}
