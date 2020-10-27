package Main.Controllers;


import Main.Features.SQLConnection;
import Main.Features.tableCons.PPlanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.sql.*;

public class HomeworkCon {
    public TextField tfName;
    public TextField tfDes;
    public TextField tfStatus;
    public TextField tfPri;
    public TextField tfDueDate;
    public TextField tfCat;
    public TableView<PPlanner> tbPPlanner;
    public TableColumn<PPlanner, String> colName;
    public TableColumn<PPlanner, String> colDes;
    public TableColumn<PPlanner, Integer> colStatus;
    public TableColumn<PPlanner, Integer> colPri;
    public TableColumn<PPlanner, String> colDue;
    public TableColumn<PPlanner, Integer> colCat;
    public Button btnInsert;
    public Button btnUpdate;
    public Button btnDelete;
    public ChoiceBox cbSearch;
    public TextField tfpSearch;

    //Connection connection = null;
    SQLConnection Con = new SQLConnection();

    public void initialize() {
        //SqliteConnection sqlConn = new SqliteConnection();
        //connection = sqlConn.dbConnector("ProjectPlanner");

        showProject();

        ObservableList<String> lvOptions = FXCollections.observableArrayList("Name", "Description", "Status", "priority", "Due Date", "Cat");
        //cbSearch.setValue("Name");
        cbSearch.setItems(lvOptions);
        cbSearch.getSelectionModel().selectFirst();

    }

    public void btn1Action(ActionEvent actionEvent) {
        /*try {
            String query = "select * from ProjectInfo"; //If you want all use * "select * from "
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
        }catch(Exception e) {
            e.printStackTrace();
        }*/
    }

    public void insertAction(ActionEvent actionEvent) {
        insertRecord();
    }

    public void updateAction(ActionEvent actionEvent) {
        updateRecord();
    }

    public void deleteAction(ActionEvent actionEvent) {
        deleteRecord();
    }

    /*public Connection getConnection() {
        Connection conn;
        try{
            //conn = DriverManager.getConnection("jdbc:mysql://www.neopect.heliohost.org:3306/neopect_test", "neopect_neo", "TyPass01");
            conn = DriverManager.getConnection("jdbc:sqlite:C:/Test/TA/Data/ProjectPlanner.sqlite"); //--------------------------------------------------
            return conn;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }*/

    public ObservableList<PPlanner> getProjectList(){ //--------------------------------------------------
        ObservableList<PPlanner> plannerList = FXCollections.observableArrayList();
        //Connection conn = getConnection();
        Connection conn = Con.sqliteCon("ProjectPlanner");
        String query = "select * from projectInfo";
        Statement st = null; //Use prepare statement for repetitive scripts
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            PPlanner pplanner;
            while(rs.next()) {
                pplanner = new PPlanner(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("status"), rs.getInt("priority"), rs.getString("dueDate"), rs.getInt("cat"));
                plannerList.add(pplanner);
            }

        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(st !=null) {
                try {
                    st.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }



        return plannerList;
    }

    public void showProject() { //--------------------------------------------------
        ObservableList<PPlanner> list = getProjectList();

        colName.setCellValueFactory(new PropertyValueFactory<PPlanner, String>("name"));
        colDes.setCellValueFactory(new PropertyValueFactory<PPlanner, String>("description"));
        colStatus.setCellValueFactory(new PropertyValueFactory<PPlanner, Integer>("status"));
        colPri.setCellValueFactory(new PropertyValueFactory<PPlanner, Integer>("priority"));
        colDue.setCellValueFactory(new PropertyValueFactory<PPlanner, String>("dueDate"));
        colCat.setCellValueFactory(new PropertyValueFactory<PPlanner, Integer>("cat"));

        tbPPlanner.setItems(list);

    }
    //left here, finish checking statements and fixing syntax//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void insertRecord() {
        String query = "INSERT INTO projectInfo (name,description,status,priority,dueDate,Cat) VALUES ('"+ tfName.getText() + "','" + tfDes.getText() + "'," + tfStatus.getText() + "," + tfPri.getText() + ",'" + tfDueDate.getText() + "',"+ tfCat.getText() + ")";
        executeQuery(query);
        showProject();
    }

