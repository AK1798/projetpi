/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.menu;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mounira.controller.user.MainFXMLController;
import mounira.entite.Menu;
import mounira.service.evenement.EvenementService;
import mounira.service.menu.MenuService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class AdminMenu2Controller implements Initializable {

    private Connection conn;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Menu> data;
    @FXML
    private VBox ListLayout;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private Button btncreate;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private TextField txt_search;

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("/mounira/view/user/MainFXML", "ForU", event);
    }

    @FXML
    private void AjouterMenu(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/menu/AjouterMenu.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHidden(event1 -> {
                clear(listMenu());
                Display(listMenu());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Menu> listMenu() {
        MenuService menuservice = new MenuService();
        return new ArrayList<>(menuservice.readEvent());
    }

    public List<Menu> listMenuS() {
        MenuService menuservice = new MenuService();
        return new ArrayList<>(menuservice.readMenuS(txt_search.getText()));
    }

    public void Display(List<Menu> menus) {
        for (Menu menu : menus) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mounira/view/menu/MenuSample.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                MenuSampleController menuSampleController = fxmlLoader.getController();
                menuSampleController.setData(menu);
                ListLayout.getChildren().add(hBox);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void DisplaySearch(List<Menu> menus) {
        for (Menu menu : menus) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mounira/view/menu/MenuSample.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                MenuSampleController menuSampleController = fxmlLoader.getController();
                menuSampleController.setData(menu);
                ListLayout.getChildren().add(hBox);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void clear(List<Menu> menus) {
        for (Menu menu : menus) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mounira/view/menu/MenuSample.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                MenuSampleController menuSampleController = fxmlLoader.getController();
                menuSampleController.setData(menu);
                ListLayout.getChildren().clear();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();
        Display(listMenu());
        searchMenu();

    }

    private void searchMenu() {
        txt_search.setOnKeyReleased(e -> {
            if (txt_search.getText().equals("")) {
                clear(listMenuS());
                Display(listMenu());
            } else {
                clear(listMenu());
                DisplaySearch(listMenuS());
            }
        });

    }

}
