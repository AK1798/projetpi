/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.evenement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mounira.controller.user.MainFXMLController;
import mounira.entite.evenement;
import mounira.service.evenement.EvenementService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class EvenementClientController implements Initializable {

    @FXML
    private MenuBar menu;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private RadioButton radtous;
    @FXML
    private ToggleGroup groupby;
    @FXML
    private RadioButton radvegan;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private VBox ListLayout;

    /**
     * Initializes the controller class.
     */
    public List<evenement> listEevents() {
        EvenementService evenementService = new EvenementService();
        return new ArrayList<>(evenementService.readEvent());
    }

    public void Display(List<evenement> evenements) {
        for (evenement even : evenements) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mounira/view/evenement/EventSample.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                EventSampleController eventSampleController = fxmlLoader.getController();
                eventSampleController.setData(even);
                ListLayout.getChildren().add(hBox);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void clear(List<evenement> evenements) {
        for (evenement even : evenements) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mounira/view/evenement/EventSample.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                EventSampleController eventSampleController = fxmlLoader.getController();
                eventSampleController.setData(even);
                ListLayout.getChildren().clear();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Display(listEevents());
    }

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
        GotoFXML("/mounira/view/user/MainClientFXML", "ForU", event);
    }

}
