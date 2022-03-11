/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.commentaire;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mounira.controller.commentaire.CommentaireController;
import mounira.controller.sou_Commentaire.ScommentaireController;
import mounira.entite.Commentaire;
import mounira.service.commentaires.CommentaireService;
import mounira.service.scommentaire.scommentaireService;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class CommentController implements Initializable {

    CommentaireService comser = new CommentaireService();
    scommentaireService scmser =new scommentaireService();

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
    private Label labemnblike;
    public static Label labelnblike;

    @FXML
    private Label labelnbdislike;
    public static Label labelnbdisslike;
    @FXML
    private Label labeldate;
    public static  Label labeldate2;
    @FXML
    private Label nbscom;
    public static  Label nbscom2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textStatic = text;
        idStaitc = id;
        APStatic = AP;
        labelnbdisslike = labelnbdislike;
        labelnblike = labemnblike;
        labeldate2=labeldate;
        nbscom2=nbscom;
        textStatic.setEditable(false);
    }

    @FXML
    private void delete(ActionEvent event) {
        Commentaire commentaire = new Commentaire();
        commentaire = comser.displayById(Integer.parseInt(id.getText()));
        if (commentaire.getUser_id() == CommentaireController.iduser) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirmation");
            alert2.setHeaderText("Voullez-vous supprimer cet commentaire ?");
            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == ButtonType.OK) {

                comser.delete(commentaire);

                //comser.setnbcom(CommentsController.idsubject, nbsujet);
                //labelnbcom.setText(Integer.toString(st.getnbcom(idsujet)));
                Notifications notificationbuilder;
                notificationbuilder = Notifications.create()
                        .title("Alert").text("Commentaire supprimé avec succes !").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
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
            Parent root = FXMLLoader.load(getClass().getResource("/mounira/view/commentaires/commentaire.fxml"));
            id.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void edit(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mounira/view/commentaires/modifierCommentaire.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Commentaire com = new Commentaire();
        com = comser.displayById(Integer.parseInt(id.getText()));

        if (com.getUser_id() == CommentaireController.iduser) {
            ModifierCommentaireController modifierCommentaireController = loader.getController();
            modifierCommentaireController.setTextField(com);
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding(event2 -> {
                try {
                    Parent parent2 = FXMLLoader.load(getClass().getResource("/mounira/view/commentaires/commentaire.fxml"));
                    Scene scene = new Scene(parent2);
                    Stage stage2 = new Stage();
                    stage2.setScene(scene);
                    stage2.initStyle(StageStyle.UTILITY);
                    stage2.show();

                } catch (IOException ex) {
                    Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        }

    }

    @FXML
    private void editAction(KeyEvent event) {
    }

    @FXML
    private void like(MouseEvent event) {
        Commentaire commentaire = new Commentaire();
        commentaire = comser.displayById(Integer.parseInt(id.getText()));
        labemnblike.setText(Integer.toString(comser.getnblike(commentaire.getCommentaire_id())));
        labelnbdislike.setText(Integer.toString(comser.getnbdislike(commentaire.getCommentaire_id())));
        int check = comser.checklikeuser(CommentaireController.iduser, commentaire.getCommentaire_id());
        if (check == 0) {
            if (comser.checkdislikeuser(CommentaireController.iduser, commentaire.getCommentaire_id()) == 0) {
                comser.addlike(CommentaireController.iduser, commentaire.getCommentaire_id());
                int nblike = comser.getnblike(commentaire.getCommentaire_id());
                nblike++;
                comser.setlike(commentaire.getCommentaire_id(), nblike);
            } else {
                comser.addlike(CommentaireController.iduser, commentaire.getCommentaire_id());
                int nblike = comser.getnblike(commentaire.getCommentaire_id());
                nblike++;
                comser.setlike(commentaire.getCommentaire_id(), nblike);
                int nbdislike = comser.getnbdislike(commentaire.getCommentaire_id());
                nbdislike--;
                comser.setdislike(commentaire.getCommentaire_id(), nbdislike);
                //**********
                comser.deletedislike(comser.checkdislikeuser(CommentaireController.iduser, commentaire.getCommentaire_id()));
                System.out.println(commentaire.getCommentaire_id());

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Like");
            alert.setHeaderText("You already liked this comment ! ");
            alert.setContentText(" Done!");
            alert.show();
        }

        labemnblike.setText(Integer.toString(comser.getnblike(commentaire.getCommentaire_id())));
        labelnbdislike.setText(Integer.toString(comser.getnbdislike(commentaire.getCommentaire_id())));
    }

    @FXML
    private void dislike(MouseEvent event) {
        Commentaire commentaire = new Commentaire();
        commentaire = comser.displayById(Integer.parseInt(id.getText()));
        labemnblike.setText(Integer.toString(comser.getnblike(commentaire.getCommentaire_id())));
        labelnbdislike.setText(Integer.toString(comser.getnbdislike(commentaire.getCommentaire_id())));
        if (comser.checkdislikeuser(CommentaireController.iduser, commentaire.getCommentaire_id()) == 0) {
            if (comser.checklikeuser(CommentaireController.iduser, commentaire.getCommentaire_id()) == 0) {
                comser.adddislike(CommentaireController.iduser, commentaire.getCommentaire_id());
                int nbdislike = comser.getnbdislike(commentaire.getCommentaire_id());
                nbdislike++;
                comser.setdislike(commentaire.getCommentaire_id(), nbdislike);

            } else {
                comser.adddislike(CommentaireController.iduser, commentaire.getCommentaire_id());
                int nbdislike = comser.getnbdislike(commentaire.getCommentaire_id());
                nbdislike++;
                comser.setdislike(commentaire.getCommentaire_id(), nbdislike);
                int nblike = comser.getnblike(commentaire.getCommentaire_id());
                nblike--;
                comser.setlike(commentaire.getCommentaire_id(), nblike);
                //***********
                comser.deletelike(comser.checklikeuser(CommentaireController.iduser, commentaire.getCommentaire_id()));

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Like");
            alert.setHeaderText("You already disliked this comment !");
            alert.setContentText(" Done!");
            alert.show();
        }
        labemnblike.setText(Integer.toString(comser.getnblike(commentaire.getCommentaire_id())));
        labelnbdislike.setText(Integer.toString(comser.getnbdislike(commentaire.getCommentaire_id())));
    }

    public static void setrefresh(boolean t) {
        refresh = t;

    }

    @FXML
    private void moveScom(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader();

        Commentaire com = new Commentaire();
        com = comser.displayById(Integer.parseInt(id.getText()));
        ScommentaireController.idcommentaire = com.getCommentaire_id();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        loader.setLocation(getClass().getResource("/mounira/view/scommentaire/scommentaire.fxml"));
        try {
            loader.load();

        } catch (IOException ex) {
            Logger.getLogger(CommentaireController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        Parent parent = loader.getRoot();
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.initStyle(StageStyle.UTILITY);
        stage2.show();
         new animatefx.animation.ZoomIn(parent).play();

    }

}
