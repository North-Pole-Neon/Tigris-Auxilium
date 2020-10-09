package Main.Features;

import javax.swing.JOptionPane; //Might need to be *
import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {

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

}
