/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.service.scommentaire;

import mounira.entite.Commentaire;
import mounira.entite.scommentaire;
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
import mounira.service.commentaires.CommentaireService;
import mounira.utils.DataSource;

/**
 *
 * @author bouss
 */
public class scommentaireService {

    private Statement st;
    private ResultSet rs;
    Connection cnx;

    public scommentaireService() {
        try {
            DataSource cs = DataSource.getInstance();
            st = cs.getCnx().createStatement();
            cnx = DataSource.getInstance().getCnx();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean insert(scommentaire co) {
        try {
            String req = "insert into scommentaire (user_id,commentaire_id ,Scommentaire_desc,Scommentaire_date) values (?,?,?,?) ";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, co.getUser_id());
            preparedStatement.setInt(2, co.getCommentaire_id());
            preparedStatement.setString(3, co.getScommentaire_desc());
            preparedStatement.setString(4, co.getScommentaire_date());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void delete(scommentaire co) {
        String req = "delete from scommentaire where Scommentaire_id=" + co.getScommentaire_id();
        scommentaire c = displayById(co.getScommentaire_id());

        if (c != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("not found");
        }
    }

    public List<scommentaire> displayAll() {
        String req = "select * from scommentaire";
        List<scommentaire> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                scommentaire c = new scommentaire();

                c.setScommentaire_id(rs.getInt("Scommentaire_id"));
                c.setUser_id(rs.getInt("user_id"));
                c.setCommentaire_id(rs.getInt("commentaire_id"));
                c.setScommentaire_desc(rs.getString("Scommentaire_desc"));
                c.setScommentaire_date(rs.getString("Scommentaire_date"));

                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public scommentaire displayById(int id) {
        String req = "select * from scommentaire where Scommentaire_id =" + id;
        scommentaire c = new scommentaire();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            c.setScommentaire_id(rs.getInt("Scommentaire_id"));
            c.setUser_id(rs.getInt("user_id"));
            c.setCommentaire_id(rs.getInt("commentaire_id"));
            c.setScommentaire_desc(rs.getString("Scommentaire_desc"));
            c.setScommentaire_date(rs.getString("Scommentaire_date"));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public boolean update(scommentaire co) {
        try {
            String qry = "UPDATE scommentaire SET Scommentaire_desc =?,comment_date =?,Scommentaire_date=? WHERE Scommentaire_id  =?";
            PreparedStatement preparedStatement = cnx.prepareStatement(qry);
            preparedStatement.setString(1, co.getScommentaire_desc());
            preparedStatement.setString(2, co.getScommentaire_date());
            preparedStatement.setInt(3, co.getScommentaire_id());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public List<scommentaire> DisplayByComID(int userid) {

        String req = "select * from scommentaire where commentaire_id   =" + userid;
        List<scommentaire> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                scommentaire c = new scommentaire();

                c.setScommentaire_id(rs.getInt("Scommentaire_id"));
                c.setUser_id(rs.getInt("user_id"));
                c.setCommentaire_id(rs.getInt("commentaire_id"));
                c.setScommentaire_desc(rs.getString("Scommentaire_desc"));
                c.setScommentaire_date(rs.getString("Scommentaire_date"));

                list.add(c);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
