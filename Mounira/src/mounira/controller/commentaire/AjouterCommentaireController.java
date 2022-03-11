/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.commentaire;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import static mounira.utils.BadWords.chackwords;
import mounira.utils.Mailling;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class AjouterCommentaireController implements Initializable {

    CommentaireService comser = new CommentaireService();
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXTextField inputcont;
    public static int idcommentaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("this is id " + idcommentaire);
    }

    @FXML
    private void save(MouseEvent event) throws IOException {

        String cont = inputcont.getText();

        if (cont.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            if (chackwords(cont).equals("false")) {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Commentaire c = new Commentaire();
                c.setCommentaire_desc(cont);
                c.setCommentaire_date(dateFormat.format(date));
                c.setUser_id(CommentaireController.iduser);
                c.setCommentaire_id(idcommentaire);
                if (comser.insert(c)) {
                    Stage stage = (Stage) addbtn.getScene().getWindow();
                    stage.close();
                    Notifications notificationbuilder;
                    notificationbuilder = Notifications.create()
                            .title("Alert").text("Commentaire ajouter avec success!").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("clicked on");
                                }
                            });
                    notificationbuilder.darkStyle();
                    notificationbuilder.showInformation();
                    clean();

                } else {
                    System.err.println("System tray not supported!");
                }

            }else {
                clean();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Worning !! ");
                alert.setContentText("Your Topic cannot be accepted ! Please stay on your limit ! ");
                alert.show();
                Mailling.envoyer();
            }
        }
    }

        @FXML
        private void clean
        
            () {
        inputcont.clear();
        }

    }
