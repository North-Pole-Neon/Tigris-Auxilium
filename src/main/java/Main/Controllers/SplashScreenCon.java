package Main.Controllers;

import Main.Features.RWJsonUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenCon {

    public AnchorPane anchor;

    public Boolean test;
    public String ert;

    RWJsonUser rwjsu = new RWJsonUser();

    public void initialize() {
        //givenUsingTimer_whenSchedulingTaskOnce_thenCorrect();
        System.out.println();
        runner();

    }

    public void givenUsingTimer_whenSchedulingTaskOnce_thenCorrect() {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + "n" +
                        "Thread's name: " + Thread.currentThread().getName());
                        runner();
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 5000L; //10 seconds = 10,000
        timer.schedule(task, delay);
    }

    public void runner() {

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

        if(rwjsu.fileUserExists == true) {
            nextWin = "Home";
        } else {
            nextWin = "Setup";
        }


        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Main/fxml/"+nextWin+".fxml")); //"/Main/fxml/"+nextWin+".fxml"
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) anchor).getScene().getWindow();
                    //actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
