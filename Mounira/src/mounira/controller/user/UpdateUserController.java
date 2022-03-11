package mounira.controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mounira.entite.user;
import mounira.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class UpdateUserController implements Initializable {

    @FXML
    private Button btncreate;

    @FXML
    private ComboBox<String> cbrole;

    @FXML
    private DatePicker date;

    @FXML
    private TextField tfadresse;

    @FXML
    private PasswordField tfconfirmpass;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfnom;

    @FXML
    private PasswordField tfpassword;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tftelephone;

    public static user userPassed;

    public void init() {
        tfnom.clear();
        tfprenom.clear();
        tfemail.clear();
        tfpassword.clear();
        tfconfirmpass.clear();
        tftelephone.clear();
        tfadresse.clear();
        date.setValue(null);
        cbrole.setValue(null);
    }
    public void setItems(){
        System.out.println(userPassed.getNom());
       tfnom.setText(userPassed.getNom());
        tfprenom.setText(userPassed.getPrenom());
        tfemail.setText(userPassed.getEmail());
        tfadresse.setText(userPassed.getAdresse());
        date.setAccessibleText(userPassed.getDate().toString());
        cbrole.setValue(userPassed.getRole());
        tftelephone.setText(String.valueOf(userPassed.getNum_tel()));
        tfpassword.setText(userPassed.getPassword());
    }
    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    void UpdateUser(ActionEvent event) {
        UserService us =new UserService();
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String email = tfemail.getText();
        String password = tfpassword.getText();
        String confirmPass = tfconfirmpass.getText();
        Date d = Date.valueOf(date.getValue());
        int tel = Integer.valueOf(tftelephone.getText());
        String adresse = tfadresse.getText();
        String role = cbrole.getSelectionModel().getSelectedItem();
        userPassed.setNom(nom);
        userPassed.setPrenom(prenom);
        userPassed.setEmail(email);
        userPassed.setPassword(password);
        userPassed.setDate(d);
        userPassed.setNum_tel(tel);
        userPassed.setAdresse(adresse);
        userPassed.setRole(role);
        if (us.modifierUserPst(userPassed)) {
            AlertWindow("Modifier " + role, role + " modifié avec succés", Alert.AlertType.INFORMATION);
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
            AlertWindow("Modifier " + role, "Echec de modificaition", Alert.AlertType.ERROR);
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
        init();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setItems();
    }
}
