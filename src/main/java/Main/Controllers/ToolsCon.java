package Main.Controllers;


import Main.Features.CryptFile;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import java.io.File;

public class ToolsCon {
    public TextField txtFieldInput;
    public TextField txtFieldOutput;
    public Button btnInput;
    public Button btnOutput;
    public Button btnERun;
    public RadioButton eRadE;
    public RadioButton eRadD;

    ToggleGroup eGP = new ToggleGroup();

    public void initialize() {

        eRadE.setToggleGroup(eGP);
        eRadD.setToggleGroup(eGP);
    }

    public void inputPathClick(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select input file");
        //fileChooser.showOpenDialog(null);
        //fc.setInitialDirectory(new File ("C:\\path\\to\\folder"));


        File selectedFile = fc.showOpenDialog(null);
        //List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if(selectedFile != null) {
            System.out.println(selectedFile);
            txtFieldInput.setText(selectedFile.getAbsolutePath());
            if(txtFieldOutput.getText().equals("") == true) {
                txtFieldOutput.setText(txtFieldInput.getText());
            }
        } else {
            // put something else here
        }

        //fileChooser.getInitialDirectory();

        /*fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );*/

    }

    public void outputPathClick(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select input file");

        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null) {
            System.out.println(selectedFile);
            txtFieldOutput.setText(selectedFile.getAbsolutePath());
        } else {
            // put something else here
        }
    }

    public void eRun(ActionEvent actionEvent) {
        if(txtFieldInput.getText().equals(txtFieldOutput.getText()) == false) {
            btnERun.setText("Please set output to new name");
            if (eRadE.isSelected() == true){
                CryptFile.fileE(txtFieldInput.getText(), txtFieldOutput.getText(), "password");
            } else {
                CryptFile.fileD(txtFieldInput.getText(), txtFieldOutput.getText(), "password");
            }
        } else {
            btnERun.setText("Please set output to new name");
            /*TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    btnERun.setText("Run");
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, 1000);*/
        }



    }
}