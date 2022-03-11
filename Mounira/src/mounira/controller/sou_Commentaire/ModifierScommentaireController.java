/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.sou_Commentaire;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mounira.entite.Commentaire;
import mounira.entite.scommentaire;
import mounira.service.scommentaire.scommentaireService;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class ModifierScommentaireController implements Initializable {

    scommentaireService scmser = new scommentaireService();

    @FXML
    private JFXTextField inputcont;
    private int idcom;

    @FXML
    private JFXButton updatebtn;

    void setTextField(scommentaire c) {
        idcom = c.getScommentaire_id();
        inputcont.setText(c.getScommentaire_desc());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(MouseEvent event) {
        String cont = inputcont.getText();

        if (cont.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            scommentaire c = new scommentaire();
            c.setScommentaire_desc(cont);
            c.setScommentaire_date(dateFormat.format(date));
            c.setScommentaire_id(idcom);
            c.setUser_id(ScommentaireController.iduser);
            if (scmser.update(c)) {
                Stage stage = (Stage) updatebtn.getScene().getWindow();
                stage.close();

                Notifications notificationbuilder;
                notificationbuilder = Notifications.create()
                        .title("Alert").text("Commentaire modifier avec success ! ").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("clicked on");
                            }
                        });
                notificationbuilder.darkStyle();
                notificationbuilder.showInformation();

            } else {
                System.err.println("System tray not supported!");
            }

        }
    }

    @FXML
    private void clean() {
        inputcont.clear();
    }

}
