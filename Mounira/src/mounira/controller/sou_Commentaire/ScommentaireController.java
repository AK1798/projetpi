/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.sou_Commentaire;

import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mounira.controller.commentaire.AjouterCommentaireController;
import mounira.controller.commentaire.CommentaireController;
import mounira.entite.Commentaire;
import mounira.entite.scommentaire;
import mounira.service.commentaires.CommentaireService;
import mounira.service.scommentaire.scommentaireService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class ScommentaireController implements Initializable {

    public static int iduser = 14;
    scommentaireService scmser = new scommentaireService();
    CommentaireService comser = new CommentaireService();
    public static int idcommentaire;

    @FXML
    private FlowPane FP;
    @FXML
    private FontAwesomeIconView addbtn;
    @FXML
    private JFXTextArea inputdescript;
    @FXML
    private Label labelnbcom;
    public static Label labelcom;

    @FXML
    private JFXTextArea inputdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Commentaire com = comser.displayById(idcommentaire);

        inputdescript.setText(com.getCommentaire_desc());
        inputdate.setText(com.getCommentaire_date());
        labelnbcom.setText(Integer.toString(comser.getnbscom(idcommentaire)));
        initializecommentaires();
        labelcom = labelnbcom;
        inputdescript.setEditable(false);
        inputdate.setEditable(false);

    }

    @FXML
    private void back(MouseEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/mounira/view/commentaires/commentaire.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AddNoteAction(MouseEvent event) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("/mounira/view/scommentaire/AjouterScommentaire.fxml"));
            AjouterScommentaireController.idcommentaire = idcommentaire;

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            System.out.println(idcommentaire);
            initializecommentaires();
            stage.setOnHiding(event2 -> {
                try {
                    Parent parent2 = FXMLLoader.load(getClass().getResource("/mounira/view/scommentaire/scommentaire.fxml"));
                    Stage stage3 = (Stage) addbtn.getScene().getWindow();
                    stage3.close();
                    Scene scene2 = new Scene(parent2);
                    Stage stage2 = new Stage();
                    stage2.setScene(scene2);
                    stage2.initStyle(StageStyle.UTILITY);
                    stage2.show();

                } catch (IOException ex) {
                    Logger.getLogger(ScommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            labelnbcom.setText(Integer.toString(comser.getnbscom(idcommentaire)));
            System.gc();
        } catch (IOException ex) {
            Logger.getLogger(ScommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(MouseEvent event) {
        initializecommentaires();
        labelnbcom.setText(Integer.toString(comser.getnbscom(idcommentaire)));
        System.gc();
    }

    public void initializecommentaires() {

        FP.getChildren().clear();

        int count = scmser.DisplayByComID(idcommentaire).size();
        System.out.println(count);
        System.out.println(idcommentaire);
        for (int i = 0; i < count; i++) {
            try {
                Parent commentFXML = FXMLLoader.load(getClass().getResource("/mounira/view/scommentaire/scom.fxml"));
                ScomController.textStatic.setText(scmser.DisplayByComID(idcommentaire).get(i).getScommentaire_desc());
                ScomController.idStaitc.setText(scmser.DisplayByComID(idcommentaire).get(i).getScommentaire_id() + "");
                ScomController.labeldate2.setText(scmser.DisplayByComID(idcommentaire).get(i).getScommentaire_date());

                labelnbcom.setText(Integer.toString(comser.getnbscom(idcommentaire)));
                ScomController.textStatic.setEditable(false);

                FP.getChildren().add(commentFXML);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }

}
