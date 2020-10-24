package Main.Controllers;

import Main.Features.OSDetector;
import Main.Features.tableCons.AppCol;
import Main.Features.tableCons.Tabs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.File;
import java.sql.*;

public class BrowserCon {
    //Make Private with @FXML in them as well
    public ListView<String> tabLv;
    public ChoiceBox<String> tabCbLv;
    public TextField tabTfLv;
    public Button tabBtnLv;

    public TextField tabTfName;
    public TextField tabTfUrl;

    public TableView<Tabs> tabTb;
    public TableColumn<Tabs, String> tabColName;
    public TableColumn<Tabs, String> tabColURL;



    public void initialize() {

        ObservableList<String> lvOptions = FXCollections.observableArrayList("Open", "Update", "Create", "Delete");
        tabCbLv.setValue("Open");
        tabCbLv.setItems(lvOptions);


        //showProject();
        tabFRefLv();

    }

    public void onColLvClick(MouseEvent mouseEvent) {
        String selected = tabLv.getSelectionModel().getSelectedItem();
        tabTfLv.setText(selected);
        showTabs();
        tabFRefLv();
    }

    public void onTabListbtn(ActionEvent actionEvent) {
        tabLvDo();
    }

    public void onTabAdd(ActionEvent actionEvent) {
        insertRecord();
    }

    public void onTabDelete(ActionEvent actionEvent) {
        deleteRecord();
        showTabs();
        tabFRefLv();
    }

    public void onTabUpdate(ActionEvent actionEvent) {
        updateRecord();
        showTabs();
        tabFRefLv();
    }

    public void onColTbKeyReleased(KeyEvent keyEvent) {
        Tabs tab = tabTb.getSelectionModel().getSelectedItem();
        tabTfName.setText(tab.getName());
        tabTfUrl.setText(tab.getUrl());
    }

    public void onColTbClicked(MouseEvent mouseEvent) {
        Tabs tab = tabTb.getSelectionModel().getSelectedItem();
        tabTfName.setText(tab.getName());
        tabTfUrl.setText(tab.getUrl());
    }

    public Connection getConnection() {
        Connection conn;
        try{
            //conn = DriverManager.getConnection("jdbc:mysql://www.neopect.heliohost.org:3306/neopect_test", "neopect_neo", "TyPass01");
            conn = DriverManager.getConnection("jdbc:sqlite:C:/Test/TA/Data/Tabs.sqlite");
            return conn;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void tabFRefLv(){ //--------------------------------------------------
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
        tabLv.setItems(names);
    }

    public ObservableList<Tabs> getTabList(){ //--------------------------------------------------
        ObservableList<Tabs> tabList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from " + tabLv.getSelectionModel().getSelectedItem() + "";
        Statement st = null; //Use prepare statement for repetitive scripts
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Tabs tabsCol;
            while(rs.next()) {
                tabsCol = new Tabs(rs.getString("name"), rs.getString("url"));
                tabList.add(tabsCol);
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
    }

    public void showTabs() { //--------------------------------------------------
        ObservableList<Tabs> list = getTabList();

        tabColName.setCellValueFactory(new PropertyValueFactory<Tabs, String>("name"));
        tabColURL.setCellValueFactory(new PropertyValueFactory<Tabs, String>("url"));

        tabTb.setItems(list);

    }

    private void insertRecord() {
        String query = "INSERT INTO "+tabLv.getSelectionModel().getSelectedItem()+" (name,url) VALUES ('"+ tabTfName.getText() + "','" + tabTfUrl.getText() + "')";
        executeQuery(query);
        showTabs();
    }

    private void updateRecord() {
        String query = "UPDATE "+tabLv.getSelectionModel().getSelectedItem()+" SET name = '" + tabTfName.getText() + "', url = '" + tabTfUrl.getText() + "' WHERE name = '" + tabTfName.getText() + "' OR url = '" +tabTfUrl.getText()+ "'";
        executeQuery(query);
        showTabs();
    }

    private void deleteRecord() {
        String query = "DELETE FROM "+tabLv.getSelectionModel().getSelectedItem()+" WHERE name = '" + tabTfName.getText() + "'";
        executeQuery(query);
        showTabs();
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

    public void tabLvDo() {
        String query = null;
        switch (tabCbLv.getSelectionModel().getSelectedItem().toString()) {
            // ----------------------------------------------------------------------------
            case "Open":

                try {
                    Connection conn = getConnection();
                    query = "select url from "+tabLv.getSelectionModel().getSelectedItem().toString();
                    System.out.println(query);
                    PreparedStatement pst =  conn.prepareStatement(query);
                    ResultSet rs = pst.executeQuery();

                    while(rs.next()) {
                        java.awt.Desktop.getDesktop().browse(java.net.URI.create(rs.getString("url")));
                    }

                    pst.close();
                    rs.close();
                    conn.close();

                }catch (Exception e1) {
                    e1.printStackTrace();
                }


                System.out.println("Opened list");
                break;
            // ----------------------------------------------------------------------------
            case "Update":
                //showTabs();
                tabFRefLv();

                System.out.println("Updated list");
                break;
            // ----------------------------------------------------------------------------
            case "Create":

                query = "CREATE TABLE "+tabTfLv.getText()+" (Name TEXT PRIMARY KEY NOT NULL, url TEXT NOT NULL) ";
                executeQuery(query);

                //showTabs();
                tabFRefLv();

                System.out.println("Created table: " +tabTfLv.getText());
                break;
            // ----------------------------------------------------------------------------
            case "Delete":


                query = "DROP TABLE "+ tabLv.getSelectionModel().getSelectedItem();
                executeQuery(query);


                tabFRefLv();

                System.out.println("Deleted table: " + tabTfLv.getText());
                break;
            // ----------------------------------------------------------------------------
            default:
                System.out.println("`Do` function can't recongize comboBox");
        }

    }


}
