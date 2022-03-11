/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.menu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mounira.entite.Menu;
import mounira.service.menu.MenuService;
import mounira.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class AjouterMenuController implements Initializable {

    private Connection conn;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Menu> data;
    @FXML
    private TextField txt_description;
    @FXML
    private Button btn_addMenu;
    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_ingredients;
    @FXML
    private Button btnBrowser;
    @FXML
    private Label error_titre;
    @FXML
    private Label error_description;
    @FXML
    private Label error_prix;
    @FXML
    private Label error_categorie;
    @FXML
    private ImageView imageView;
    private Image image;

    @FXML
    private Label error_image;
    @FXML
    private Label error_ingredients;
    @FXML
    private ComboBox<String> txt_categorie;
    @FXML
    private AnchorPane anchorPane;
    Alert alertError = new Alert(Alert.AlertType.ERROR);
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    Alert alertCon = new Alert(Alert.AlertType.CONFIRMATION);
    private FileChooser fileChooser;
    private File file;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance().getCnx();
        data = FXCollections.observableArrayList();
        txt_categorie.getItems().addAll("Vegan", "Normal");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("image", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        );

    }
    MenuService ms = new MenuService();

    @FXML
    private void handleAddMenu(ActionEvent event) {
        boolean isTitreString = mounira.validation.TextFieldValidation.isTextFieldTypeString(txt_titre, error_titre, "Le titre doit être une chaine de caractères");
        boolean isDescriptionString = mounira.validation.TextFieldValidation.isTextFieldTypeString(txt_description, error_description, "La description doit être une chaine de caractères");
        boolean isPrixNumber = mounira.validation.TextFieldValidation.isTextFieldTypeNumber(txt_prix, error_prix, "Le prix doit être un nombre");
        boolean isIngredientsString = mounira.validation.TextFieldValidation.isTextFieldTypeString(txt_ingredients, error_ingredients, "Les ingredients doit être une chaine de caractères");
        boolean isCategorieEmpty = mounira.validation.ComboBoxValidation.isComboBoxNotEmpty(txt_categorie, error_categorie, "La catégorie est requis");
        boolean isImageViewEmpty = mounira.validation.ImageViewValidation.isImageViewEmpty(imageView, error_image, "Vous devez sélectionner une image");
        if (isTitreString && isDescriptionString && isPrixNumber && isIngredientsString && isCategorieEmpty && isImageViewEmpty) {

            String titre = txt_titre.getText();
            String description = txt_description.getText();
            float prix = Float.valueOf(txt_prix.getText());
            String ingredients = txt_ingredients.getText();
            String categorie = (String) txt_categorie.getValue();
            String imageIn = file.getAbsolutePath();

            Menu m = new Menu(titre, description, prix, ingredients, categorie, imageIn);
            if (ms.ajouterMenu(m)) {
                alertInfo.setTitle("Info");
                alertInfo.setHeaderText("Message");
                alertInfo.setContentText("Menu ajouté avec succès");
                alertInfo.showAndWait();
                try {
                    Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/menu/AdminMenu2.fxml")));
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                alertError.setTitle("Error");
                alertError.setHeaderText("Message");
                alertError.setContentText("Le menu " + titre + " existe déjà");
                alertError.showAndWait();
            }
        }
    }

    @FXML
    private void handleBtnBrowser(ActionEvent event) {
        stage = (Stage) anchorPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            //System.out.println(getFilePath());
            image = new Image(file.getAbsoluteFile().toURI().toString(),
                    imageView.getFitWidth(), imageView.getFitHeight(), true, true);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
        }
    }

}
