package Main.Features;

import Main.Features.tableCons.Tabs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.JOptionPane; //Might need to be *
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {

    Connection conn = null;
    RWJsonUser rwjsu = new RWJsonUser();

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

            rwjsu.getOSVersion();
            rwjsu.rootPathMaker();
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
                //System.out.println("Tsetsessdfsdf");
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


    public Connection sqlCon(String name) { // TODO Need to work on this

        Connection conn  = null;
        try{
            //conn = DriverManager.getConnection("jdbc:mysql://www.neopect.heliohost.org:3306/neopect_test", "neopect_neo", "TyPass01");
            String sqlpath = null;

            rwjsu.getOSVersion();
            rwjsu.rootPathMaker();
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
                System.out.println("Tsetsessdfsdf");
                sqlpath = fullPath + name + ".sqlite";
            } else {
                nextWin = "Setup";
            }


            conn = DriverManager.getConnection("jdbc:mysql:" + sqlpath);
            return conn;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    public void tester(Class good, String name) {


    }


    /*public ObservableList getSqlList(ObservableList oList, String table, Class<Object> tssss){ //--------------------------------------------------
        //ObservableList<table> tabList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        Connection conn = sqliteCon("Tabs");

        String query = "select * from " + table + "";
        Statement st = null; //Use prepare statement for repetitive scripts
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Tabs tabsCol;
            while(rs.next()) {
                tabsCol = new Tabs(rs.getString("name"), rs.getString("url"));
                oList.add(tabsCol);
            }

        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return tabList;
    }*/

}
