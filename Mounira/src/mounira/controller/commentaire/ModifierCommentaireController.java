/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.commentaire;

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
import mounira.service.commentaires.CommentaireService;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class ModifierCommentaireController implements Initializable {

    CommentaireService comser = new CommentaireService();

    @FXML
    private JFXTextField inputcont;
    @FXML
    private JFXButton updatebtn;
    private int idcom;

  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
  void setTextField(Commentaire c) {

        idcom = c.getCommentaire_id();
        inputcont.setText(c.getCommentaire_desc());

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
            Commentaire c = new Commentaire();
            c.setCommentaire_desc(cont);
            c.setCommentaire_date(dateFormat.format(date));
            c.setCommentaire_id(idcom);

            if (comser.update(c)) {
                Stage stage = (Stage) updatebtn.getScene().getWindow();
                stage.close();

                Notifications notificationbuilder;
                notificationbuilder = Notifications.create()
                        .title("Alert").text("Commentaire modifier avec success !").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
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
    private void clean(MouseEvent event) {
        inputcont.clear();
    }

}
