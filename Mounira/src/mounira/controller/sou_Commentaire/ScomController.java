/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.sou_Commentaire;

import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mounira.entite.scommentaire;
import mounira.service.commentaires.CommentaireService;
import mounira.service.scommentaire.scommentaireService;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class ScomController implements Initializable {

    scommentaireService scoms = new scommentaireService();
    @FXML
    private AnchorPane AP;
    public static AnchorPane APStatic;

    @FXML
    private Label id;
    public static Label idStaitc;

    @FXML
    private JFXTextArea text;
    public static JFXTextArea textStatic;
    public static boolean refresh = false;

    @FXML
    private Label labeldate;
    public static Label labeldate2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textStatic = text;
        idStaitc = id;
        APStatic = AP;
        textStatic.setEditable(false);
        labeldate2=labeldate;

    }

    @FXML
    private void delete(ActionEvent event) {
        scommentaire scomentaire = new scommentaire();
        scomentaire = scoms.displayById(Integer.parseInt(id.getText()));
        if (scomentaire.getUser_id() == ScommentaireController.iduser) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirmation");
            alert2.setHeaderText("Voullez-vous supprimer cet commentaire ?");
            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == ButtonType.OK) {
                CommentaireService ss = new CommentaireService();
                scoms.delete(scomentaire);
                int nbscom = ss.getnbscom(ScommentaireController.idcommentaire);
                nbscom--;
                ss.setnbscom(ScommentaireController.idcommentaire, nbscom);
                //labelnbcom.setText(Integer.toString(st.getnbcom(idsujet)));
                Notifications notificationbuilder;
                notificationbuilder = Notifications.create()
                        .title("Alert").text("Commentaire supprimé avec success  !").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("clicked on");
                            }
                        });
                notificationbuilder.darkStyle();
                notificationbuilder.showInformation();

            } else {
                alert2.close();
            }

        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/mounira/view/scommentaire/scommentaire.fxml"));
            id.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void edit(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mounira/view/scommentaire/modifierScommentaire.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ScommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        scommentaire scomentaire = new scommentaire();
        scomentaire = scoms.displayById(Integer.parseInt(id.getText()));

        if (scomentaire.getUser_id() == ScommentaireController.iduser) {
            ModifierScommentaireController modifierScommentaireController = loader.getController();
            modifierScommentaireController.setTextField(scomentaire);
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding(event2 -> {
                try {
                    Parent parent2 = FXMLLoader.load(getClass().getResource("/mounira/view/scommentaire/scommentaire.fxml"));
                    Scene scene = new Scene(parent2);
                    Stage stage2 = new Stage();
                    stage2.setScene(scene);
                    stage2.initStyle(StageStyle.UTILITY);
                    stage2.show();

                } catch (IOException ex) {
                    Logger.getLogger(ScommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        }
    }

    @FXML
    private void editAction(KeyEvent event) {
    }

    public static void setrefresh(boolean t) {
        refresh = t;

    }
}
