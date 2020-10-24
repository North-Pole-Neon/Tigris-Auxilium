package Main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HomeCon {


    public StackPane stackPane;
    public Button btnB;
    public Button btnA;
    public Button btnC;
    public Button btnH;
    public Button btnT;
    public Button btnHW;
    public Button btnHe;
    public BorderPane bpB;
    public BorderPane bpA;
    public BorderPane bpC;
    public BorderPane bpH;
    public BorderPane bpT;
    public BorderPane bpHW;
    public BorderPane bpHe;


    public void initialize() {

        hidePanes();
        bpH.setVisible(true);
        bpH.setDisable(false);
    }


    public void onActB(ActionEvent actionEvent) {
        hidePanes();
        bpB.setVisible(true);
        bpB.setDisable(false);

    }

    public void onActA(ActionEvent actionEvent) {
        hidePanes();
        bpA.setVisible(true);
        bpA.setDisable(false);
    }

    public void onActC(ActionEvent actionEvent) {
        hidePanes();
        bpC.setVisible(true);
        bpC.setDisable(false);
    }

    public void onActH(ActionEvent actionEvent) {
        hidePanes();
        bpH.setVisible(true);
        bpH.setDisable(false);
    }

    public void onActT(ActionEvent actionEvent) {
        hidePanes();
        bpT.setVisible(true);
        bpT.setDisable(false);
    }

    public void onActHW(ActionEvent actionEvent) {
        hidePanes();
        bpHW.setVisible(true);
        bpHW.setDisable(false);
    }

    public void onActHe(ActionEvent actionEvent) {
        hidePanes();
        bpHe.setVisible(true);
        bpHe.setDisable(false);
    }

    void hidePanes() {
        bpB.setVisible(false);
        bpB.setDisable(true);
        bpA.setVisible(false);
        bpA.setDisable(true);
        bpC.setVisible(false);
        bpC.setDisable(true);
        bpH.setVisible(false);
        bpH.setDisable(true);
        bpT.setVisible(false);
        bpT.setDisable(true);
        bpHW.setVisible(false);
        bpHW.setDisable(true);
        bpHe.setVisible(false);
        bpHe.setDisable(true);
    }
}

/*
bp3.setVisible(false);
    bp3.setDisable(true);
 */

//bp1.setVisible(true);
//bp1.setDisable(false);