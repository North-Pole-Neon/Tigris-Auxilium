package Main.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class MainCon {
    public Label lbl;
    public int clicks;
    public void onNewBtn(ActionEvent actionEvent) {
        clicks++;
        lbl.setText("You clicked " + clicks + " times");
    }
}
