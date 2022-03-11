/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.commande;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import mounira.controller.menu.MenuController;
import mounira.entite.Menu;
import mounira.utils.MyListener;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class AdminCommande1Controller implements Initializable {

    @FXML
    private Label lb_titre;
    @FXML
    private Label lb_prix;
    @FXML
    private ImageView menuImage;
    @FXML
    private Text txt_ingredients;
    @FXML
    private Button deleteBtn;
    private MyListener myListener;
    private Menu menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
        myListener.onClickListener(menu);

    }

    public void setData(Menu menu, MyListener myListener) {
        this.myListener = myListener;
        this.menu = menu;
        lb_titre.setText(menu.getTitre());
        lb_prix.setText(String.valueOf(menu.getPrix()) + MenuController.CURRENCY);
        txt_ingredients.setText(menu.getIngredients());
        File file = new File(menu.getImage());
        Image image = new Image(file.getAbsoluteFile().toURI().toString());
        menuImage.setImage(image);
    }

}
