/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.menu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static mounira.controller.evenement.UpdateEventController.eventPasedUpdate;
import static mounira.controller.menu.SupprimerMenuController.menuPassedDelete;
import mounira.entite.Menu;
import mounira.service.evenement.EvenementService;
import mounira.service.menu.MenuService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class ModifierMenuController implements Initializable {
    
    @FXML
    private TextField txt_description;
    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_ingredients;
    @FXML
    private ComboBox<String> txt_categorie;
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
    public static Menu menuPassedModifier;
    @FXML
    private Button btn_ModifMenu;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    @FXML
    private AnchorPane anchorPane;
    
    public void init() {
        txt_titre.clear();
        txt_prix.clear();
        txt_ingredients.clear();
        txt_categorie.setValue(null);
        txt_description.clear();
    }
    
    public void setItems() {
        System.out.println(menuPassedModifier);
        txt_titre.setText(menuPassedModifier.getTitre());
        txt_prix.setText(String.valueOf(menuPassedModifier.getPrix()));
        //datecb.setValue(eventPasedUpdate.getDate());
        txt_categorie.setValue(menuPassedModifier.getCategorie());
        txt_ingredients.setText(menuPassedModifier.getIngredients());
        txt_description.setText(menuPassedModifier.getDescription());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_categorie.setItems(FXCollections.observableArrayList("Vegan", "Normal"));
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("image", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        );
        setItems();
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
    
    @FXML
    private void handleModifierMenu(ActionEvent event) {
        MenuService ms = new MenuService();
        String title = txt_titre.getText();
        float prix = Integer.valueOf(txt_prix.getText());
        String Categorie = txt_categorie.getSelectionModel().getSelectedItem();
        String ingred = txt_ingredients.getText();
        String desc = txt_description.getText();
        
        menuPassedModifier.setTitre(title);
        menuPassedModifier.setPrix(prix);
        menuPassedModifier.setDescription(desc);
        menuPassedModifier.setIngredients(ingred);
        menuPassedModifier.setCategorie(Categorie);
        menuPassedModifier.setImage(file.getAbsolutePath());
        ms.modifierMenu(menuPassedModifier);
        init();
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/menu/AdminMenu2.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
