/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.commentaire;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import mounira.service.commentaires.CommentaireService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class CommentaireController implements Initializable {

    CommentaireService comser = new CommentaireService();
    public static int iduser=14;
    @FXML
    private FontAwesomeIconView backbtn;
    @FXML
    private JFXTextField tfsearch;
    @FXML
    private FlowPane FP;
    @FXML
    private FontAwesomeIconView addbtn;
    @FXML
    private JFXTextArea titleta;
    @FXML
    private JFXTextArea descta;
    @FXML
    private Label nbsub;
    @FXML
    private JFXTextArea dateta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initializecommentaires();
        backbtn.setOnMouseClicked(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/mounira/view/user/MainClientFXML.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      /*  tfsearch.textProperty().addListener((obj, old, ne) -> {
            if (ne != null) {

                initializesubjectsByTitle(ne);
                initializesubjectsBydesc(ne);

            } else {

                initializesubjects();
            }
        });*/
    }

    @FXML
    private void ajoutercom(MouseEvent event) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("/mounira/view/commentaires/AjouterCommentaire.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding(event2 -> {
                try {
                    Parent parent2 = FXMLLoader.load(getClass().getResource("/mounira/view/commentaires/commentaire.fxml"));
                    Stage stage3 = (Stage) addbtn.getScene().getWindow();
                    stage3.close();
                    Scene scene2 = new Scene(parent2);
                    Stage stage2 = new Stage();
                    stage2.setScene(scene2);
                    stage2.initStyle(StageStyle.UTILITY);
                    stage2.show();

                } catch (IOException ex) {
                    Logger.getLogger(AjouterCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refreshTable(MouseEvent event) {
        initializecommentaires();
    }

    public void initializecommentaires() {

        FP.getChildren().clear();

        int count = comser.displayAll().size();
        System.out.println(count);

        for (int i = 0; i < count; i++) {
            try {
                Parent commentFXML = FXMLLoader.load(getClass().getResource("/mounira/view/commentaires/com.fxml"));
                CommentController.textStatic.setText(comser.displayAll().get(i).getCommentaire_desc());
                CommentController.idStaitc.setText(comser.displayAll().get(i).getCommentaire_id() + "");
                CommentController.labelnbdisslike.setText(Integer.toString(comser.displayAll().get(i).getNbdislike()));
                CommentController.labelnblike.setText(Integer.toString(comser.displayAll().get(i).getNblike()));

                CommentController.labeldate2.setText(comser.displayAll().get(i).getCommentaire_date());
                CommentController.nbscom2.setText(Integer.toString(comser.displayAll().get(i).getNbscom()));
                CommentController.textStatic.setEditable(false);

                FP.getChildren().add(commentFXML);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

  /*  public void initializesubjectsByTitle(String s) {

        FP.getChildren().clear();

        int count = ss.SearchTitleDynamic(s).size();
        System.out.println(count);

        for (int i = 0; i < count; i++) {
            try {
                Parent commentFXML = FXMLLoader.load(getClass().getResource("/allintravelfinal/view/subject.fxml"));
                SubjectController.titlesubject2.setText(ss.SearchTitleDynamic(s).get(i).getSubject_title());
                SubjectController.id2.setText(ss.SearchTitleDynamic(s).get(i).getSubject_Id() + "");
                SubjectController.descriptionsubject2.setText(ss.SearchTitleDynamic(s).get(i).getSubject_description());
                try {
                    String path = ss.SearchTitleDynamic(s).get(i).getSubject_image();
                    if (path != null) {
                        InputStream stream = new FileInputStream(path);
                        Image image = new Image(stream);
                        SubjectController.image2.setImage(image);
                    } else {
                        System.out.println("no image");
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("no changes ");
                }
                SubjectController.datesubject2.setText(ss.SearchTitleDynamic(s).get(i).getSubject_date());
                SubjectController.nbcomsubject2.setText(Integer.toString(ss.SearchTitleDynamic(s).get(i).getSubject_num_comments()));
                SubjectController.titlesubject2.setEditable(false);
                SubjectController.id2.setVisible(false);
                SubjectController.descriptionsubject2.setEditable(false);

                FP.getChildren().add(commentFXML);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }*/

}
