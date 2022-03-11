package mounira.controller.evenement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mounira.entite.evenement;
import mounira.entite.user;
import mounira.service.UserService;
import mounira.service.evenement.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static mounira.controller.evenement.UpdateEventController.*;
import static mounira.controller.evenement.DeleteEventController.*;

public class EventSampleController implements Initializable {


    @FXML
    private Label Lcategorie;

    @FXML
    private Label Ldate;

    @FXML
    private Label Lnom;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label nbrp;
    @FXML
    private Label Ldescription;


    public void setData(evenement even){
        Lnom.setText(even.getNom());
        Lcategorie.setText(even.getCategorie());
        Ldate.setText(even.getDate().toString());
        nbrp.setText(String.valueOf(even.getNbr_personnes()));
        Ldescription.setText(even.getDescription());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.setOnAction(event -> {
            try {
                EvenementService evenementService=new EvenementService();
                eventPasedUpdate=evenementService.getEventByName(Lnom.getText());
                //System.out.println(userPassed);
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/evenement/UpdateEvent.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
                stage.setOnHidden(event1 -> {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/mounira/view/evenement/Evenement2.fxml"));
                        Lnom.getScene().setRoot(root);
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
                EvenementService evenementService=new EvenementService();
                eventPassedDelete=evenementService.getEventByName(Lnom.getText());
                //System.out.println(userPassed);
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/evenement/DeleteEvent.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
                stage.setOnHidden(event1 -> {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/mounira/view/evenement/Evenement2.fxml"));
                        Lnom.getScene().setRoot(root);
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
