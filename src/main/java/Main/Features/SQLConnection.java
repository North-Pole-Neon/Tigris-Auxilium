package Main.Features;

import javax.swing.JOptionPane; //Might need to be *
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    Connection conn = null;

    public Connection dbConnector(String fName) {

        String fullPath;
        String OS = "Windows 10"; //RWJsonUser.osName;

        if (OS.equals("Windows 10") || OS.equals("Windows 8") || OS.equals("Windows 7")) {
            fullPath = "C:\\Test\\TA\\Data\\"+fName+".sqlite";

        } else {

            fullPath = System.getProperty("user.home") + "/TA/Data/"+fName+".sqlite";
            //System.out.println(full);
        }

        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+fullPath);
            System.out.println("Connection Successful with: " + fName);//PRINT Connection Successful
            return conn;
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }


    public Connection sqliteCon(String name) {

        Connection conn  = null;
        try{
            //conn = DriverManager.getConnection("jdbc:mysql://www.neopect.heliohost.org:3306/neopect_test", "neopect_neo", "TyPass01");
            String sqlpath = null;

            RWJsonUser.getOSVersion();
            RWJsonUser.rootPathMaker();
            String OS = RWJsonUser.osName;
            String fullPath;
            String nextWin;
            if (OS.equals("Windows 10") || OS.equals("Windows 8") || OS.equals("Windows 7")) {
                fullPath = "C:\\Test\\TA\\Data\\";
            } else {
                fullPath = System.getProperty("user.home") + "/TA/Data/";
            }
            String userfile = fullPath + "User.json";
            RWJsonUser.userFileExists(userfile);

            if(RWJsonUser.fileUserExists) {
                nextWin = "Home";
                System.out.println("Tsetsessdfsdf");
                sqlpath = fullPath + name + ".sqlite";
            } else {
                nextWin = "Setup";
            }


            conn = DriverManager.getConnection("jdbc:sqlite:" + sqlpath);
            return conn;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    public void tester(Class good, String name) {

    }

}
