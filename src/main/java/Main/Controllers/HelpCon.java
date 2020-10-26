package Main.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;

import java.io.IOException;

public class HelpCon {

    public void initialize() {


    }

    public void onHlReadMe(ActionEvent actionEvent) throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://github.com/Neopect/Tigris-Auxilium/blob/master/README.md"));

    }

    public void onHlWiki(ActionEvent actionEvent) throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://github.com/Neopect/Tigris-Auxilium/wiki"));
    }

    public void onHlIssue(ActionEvent actionEvent) throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://github.com/Neopect/Tigris-Auxilium/issues"));
    }

    public void onHlEmail(ActionEvent actionEvent) throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("mailto:deb@northpoleneon@gmail.com"));
    }
}

//java.awt.Desktop.getDesktop().browse(java.net.URI.create("www.google.com"));