package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Controller {

    private int inputTemp;
    private double outputTemp;

    private int infoState = 0;
    private String celcInfo = "Градус Цельсія — одиниця вимірювання температури за шкалою Цельсія. Для позначення одиниці використовується символ °C. Широко використовується в побуті та медицині. Градус Цельсія названий на честь шведського вченого Андерса Цельсія, який запропонував в 1742 нову шкалу для вимірювання температури.";
    private String kelvInfo = "Один кельвін дорівнює точно 1/273,16 часткам температури потрійної точки води; один кельвін дорівнює одному градусу Цельсія точно; різниця між двома температурними шкалами дорівнює 273,15 кельвіна точно.";
    private String farInfo = "Одиницею вимірювання є гра́дус Фаренге́йта, або гра́дус Фаренга́йта (°F). За нульову точку Фаренгейт прийняв температуру соляного розчину. Стабілізація температури такого розчину відбувалася при 0 °F (−17.78 °C). Друга точка 32 °F була точкою плавлення льоду(0 °C). ";

    @FXML
    private TextField tempAmountInput;
    @FXML
    private Text tempAmountOutput;
    @FXML
    private Button countBtn;

    @FXML
    private RadioButton radioBtnCelc1;
    @FXML
    private RadioButton radioBtnKelv1;
    @FXML
    private RadioButton radioBtnFar1;
    @FXML
    private RadioButton radioBtnCelc2;
    @FXML
    private RadioButton radioBtnKelv2;
    @FXML
    private RadioButton radioBtnFar2;
    @FXML
    private Text formulaText;
    @FXML
    private Button exitBtn;
    @FXML
    private Button aboutBtn;
    @FXML
    private TextFlow infoText;


    public void readText() {
        this.inputTemp = Integer.parseInt(this.tempAmountInput.getText());
    }

    public void writeText(double countedTemp) {
        this.tempAmountOutput.setText("Значення: " + countedTemp);
    }

    public void writeText(String str) {
        this.tempAmountOutput.setText("Значення: " + str);
    }

    public void writeFormula(String f) {
        formulaText.setText("Формула:  " + f);
    }


    public Converter convertType() {
        if ( radioBtnCelc1.isSelected() ) {
            this.infoState = 0;
            if ( radioBtnCelc2.isSelected() ) return Converter.Cels_Cels;
            if ( radioBtnKelv2.isSelected() ) return Converter.Cels_Kelv;
            if ( radioBtnFar2.isSelected() ) return Converter.Cels_Far;
        }
        if ( radioBtnKelv1.isSelected() ) {
            this.infoState = 1;
            if ( radioBtnCelc2.isSelected() ) return Converter.Kelv_Cels;
            if ( radioBtnKelv2.isSelected() ) return Converter.Kelv_Kelv;
            if ( radioBtnFar2.isSelected() ) return Converter.Kelv_Far;
        }
        if ( radioBtnFar1.isSelected() ) {
            this.infoState = 2;
            if ( radioBtnCelc2.isSelected() ) return Converter.Far_Cels;
            if ( radioBtnKelv2.isSelected() ) return Converter.Far_Kelv;
            if ( radioBtnFar2.isSelected() ) return Converter.Far_Far;
        }
        return Converter.Cels_Cels;
    }

    public double convertCelcToCelc(double inputTemp) {
        return inputTemp;
    }

    public double converCelcToKelv(double inputTemp) {
        return inputTemp + 273.15;
    }

    public double converCelcToFar(double inputTemp) {
        return (inputTemp * 9 / 5) + 32;
    }

    public double convertKelvToCelc(double inputTemp) throws Exception {
        if ( inputTemp < 0 ) {
            throw new Exception();
        }

        return inputTemp - 273.15;
    }

    public double convertKelvToKelv(double inputTemp) throws Exception {
        if ( inputTemp < 0 ) {
            throw new Exception();
        }

        return inputTemp;
    }

    public double convertKelvToFar(double inputTemp) throws Exception {
        if ( inputTemp < 0 ) {
            throw new Exception();
        }

        return converCelcToFar(convertKelvToCelc(inputTemp));
    }

    public double convertFarToCelc(double inputTemp) {
        return (inputTemp - 32) * 5 / 9;
    }

    public double convertFarToKelv(double inputTemp) {
        return convertFarToCelc(convertFarToCelc(inputTemp));
    }

    public double convertFarToFar(double inputTemp) {
        return inputTemp;
    }

    void printTextInfo() {
        String outputInfo = "";
        if ( infoText.getChildren().size() > 0 ) {
            infoText.getChildren().remove(0);
        }
        if ( this.infoState == 0 ) {
            outputInfo = this.celcInfo;
        } else if ( this.infoState == 1 ) {
            outputInfo = this.kelvInfo;
        } else if ( this.infoState == 2 ) {
            outputInfo = this.farInfo;
        }

        infoText.getChildren().add(new Text(outputInfo));
    }


    public void count(ActionEvent e) throws Exception {
        readText();
        Converter convertType = convertType();
        printTextInfo();

        try {
            if (convertType == Converter.Cels_Cels) {
                this.outputTemp = convertCelcToCelc(this.inputTemp);
                writeFormula("C = 1 * C");
            } else if (convertType == Converter.Cels_Kelv) {
                this.outputTemp = converCelcToKelv(this.inputTemp);
                if ( this.outputTemp < 0 ) {
                    throw new Exception();
                }

                writeFormula("K = C + 273,15");
            } else if (convertType == Converter.Cels_Far) {
                this.outputTemp = converCelcToFar(this.inputTemp);
                if ( this.outputTemp < -459.67 ) {
                    throw new Exception();
                }

                writeFormula("F = (C * 9 / 5) + 32");
            } else if (convertType == Converter.Kelv_Cels) {
                this.outputTemp = convertKelvToCelc(this.inputTemp);
                writeFormula("C = K - 273,15");
            } else if (convertType == Converter.Kelv_Kelv) {
                this.outputTemp = convertKelvToKelv(this.inputTemp);
                writeFormula("K = 1 * K");
            } else if (convertType == Converter.Kelv_Far) {
                this.outputTemp = convertKelvToFar(this.inputTemp);
                writeFormula("F = ((K - 273,15) * 9 / 5) + 32");
            } else if (convertType == Converter.Far_Cels) {
                this.outputTemp = convertFarToCelc(this.inputTemp);
                writeFormula("C = (F - 32) / 9 * 5");
            } else if (convertType == Converter.Far_Kelv) {
                this.outputTemp = convertFarToKelv(this.inputTemp);
                if ( this.outputTemp < 0 ) {
                    throw new Exception();
                }

                writeFormula("K = ((F - 273,15) - 32) * 5 / 9");
            } else if (convertType == Converter.Far_Far) {
                this.outputTemp = convertFarToFar(this.inputTemp);
                writeFormula("F = 1 * F");
            }
        } catch (Exception exception) {
            writeText("Error");
            return;
        }
        writeText(this.outputTemp);
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }

    public void about(ActionEvent event) throws IOException {
        Scene scene = aboutBtn.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        Parent loader = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene sceneTmp = new Scene(loader ,600, 300);
        stage.setScene(sceneTmp);

        stage.setTitle("Інформація про розробника програми");
        stage.setResizable(false);

        stage.show();
//        Scene scene = new Scene(root ,600, 300);
    }

}
