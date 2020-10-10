package Main.Controllers;

import Main.Features.OSDetector;
import Main.Features.tableCons.AppCol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class AppsCon {
    @FXML
    private ListView<String> colLv;
    public TextField colTfLv;
    public ChoiceBox<String> colCbLv;
    //---
    public TextField colTfName;
    public TextField colTfPath;
    public Button colPathBtn;
    //---
    public TableView<AppCol> colTb;
    public TableColumn<AppCol, String> colColName;
    public TableColumn<AppCol, String> colColPath;


    public void initialize() {
        //String[] st = new String[] {"Open", "Update", "Create", "Delete"};
        //colCbLv.getItems().addAll(st);

        ObservableList<String> lvOptions = FXCollections.observableArrayList("Open", "Update", "Create", "Delete");
        colCbLv.setValue("Open");
        colCbLv.setItems(lvOptions);

        System.out.println("Test");
        //showProject();
        colFRefLv();


    }

    public void onColListbtn(ActionEvent actionEvent) { //List button
        appcolDo();
    }

    public void onColPathBtn(ActionEvent actionEvent) {//Col Path Finder
        FileChooser fc = new FileChooser();
        fc.setTitle("Select Application Executable");

        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null) {
            System.out.println(selectedFile);
            colTfPath.setText(selectedFile.getAbsolutePath());
        } else {
            // put something else here
        }

    }

    public void onColAdd(ActionEvent actionEvent) {
        insertRecord();

    }

    public void onColDelete(ActionEvent actionEvent) {
        deleteRecord();
    }

    public void onColUpdate(ActionEvent actionEvent) {
        updateRecord();

    }

    public void onColTbClicked(MouseEvent mouseEvent) {
        AppCol appcol = colTb.getSelectionModel().getSelectedItem();
        colTfName.setText(appcol.getName());
        colTfPath.setText(appcol.getPath());
    }

    public void onColTbKeyReleased(KeyEvent keyEvent) {
        AppCol appcol = colTb.getSelectionModel().getSelectedItem();
        colTfName.setText(appcol.getName());
        colTfPath.setText(appcol.getPath());
    }

    public void onColLvClick(MouseEvent mouseEvent) {
        String selected = colLv.getSelectionModel().getSelectedItem();
        colTfLv.setText(selected);
        showApps();

    }

    public Connection getConnection() {
        Connection conn;
        try{
            //conn = DriverManager.getConnection("jdbc:mysql://www.neopect.heliohost.org:3306/neopect_test", "neopect_neo", "TyPass01");
            conn = DriverManager.getConnection("jdbc:sqlite:C:/Test/TA/Data/Collections.sqlite"); //--------------------------------------------------
            return conn;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void colFRefLv(){ //--------------------------------------------------
        ObservableList<String> names = FXCollections.observableArrayList();

        Connection conn = getConnection();
        String query = "select name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%'";
        Statement st = null; //Use prepare statement for repetitive scripts
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                names.add(rs.getString("name"));
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
        colLv.setItems(names);
    }


    public ObservableList<AppCol> getAppList(){ //--------------------------------------------------
        ObservableList<AppCol> appList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from " + colLv.getSelectionModel().getSelectedItem() + "";
        Statement st = null; //Use prepare statement for repetitive scripts
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            AppCol appCol;
            while(rs.next()) {
                appCol = new AppCol(rs.getString("name"), rs.getString("path"));
                appList.add(appCol);
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
        return appList;
    }

    public void showApps() { //--------------------------------------------------
        ObservableList<AppCol> list = getAppList();

        colColName.setCellValueFactory(new PropertyValueFactory<AppCol, String>("name"));
        colColPath.setCellValueFactory(new PropertyValueFactory<AppCol, String>("path"));

        colTb.setItems(list);

    }

    private void insertRecord() {
        String query = "INSERT INTO "+colLv.getSelectionModel().getSelectedItem()+" (name,path) VALUES ('"+ colTfName.getText() + "','" + colTfPath.getText() + "')";
        executeQuery(query);
        showApps();
    }

    private void updateRecord() {
        String query = "UPDATE "+colLv.getSelectionModel().getSelectedItem()+" SET name = '" + colTfName.getText() + "', path = '" + colTfPath.getText() + "' WHERE name = '" + colTfName.getText() + "' OR path = '" +colTfPath.getText()+ "'";
        executeQuery(query);
        showApps();
    }

    private void deleteRecord() {
        String query = "DELETE FROM "+colLv.getSelectionModel().getSelectedItem()+" WHERE name = '" + colTfName.getText() + "'";
        executeQuery(query);
        showApps();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st = null;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void appcolDo() {
        String query = null;
        switch (colCbLv.getSelectionModel().getSelectedItem().toString()) {
            // ----------------------------------------------------------------------------
            case "Open":

                try {
                    Connection conn = getConnection();
                    query = "select Path from "+colLv.getSelectionModel().getSelectedItem().toString();
                    System.out.println(query);
                    PreparedStatement pst =  conn.prepareStatement(query);
                    ResultSet rs = pst.executeQuery();

                    while(rs.next()) {
                        File filename = new File(rs.getString("Path"));
                        open(filename);
                    }

                    pst.close();
                    rs.close();
                    conn.close();

                }catch (Exception e1) {
                    e1.printStackTrace();
                }

                //refreshTable();
                System.out.println("Opened list");
                break;
            // ----------------------------------------------------------------------------
            case "Update":
                showApps();
                colFRefLv();
                System.out.println("Updated list");
                break;
            // ----------------------------------------------------------------------------
            case "Create":

                query = "CREATE TABLE "+colTfLv.getText()+" (Name TEXT PRIMARY KEY NOT NULL, Path TEXT NOT NULL) ";
                executeQuery(query);

                showApps();
                colFRefLv();

                System.out.println("Created table: " +colTfLv.getText());
                break;
            // ----------------------------------------------------------------------------
            case "Delete":


                query = "DROP TABLE "+ colLv.getSelectionModel().getSelectedItem();
                executeQuery(query);


                showApps();
                colFRefLv();

                System.out.println("Deleted table: " + colTfLv.getText());
                break;
            // ----------------------------------------------------------------------------
            default:
                System.out.println("`Do` function can't recongize comboBox");
        }

    }

    public static boolean open(File file)
    {
        try
        {
            if (OSDetector.isWindows())
            {
                Runtime.getRuntime().exec(new String[]
                        {"rundll32", "url.dll,FileProtocolHandler",
                                file.getAbsolutePath()});
                return true;
            } else if (OSDetector.isLinux() || OSDetector.isMac())
            {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open",
                        file.getAbsolutePath()});
                return true;
            } else
            {
                // Unknown OS, try with desktop
                if (Desktop.isDesktopSupported())
                {
                    Desktop.getDesktop().open(file);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace(System.err);
            return false;
        }
    }

}
