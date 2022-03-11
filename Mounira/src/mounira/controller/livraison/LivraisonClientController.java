/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.livraison;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mounira.controller.user.MainFXMLController;
import mounira.entite.livraison;
import mounira.entite.user;
import mounira.service.UserService;
import mounira.service.livraison.LivraisonService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class LivraisonClientController implements Initializable {

    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbtri;
    @FXML
    private TableView<livraison> tablelivraison;
    @FXML
    private TableColumn<livraison, Integer> coluserid;
    @FXML
    private TableColumn<livraison, Integer> collivreurid;
    @FXML
    private TableColumn<livraison, Integer> colcommandeid;
    @FXML
    private TableColumn<livraison, String> colnomlivraison;
    @FXML
    private TableColumn<livraison, String> coletat;
    @FXML
    private Button btntri;
    @FXML
    private ComboBox<String> cbrechpar;
    @FXML
    private MenuBar menu;
    @FXML
    private ImageView imageviewlogo;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private RadioButton radencours;
    @FXML
    private ToggleGroup etat;
    @FXML
    private RadioButton radlivre;
    @FXML
    private RadioButton radtous;
    @FXML
    private RadioButton radannulle;
    UserService us = new UserService();
    LivraisonService ls = new LivraisonService();

    ObservableList<user> Users;
    ObservableList<String> UsersName;
    ObservableList<String> Livs;
    ObservableList<String> nomclient;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();

    }

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
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

    @FXML
    private void SearchUser(KeyEvent event) {
    }

    @FXML
    private void TriLiv(ActionEvent event) {
    }

    @FXML
    private void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("/mounira/view/user/MainClientFXML", "ForU", event);

    }

    @FXML
    private void getEncours(ActionEvent event) {
        ObservableList<livraison> livraison = ls.filterEtat("En cours");
        coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        collivreurid.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colcommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        colnomlivraison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tablelivraison.setItems(livraison);
    }

    @FXML
    private void getLivree(ActionEvent event) {
        ObservableList<livraison> livraison = ls.filterEtat("Livrée");
        coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        collivreurid.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colcommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        colnomlivraison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tablelivraison.setItems(livraison);
    }

    @FXML
    private void updateTable() {
        radtous.setSelected(true);
        ObservableList<livraison> Livraison1 = ls.readLivraison();
        coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        collivreurid.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colcommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        colnomlivraison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tablelivraison.setItems(Livraison1);
    }

    @FXML
    private void getannulee(ActionEvent event) {
        ObservableList<livraison> livraison = ls.filterEtat("Annulé");
        coluserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        collivreurid.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colcommandeid.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        colnomlivraison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tablelivraison.setItems(livraison);
    }

    public void init() {
        updateTable();

    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

}
