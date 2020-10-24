package Main.Controllers;

import Main.Features.Auxy;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;

public class AuxyCon {
    public TitledPane paneAuxy;
    public Button btnAuxy;
    public TextField txtFieldAuxy;

    public void initialize() {

    }

    public void onClickedAuxy(ActionEvent actionEvent) {
        Auxy auxy = new Auxy();
        paneAuxy.setText("Alright... - " + auxy.askAuxy(txtFieldAuxy.getText()));
    }

    public void onKeyReleasedAuxy(KeyEvent keyEvent) {
        paneAuxy.setText("Ask Auxy");
    }
}
