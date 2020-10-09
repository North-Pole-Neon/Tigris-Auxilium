package Main.Controllers;


import Main.Features.RWJsonUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SetupCon {

    public TextField txtBoxFN;
    public TextField txtBoxLN;
    public ChoiceBox<String> comboGrade;
    public TextField txtBoxISD;

    public void initialize() {
        ObservableList<String> list = comboGrade.getItems();
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");

    }


    public void FinishedClicked(ActionEvent actionEvent) {

        RWJsonUser.firstName = txtBoxFN.getText();
        RWJsonUser.lastName = txtBoxLN.getText();
        RWJsonUser.sGrade = comboGrade.getSelectionModel().toString();
        RWJsonUser.sIsd = txtBoxISD.getText();
        RWJsonUser.getComputerName(false);
        RWJsonUser.getOSVersion();
        RWJsonUser.rootPathMaker();
        RWJsonUser.jsonPathMaker("User");
        RWJsonUser.setupCom = "true";

        RWJsonUser.WriteToJson();
        RWJsonUser.ReadToJson();

        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void buildSaveSys() {

        copyFiles("ProjectPlanner.sqlite");
        copyFiles("Collections.sqlite");
    }

    public void copyFiles(String fileName) {

        //Path source = null;


        Object OS = RWJsonUser.osName;
        String fullPath;
        if (OS.equals("Windows 10") || OS.equals("Windows 8") || OS.equals("Windows 7")) {
            fullPath = "C:\\Test\\TA\\Data\\"+fileName;

        } else {

            String paths = System.getProperty("user.home");
            //System.out.println(paths);

            fullPath = paths + "/TA/Data/"+fileName;
            //System.out.println(full);
        }


        Path destination = Paths.get(fullPath); //"C:\\Test\\TA\\Data\\" + fileName

	    /*try {
	    	source = Paths.get(PanelSetup.class.getResource("/mainFolder/resources/" + fileName).toURI());
	    	Files.copy(source, destination); //Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/

        try (InputStream stream = getClass().getResourceAsStream("/src/resources/" + fileName)) { //Fsdfsd
            Files.copy(stream, destination);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
