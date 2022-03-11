package mounira.controller.evenement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mounira.entite.evenement;
import mounira.service.evenement.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeleteEventController implements Initializable {

    @FXML
    private Button btnNon;

    @FXML
    private Button btnOui;

    public static evenement eventPassedDelete;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnNon.setOnAction(event -> {
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/evenement/DeleteEvent.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnOui.setOnAction(event -> {
            EvenementService ev = new EvenementService();
            ev.suppEvenementPst(eventPassedDelete);
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/evenement/DeleteEvent.fxml")));
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
