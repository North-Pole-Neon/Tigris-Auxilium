package Main.Controllers;


import Main.Features.tableCons.AppCol;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class AppsCon {

    public ListView colLv;
    public TextField colTfLv;
    public ChoiceBox colCbLv;
    //---
    public TextField colTfName;
    public TextField colTfPath;
    public Button colPathBtn;
    //---
    public TableView<AppCol> colTb;
    public TableColumn<AppCol, String> colColName;
    public TableColumn<AppCol, String> colColPath;




    public void initialize() {
        String st[] = {"Open", "Update", "Create", "delete"};
        colCbLv.getItems().addAll(st);
        System.out.println("Test");

    }

    public void onColListbtn(ActionEvent actionEvent) { //List button

    }

    public void onColPathBtn(ActionEvent actionEvent) {//Col Path Finder

    }

    public void onColAdd(ActionEvent actionEvent) {

    }

    public void onColDelete(ActionEvent actionEvent) {

    }

    public void onColUpdate(ActionEvent actionEvent) {

    }
}
