/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.menu;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mounira.entite.Menu;
import mounira.service.menu.MenuService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class SupprimerMenuController implements Initializable {

    @FXML
    private Button btnNon;
    @FXML
    private Button btnOui;
    public static Menu menuPassedDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           btnNon.setOnAction(event -> {
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/menu/SupprimerMenu.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnOui.setOnAction(event -> {
            MenuService ms = new MenuService();
            ms.suppMenu(menuPassedDelete);
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/menu/SupprimerMenu.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