    private void updateRecord() {
        String query = "UPDATE projectInfo SET name = '" + tfName.getText() + "', description = '" + tfDes.getText() + "', status = " + tfStatus.getText() + ", priority = " + tfPri.getText() + ", dueDate = '" + tfDueDate.getText() + "', cat = " + tfCat.getText() + " WHERE name = '" + tfName.getText() + "'";
        executeQuery(query);
        showProject();
    }

    private void deleteRecord() {
        String query = "DELETE FROM projectInfo WHERE name = '" + tfName.getText() + "'";
        executeQuery(query);
        showProject();
    }

    private void executeQuery(String query) {
        //Connection conn = getConnection();
        Connection conn = Con.sqliteCon("ProjectPlanner");
        Statement st = null;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();



        }finally {
            /*if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }*/
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void onMouseClickedPlanner(MouseEvent mouseEvent) { //--------------------------------------------------
        PPlanner pplanner = tbPPlanner.getSelectionModel().getSelectedItem();
        //System.out.println("id: " + pplanner.getId() + "  " + "title: " + pplanner.getName());
        //tfName.setText(String.valueOf(pplanner.getId()));
        tfName.setText(pplanner.getName());
        tfDes.setText(pplanner.getDescription());
        tfStatus.setText(String.valueOf(pplanner.getStatus()));
        tfPri.setText(String.valueOf(pplanner.getPriority()));
        tfDueDate.setText(pplanner.getDueDate());
        tfCat.setText(String.valueOf(pplanner.getCat()));

    }

    public void ppSearchKeyReleased(KeyEvent keyEvent) { // Work on later
        String ser = "'%" + tfpSearch.getText() + "%'";
        String selection = (String)cbSearch.getSelectionModel().getSelectedItem();


        if(tfpSearch.getText() != "") {
            ObservableList<PPlanner> plannerList = FXCollections.observableArrayList();
            String query = "select * from projectInfo WHERE " + selection + " LIKE " + ser;
            System.out.println(query);
            //executeQuery(query);
            //showProject();
            //Connection conn = getConnection();
            Connection conn = Con.sqliteCon("ProjectPlanner");

            Statement st = null; //Use prepare statement for repetitive scripts
            ResultSet rs = null;
            try{
                st = conn.createStatement();
                rs = st.executeQuery(query);
                PPlanner pplanner;
                while(rs.next()) {
                    pplanner = new PPlanner(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("status"), rs.getInt("priority"), rs.getString("dueDate"), rs.getInt("cat"));
                    plannerList.add(pplanner);
                }

                colName.setCellValueFactory(new PropertyValueFactory<PPlanner, String>("name"));
                colDes.setCellValueFactory(new PropertyValueFactory<PPlanner, String>("description"));
                colStatus.setCellValueFactory(new PropertyValueFactory<PPlanner, Integer>("status"));
                colPri.setCellValueFactory(new PropertyValueFactory<PPlanner, Integer>("priority"));
                colDue.setCellValueFactory(new PropertyValueFactory<PPlanner, String>("dueDate"));
                colCat.setCellValueFactory(new PropertyValueFactory<PPlanner, Integer>("cat"));

                tbPPlanner.setItems(plannerList);


            }catch(Exception e) {
                e.printStackTrace();
            }

        } else {
            showProject();
        }


    }


    private void styleRowColor() {
        /*
        int i = 0;
        for (Node n: tbPPlanner.lookupAll("TableRow")) {
            if (n instanceof TableRow) {
                TableRow row = (TableRow) n;
                if (tbPPlanner.getItems().get(i).getWillPay()) {
                    row.getStyleClass().add("willPayRow");
                    row.setDisable(false);
                } else {
                    row.getStyleClass().add("wontPayRow");
                    row.setDisable(true);
                }
                i++;
                if (i == tbPPlanner.getItems().size())
                    break;
            }
        }

         */

    }

    /*
    \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    -------------------------------------------------------------------------------Project Planner-----------------------------------------------------------------------
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */

}

/*
MySQL Syntax:
    conn = DriverManager.getConnection("jdbc:mysql://www.neopect.heliohost.org:3306/neopect_test", "neopect_neo", "TyPass01");
    "SELECT * FROM books"
 */