/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.user;

import mounira.entite.MD5Utils;
import mounira.entite.user;
import mounira.service.UserService;
import static pidev.Pidev.Userconnected;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import mounira.utils.Mailing;
import mounira.utils.Utils;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private CheckBox chkbvoirmdp;
    @FXML
    private Label mdpoublie;
    @FXML
    private Label newaccout;

    /*
     * Initializes the controller class.
     */
    UserService us = new UserService();
    @FXML
    private Button connexion;
    @FXML
    private Button fermer;
    @FXML
    private AnchorPane codeveirform;
    @FXML
    private Button btn_crea_co2;
    @FXML
    private TextField codealpha;
    @FXML
    private AnchorPane doublepasswordform;
    @FXML
    private Button btn_crea_co1;
    @FXML
    private PasswordField resetpasswordvalue1;
    @FXML
    private PasswordField resetpasswordvalue2;
    @FXML
    private AnchorPane resetpasswordform;
    @FXML
    private Button btn_crea_co;
    @FXML
    private TextField emailresetvalue;
    @FXML
    private AnchorPane cnxform;
    private String code;
    private user resetUser;
    public static int iduer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    private void MdpOublie(MouseEvent event) {

    }

    @FXML
    private void resetpassword(MouseEvent event) {
        cnxform.setDisable(true);
        cnxform.setOpacity(0);
        resetpasswordform.setOpacity(1);
        resetpasswordform.setLayoutX(cnxform.getLayoutX());
        resetpasswordform.setLayoutY(cnxform.getLayoutY());
        resetpasswordform.setDisable(false);
    }

    @FXML
    private void gotoREGISTER(MouseEvent event) {
        GotoFXML("/mounira/view/user/RegisterFXML", "Bienvenue", event);
    }

    @FXML
    private void connexion(ActionEvent event) {
        String email = tfemail.getText();
        String mdp = pfpassword.getText();
        Userconnected = us.getUserbyEmailPass(email, mdp);
        if (tfemail.getText().compareTo("") == 0 || pfpassword.getText().compareTo("") == 0) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Un ou plusieurs champs sont vides");
            alert.setHeaderText("Un ou plusieurs champs sont vides");
            alert.setContentText("Un ou plusieurs champs sont vides !");
            alert.showAndWait();

            return;

        } else if (Userconnected.getId() != 0) {
            Userconnected.setId(Userconnected.getId());
            Userconnected.setEmail(Userconnected.getEmail());
            Userconnected.setPassword(Userconnected.getPassword());
            AlertWindow("Connexion avec succées", "Je vous souhaite la bienvenue Mr/Mme " + Userconnected.getNom() + ",\nInterface " + Userconnected.getRole(), Alert.AlertType.INFORMATION);
            if ("Admin".equals(Userconnected.getRole())) {
                GotoFXML("/mounira/view/user/MainFXML", "Dashbord Admin", event);
            } else {
                GotoFXML("/mounira/view/user/MainClientFXML", "ForU", event);
            }
        } else {
            AlertWindow("Connexion echouée", "Email ou mot de pass invalid!!", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void handleCloseButtonAction(MouseEvent event
    ) {
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void verifcode(ActionEvent event) {

        if (codealpha.getText().compareTo(code) != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Code invalide ");
            alert.setHeaderText("Code invalide ");
            alert.setContentText(" ");
            alert.showAndWait();
            return;

        } else {
            codeveirform.setDisable(true);
            codeveirform.setOpacity(0);
            doublepasswordform.setLayoutX(codeveirform.getLayoutX());

            doublepasswordform.setLayoutY(codeveirform.getLayoutY());
            doublepasswordform.setOpacity(1);
            doublepasswordform.setDisable(false);
        }
    }

    @FXML
    private void updatePassword(ActionEvent event) throws SQLException {

        if (resetpasswordvalue1.getText().compareTo("") == 0 || resetpasswordvalue2.getText().compareTo("") == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Problème de vérification de mot de passe");
            alert.setHeaderText("Problème de vérification de mot de passe");
            alert.setContentText("Problème de vérification de mot de passe !");
            alert.showAndWait();

        } else if (resetpasswordvalue1.getText().compareTo(resetpasswordvalue2.getText()) != 0) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Problème de vérification de mot de passe");
            alert.setHeaderText("Problème de vérification de mot de passe");
            alert.setContentText("Les champs mot de passe et répéter le mot de pass doivent etre identiques !");
            alert.showAndWait();
            resetpasswordvalue1.clear();
            resetpasswordvalue2.clear();
        } else {
            UserService us = new UserService();
//            resetUser.setPassword(resetpasswordvalue1.getText());
           // System.out.println(Userconnected.getId());
            us.ResetPassword(resetpasswordvalue1.getText(), iduer);
            //us.modifierUserPst(resetUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ton mot de passe a été changé ");
            alert.setHeaderText("Ton mot de passe a été changé ");
            alert.setContentText("Ton mot de passe a été changé !");
            alert.showAndWait();
            //  tfemail.setText(resetUser.getEmail());
            //resetUser = new user();

            doublepasswordform.setDisable(true);
            doublepasswordform.setOpacity(0);
            cnxform.setDisable(false);
            cnxform.setOpacity(1);
        }

    }

    @FXML
    private void passwordRecovery(ActionEvent event) {

        UserService us = new UserService();
        System.out.println(emailresetvalue.getText());
        System.out.println(us.MailExiste(emailresetvalue.getText()));
        if (us.MailExiste(emailresetvalue.getText()) != null) {
            iduer = us.MailExiste(emailresetvalue.getText()).getId();

            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 7;
            Random random = new Random();

            code = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            String subject = "Le code de vérification :  ";

            String message = "Ton code est le suivant " + code;

            Mailing mailing = new Mailing();
            try {
                mailing.sendMail(emailresetvalue.getText(), subject, message);
                codeveirform.setDisable(false);
                codeveirform.setOpacity(1);
                resetpasswordform.setOpacity(0);
                resetpasswordform.setDisable(true);
                codeveirform.setLayoutX(resetpasswordform.getLayoutX());

                codeveirform.setLayoutY(resetpasswordform.getLayoutY());
                resetUser = us.SearchUserByMail(emailresetvalue.getText());

            } catch (Exception ex) {
                Logger.getLogger(LoginFXMLController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compte introuvable ");
            alert.setHeaderText("Adresse email introuvable");
            alert.setContentText("Merci de faire l'inscription d'abord ! ");
            alert.showAndWait();
            return;
        }

    }

}
