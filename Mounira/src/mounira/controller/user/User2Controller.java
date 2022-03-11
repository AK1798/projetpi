package mounira.controller.user;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mounira.entite.user;
import mounira.service.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javax.swing.JFileChooser;

public class User2Controller implements Initializable {

    @FXML
    private Button btnReturnMenu;

    @FXML
    private Button btncreate;

    @FXML
    private Button btndel;

    @FXML
    private Button btnmodif;

    @FXML
    private Button btntri;

    @FXML
    private ComboBox<?> cbrechpar;

    @FXML
    private ComboBox<?> cbtri;

    @FXML
    private ImageView imageviewlogo;

    @FXML
    private MenuBar menu;

    @FXML
    private Button pdf;

    @FXML
    private RadioButton radadmin;

    @FXML
    private RadioButton radclient;

    @FXML
    private RadioButton radtous;

    @FXML
    private ToggleGroup role;

    @FXML
    private Button stat;

    @FXML
    private VBox ListLayout;

    @FXML
    private TextField tfrecherche;

    @FXML
    void CreateUser(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mounira/view/user/AddUser.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHidden(event1 -> {
                clear(listUsers());
                Display(listUsers());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void DeleteUser(ActionEvent event) {
    }

    @FXML
    void ModifUser(ActionEvent event) {
    }

    @FXML
    void OnClickedPrint(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.pdf;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }
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

    @FXML
    void OnClickedStatistique(ActionEvent event) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("/mounira/view/Piechart.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
//            stage.getIcons().add(new Image("/mounira/Ressources/Logo.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Pdf(ActionEvent event) {
        String path = "";

        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(j);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();

        }

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "/User.pdf"));
            doc.open();

            PdfPTable table = new PdfPTable(8);
            table.addCell("NOM");
            table.addCell("PRENOM");
            table.addCell("EMAIL");
            table.addCell("PASSWORD");
            table.addCell("DATE");
            table.addCell("NUM TELEPHONE");
            table.addCell("ADRESSE");
            table.addCell("ROLE");

            UserService u = new UserService();

            for (int i = 0; i < listUsers().size(); i++) {
                Display(listUsers());
                String Nom = u.readAll().get(i).getNom();
                String Prenom = u.readAll().get(i).getPrenom();
                String email = u.readAll().get(i).getEmail();
                String password = u.readAll().get(i).getPassword();
                String Date = String.valueOf(u.readAll().get(i).getDate());
                String num_tel = String.valueOf(u.readAll().get(i).getNum_tel());
                String Adresse = u.readAll().get(i).getAdresse();
                String role = u.readAll().get(i).getRole();

                table.addCell(Nom);
                table.addCell(Prenom);
                table.addCell(email);
                table.addCell(password);
                table.addCell(Date);
                table.addCell(num_tel);
                table.addCell(Adresse);
                table.addCell(role);

            }

            doc.add(table);

        } catch (FileNotFoundException | DocumentException ex) {

        }

        doc.close();
    }

    @FXML
    void SearchUser(KeyEvent event) {
        UserService userService = new UserService();

        String search = tfrecherche.getText();
        if (search == null) {
            Display(listUsers());
        } else {
            String searchby = (String) cbrechpar.getSelectionModel().getSelectedItem();
            ObservableList<user> users = userService.recherche(searchby, search);
            Display(users);
        }
    }

    @FXML
    void TriUsers(ActionEvent event) {
        clear(listUsers());
        UserService userService = new UserService();
        String tri = (String) cbtri.getSelectionModel().getSelectedItem();
        ObservableList<user> users = userService.tri(tri);
        Display(users);
    }

    @FXML
    void getAdmins(ActionEvent event) {
        clear(listUsers());
        UserService userService = new UserService();
        ObservableList<user> users = userService.filterRole("Admin");
        Display(users);
    }

    @FXML
    void getClients(ActionEvent event) {
        clear(listUsers());

        UserService userService = new UserService();
        ObservableList<user> users = userService.filterRole("Client");
        Display(users);
    }

    @FXML
    void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("/mounira/view/user/MainFXML", "ForU", event);

    }

    public List<user> listUsers() {
        UserService userService = new UserService();
        return new ArrayList<>(userService.readAll());
    }

    public void Display(List<user> users) {
        for (user utilisateur : users) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mounira/view/user/UserSample.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                UserSampleController userSampleController = fxmlLoader.getController();
                userSampleController.setData(utilisateur);
                ListLayout.getChildren().add(hBox);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void clear(List<user> users) {
        for (user utilisateur : users) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mounira/view/user/UserSample.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                UserSampleController userSampleController = fxmlLoader.getController();
                userSampleController.setData(utilisateur);
                ListLayout.getChildren().clear();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Display(listUsers());
    }

    @FXML
    private void updateTable(ActionEvent event) {
        clear(listUsers());
        Display(listUsers());
    }
}
