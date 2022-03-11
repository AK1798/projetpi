package mounira.controller.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mounira.entite.user;
import mounira.service.UserService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static mounira.controller.user.UpdateUserController.*;
import static mounira.controller.user.DeleteUserController.*;



public class UserSampleController implements Initializable {

    @FXML
    private Label Ladd;

    @FXML
    private Label LdateDeNaissance;

    @FXML
    private Label Lmail;

    @FXML
    private Label Lnom;

    @FXML
    private Label Lprenom;

    @FXML
    private Label Lrole;

    @FXML
    private Label Ltel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    public void setData(user utilisateur){
        Lnom.setText(utilisateur.getNom());
        Lprenom.setText(utilisateur.getPrenom());
        Lmail.setText(utilisateur.getEmail());
        LdateDeNaissance.setText(String.valueOf(utilisateur.getDate()));
        Ltel.setText(String.valueOf(utilisateur.getNum_tel()));
        Ladd.setText(utilisateur.getAdresse());
        Lrole.setText(utilisateur.getRole());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    btnUpdate.setOnAction(event -> {
        try {
            UserService userService=new UserService();
            userPassed=userService.getUserByMail(Lmail.getText());
            //System.out.println(userPassed);
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/user/UpdateUser.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHidden(event1 -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/mounira/view/user/User2.fxml"));
                    Lmail.getScene().setRoot(root);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    btnDelete.setOnAction(event -> {
        try {
            UserService userService=new UserService();
            userDeletePassed=userService.getUserByMail(Lmail.getText());
            //System.out.println(userPassed);
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/user/DeleteUser.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHidden(event1 -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/mounira/view/user/User2.fxml"));
                    Lmail.getScene().setRoot(root);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });
    }
}
