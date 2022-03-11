package mounira.controller.evenement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mounira.controller.user.UserSampleController;
import mounira.entite.evenement;
import mounira.entite.user;
import mounira.service.UserService;
import mounira.service.evenement.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.Node;
import mounira.controller.user.MainFXMLController;

public class Evenement2Controller implements Initializable {

    @FXML
    private VBox ListLayout;

    @FXML
    private Button btnReturnMenu;

    @FXML
    private Button btncreate;

    @FXML
    private Button btndel;

    @FXML
    private Button btnmodif;

    @FXML
    private ComboBox<?> cbcatego;

    @FXML
    private ComboBox<?> cbnbper;

    @FXML
    private DatePicker datecb;

    @FXML
    private ToggleGroup groupby;

    @FXML
    private ImageView imageviewlogo;

    @FXML
    private MenuBar menu;

    @FXML
    private RadioButton radtous;

    @FXML
    private RadioButton radvegan;

    @FXML
    private TextField tfdescrip;

    @FXML
    private TextField tfnom;

    @FXML
    void CreateEvent(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/evenement/AddEvent.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHidden(event1 -> {
                clear(listEevents());
                Display(listEevents());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    @FXML
    void updateTable(ActionEvent event) {

    }

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
}
