package mounira.controller.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mounira.entite.user;
import mounira.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeleteUserController implements Initializable {
    public static user userDeletePassed;

    @FXML
    private Button btnNon;

    @FXML
    private Button btnOui;


    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnNon.setOnAction(event -> {
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/user/DeleteUser.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnOui.setOnAction(event -> {
            UserService us =new UserService();
            if (us.suppUserPst(userDeletePassed)) {
                AlertWindow("Supprimer " + userDeletePassed.getRole(), userDeletePassed.getRole() + " supprimé avec succés", Alert.AlertType.INFORMATION);
                try {
                    Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/user/DeleteUser.fxml")));
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                AlertWindow("Supprimer " + userDeletePassed.getRole(), "Echec de suppression", Alert.AlertType.ERROR);
                try {
                    Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/user/DeleteUser.fxml")));
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
