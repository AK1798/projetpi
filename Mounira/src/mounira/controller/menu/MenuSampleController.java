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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static mounira.controller.menu.SupprimerMenuController.menuPassedDelete;
import static mounira.controller.menu.ModifierMenuController.menuPassedModifier;

import mounira.entite.Menu;
import mounira.service.menu.MenuService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class MenuSampleController implements Initializable {

    @FXML
    private Label Ltitre;
    @FXML
    private Label Ldesc;
    @FXML
    private Label Lprix;
    @FXML
    private Label Lcategorie;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label LIngr;

    public void setData(Menu menu) {
        Ltitre.setText(menu.getTitre());
        Lcategorie.setText(menu.getCategorie());
        Ldesc.setText(menu.getDescription());
        Lprix.setText(String.valueOf(menu.getPrix()));
        Lcategorie.setText(menu.getDescription());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnUpdate.setOnAction(event -> {
            try {
                MenuService menuser = new MenuService();
                menuPassedModifier = menuser.getMenu(Ltitre.getText());
                //System.out.println(userPassed);
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/menu/ModifierMenu.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
                stage.setOnHidden(event1 -> {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/mounira/view/menu/AdminMenu2.fxml"));
                        Ltitre.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnDelete.setOnAction(event -> {
            try {
                MenuService menuser = new MenuService();
                menuPassedDelete = menuser.getMenu(Ltitre.getText());
                //System.out.println(userPassed);
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/menu/SupprimerMenu.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
                stage.setOnHidden(event1 -> {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/mounira/view/menu/AdminMenu2.fxml"));
                        Ltitre.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}


