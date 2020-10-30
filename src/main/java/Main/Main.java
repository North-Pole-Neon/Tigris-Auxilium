package Main;

import Main.Features.RWJsonUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    RWJsonUser rwjsu = new RWJsonUser();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Shorten this soon in another function
        rwjsu.getOSVersion();
        String OS = rwjsu.osName;
        String fullPath;
        String nextWin;
        if (OS.equals("Windows 10") || OS.equals("Windows 8") || OS.equals("Windows 7")) {
            fullPath = "C:\\Test\\TA\\Data\\";
        } else {
            fullPath = System.getProperty("user.home") + "/TA/Data/";
        }
        String userfile = fullPath + "User.json";
        rwjsu.userFileExists(userfile);

        if(rwjsu.fileUserExists) {
            nextWin = "Home";
        } else {
            nextWin = "Setup";
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/"+nextWin+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        primaryStage.setTitle("Tigris Auxilium");
        //later add icon
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
