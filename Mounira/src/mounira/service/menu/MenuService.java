/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.service.menu;

import mounira.entite.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mounira.utils.DataSource;

/**
 *
 * @author Dhia
 */
public class MenuService {

    private PreparedStatement pst;
    private final Connection conn;
    private Statement ste;
    private ResultSet rs;

    public MenuService() {
        conn = DataSource.getInstance().getCnx();
    }

    public boolean ajouterMenu(Menu m) {
        String req = "insert into menu(titre,description,prix,ingredients,categorie,image) values (?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getTitre());
            pst.setString(2, m.getDescription());
            pst.setFloat(3, m.getPrix());
            pst.setString(4, m.getIngredients());
            pst.setString(5, m.getCategorie());
            pst.setString(6, m.getImage());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modifierMenu(Menu m) {
        String req = "update menu set titre = ? , description = ? , prix= ?, ingredients = ? ,categorie= ?, image= ? where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getTitre());
            pst.setString(2, m.getDescription());
            pst.setFloat(3, m.getPrix());
            pst.setString(4, m.getIngredients());
            pst.setString(5, m.getCategorie());
            pst.setString(6, m.getImage());
            pst.setInt(7, m.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    public boolean suppMenu(Menu m) {
        String req = "delete from menu where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, m.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Menu> afficherMenuVegan() {
        List<Menu> menus = new ArrayList<>();
        String sql = "select * from menu where categorie='Vegan'";
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setId(rs.getInt("id"));
                m.setTitre(rs.getString("titre"));
                m.setDescription(rs.getString("description"));
                m.setPrix(rs.getFloat("prix"));
                m.setIngredients(rs.getString("ingredients"));
                m.setCategorie(rs.getString("categorie"));
                m.setImage(rs.getString("image"));
                menus.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return menus;
    }

    public List<Menu> afficherMenuNormal() {
        List<Menu> menus = new ArrayList<>();
        String sql = "select * from menu where categorie='Normal'";
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setId(rs.getInt("id"));
                m.setTitre(rs.getString("titre"));
                m.setDescription(rs.getString("description"));
                m.setPrix(rs.getFloat("prix"));
                m.setIngredients(rs.getString("ingredients"));
                m.setCategorie(rs.getString("categorie"));
                m.setImage(rs.getString("image"));
                menus.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return menus;
    }

    public List<Menu> afficherAllMenus() {
        List<Menu> menus = new ArrayList<>();
        String sql = "select * from menu";
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setId(rs.getInt("id"));
                m.setTitre(rs.getString("titre"));
                m.setDescription(rs.getString("description"));
                m.setPrix(rs.getFloat("prix"));
                m.setIngredients(rs.getString("ingredients"));
                m.setCategorie(rs.getString("categorie"));
                m.setImage(rs.getString("image"));
                menus.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return menus;
    }

    public ObservableList<Menu> readEvent() {
        String req = "select * from menu";

        ObservableList<Menu> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new Menu(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getInt("prix"), rs.getString("ingredients"), rs.getString("categorie"), rs.getString("image")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
     public ObservableList<Menu> readMenuS(String search) {
        String req = "select * from menu where titre LIKE '%" + search + "%'"
                        + "UNION select * from menu where description Like '%" + search + "%'"
                        + "UNION select * from menu where prix Like '%" + search + "%'"
                        + "UNION select * from menu where categorie Like '%" + search + "%'"
                        + "UNION select * from menu where ingredients Like '%" + search + "%'";

        ObservableList<Menu> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new Menu(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getInt("prix"), rs.getString("ingredients"), rs.getString("categorie"), rs.getString("image")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    public Menu getMenu(String tt){
        String req = "select * from menu where titre='"+tt+"'";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                Menu menu =new Menu(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getInt("prix"), rs.getString("ingredients"), rs.getString("categorie"), rs.getString("image"));
                return menu;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
