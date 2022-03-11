package mounira.controller.evenement;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mounira.entite.evenement;
import mounira.service.evenement.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {
    @FXML
    private Button btncreate;

    @FXML
    private ComboBox<String> cbcatego;

    @FXML
    private ComboBox<Integer> cbnbper;

    @FXML
    private DatePicker datecb;

    @FXML
    private TextField tfdescrip;

    @FXML
    private TextField tfnom;
    public void init() {
        tfnom.clear();
        tfdescrip.clear();
        datecb.setValue(null);
        cbcatego.setValue(null);
        cbnbper.setValue(null);
    }

    @FXML
    void CreateEvent(ActionEvent event) {
        EvenementService ev = new EvenementService();
        String nom = tfnom.getText();
        Date d = Date.valueOf(datecb.getValue());
        Integer nb_personne = cbnbper.getSelectionModel().getSelectedItem();
        String categorie = cbcatego.getSelectionModel().getSelectedItem();
        String description = tfdescrip.getText();
        evenement e1 = new evenement(nom, d, nb_personne, categorie, description);
        String path =ev.QR(nom);
        System.out.println(path);
        ev.ajouterEventPst(e1);
        init();
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/evenement/AddEvent.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbnbper.setItems((FXCollections.observableArrayList(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)));
        cbcatego.setItems(FXCollections.observableArrayList("Vegan", "Non Vegan", "Both"));
    }
}
