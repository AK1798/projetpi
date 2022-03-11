/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.controller.commande;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mounira.controller.menu.MenuController;
import mounira.controller.menu.UserMenuController;
import mounira.controller.user.MainFXMLController;
import mounira.entite.Menu;
import mounira.entite.MenuCommande;
import mounira.service.commandes.MenuCommandeService;
import mounira.utils.MyListener;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class ClientCommande2Controller implements Initializable {

    @FXML
    private Text total;
    @FXML
    private Text totalNew;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private MyListener myListener;
    private int mcId;
    private float totalP;
    MenuCommandeService mcs = new MenuCommandeService();
    private boolean b = false;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grid.getChildren().clear();
        showCommande(mcs.afficherMenuCommande());
        total.setText(String.valueOf(mcs.afficherTotal()) + MenuController.CURRENCY);
        totalP = mcs.afficherTotal();
    }

    private void showCommande(List<Menu> menus) {
        //menus.clear();
        //System.out.println(menus.isEmpty());
        if (menus.size() > 0) {
            myListener = new MyListener() {
                @Override
                public void onClickListener(Menu menu) {
                    mcId = menu.getMcId();
                    MenuCommandeService mcs = new MenuCommandeService();
                    MenuCommande mc = new MenuCommande(mcId);
                    //System.out.println(PanierInterfaceController.mcId);
                    mcs.supCommande(mc);
                    //grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
                    //showPanier(panierList);
                    grid.getChildren().clear();
                    showCommande(mcs.afficherMenuCommande());
                    total.setText(String.valueOf(mcs.afficherTotal()) + MenuController.CURRENCY);
                    totalP = mcs.afficherTotal();
                    b = false;
                    //System.out.println(mcs.afficherMenuCommande());
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < menus.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/mounira/view/commandes/AdminCommande1.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                AdminCommande1Controller admincommande1Controller = fxmlLoader.getController();
                admincommande1Controller.setData(menus.get(i), myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(UserMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(menus);
    }

    @FXML
    private void handleReturnMenuAdmin(ActionEvent event) {
                GotoFXML("/mounira/view/user/MainClientFXML", "ForU", event);

    }

}
