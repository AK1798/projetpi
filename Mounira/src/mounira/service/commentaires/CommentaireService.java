/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.service.commentaires;

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
import mounira.service.scommentaire.scommentaireService;
import mounira.utils.DataSource;

/**
 *
 * @author bouss
 */
public class CommentaireService {

    private Statement st;
    private ResultSet rs;
    Connection cnx;

    public CommentaireService() {
        try {
            DataSource cs = DataSource.getInstance();
            st = cs.getCnx().createStatement();
            cnx = DataSource.getInstance().getCnx();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean insert(Commentaire co) {
        try {
            String req = "insert into commentaire (user_id,commentaire_desc,commentaire_date) values (?,?,?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, co.getUser_id());
            preparedStatement.setString(2, co.getCommentaire_desc());
            preparedStatement.setString(3, co.getCommentaire_date());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void delete(Commentaire co) {
        String req = "delete from commentaire where commentaire_id=" + co.getCommentaire_id();
        Commentaire c = displayById(co.getCommentaire_id());

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

    public List<Commentaire> displayAll() {
        String req = "select * from commentaire";
        List<Commentaire> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Commentaire c = new Commentaire();

                c.setCommentaire_id(rs.getInt("commentaire_id"));
                c.setUser_id(rs.getInt("user_id"));
                c.setCommentaire_desc(rs.getString("commentaire_desc"));
                c.setCommentaire_date(rs.getString("commentaire_date"));
                c.setNbscom(rs.getInt("nbscom"));
                c.setNblike(rs.getInt("nblike"));
                c.setNbdislike(rs.getInt("nbdislike"));

                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Commentaire displayById(int id) {
        String req = "select * from commentaire where commentaire_id =" + id;
        Commentaire c = new Commentaire();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            c.setCommentaire_id(rs.getInt("commentaire_id"));
            c.setUser_id(rs.getInt("user_id"));
            c.setCommentaire_desc(rs.getString("commentaire_desc"));
            c.setCommentaire_date(rs.getString("commentaire_date"));
            c.setNbscom(rs.getInt("nbscom"));
            c.setNblike(rs.getInt("nblike"));
            c.setNbdislike(rs.getInt("nbdislike"));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public boolean update(Commentaire co) {
        try {
            String qry = "UPDATE commentaire SET commentaire_desc =?,commentaire_date=? WHERE commentaire_id =?";
            PreparedStatement preparedStatement = cnx.prepareStatement(qry);
            preparedStatement.setString(1, co.getCommentaire_desc());
            preparedStatement.setString(2, co.getCommentaire_date());
            preparedStatement.setInt(3, co.getCommentaire_id());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public List<Commentaire> DisplayByUserID(int userid) {

        String req = "select * from commentaire where user_id  =" + userid;
        List<Commentaire> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Commentaire c = new Commentaire();

                c.setCommentaire_id(rs.getInt("commentaire_id"));
                c.setUser_id(rs.getInt("user_id"));
                c.setCommentaire_desc(rs.getString("commentaire_desc"));
                c.setCommentaire_date(rs.getString("commentaire_date"));
                c.setNblike(rs.getInt("nblike"));
                c.setNbdislike(rs.getInt("nbdislike"));
                list.add(c);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void setlike(int id_com, int nb) {
        try {
            String req = "update commentaire set nblike=?  where commentaire_id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, nb);
            ps.setInt(2, id_com);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getnblike(int id) {
        int s = 0;
        try {

            String req = "select * from commentaire where commentaire_id=" + id;
            rs = st.executeQuery(req);
            while (rs.next()) {
                s = rs.getInt(5);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void setdislike(int id_com, int nb) {
        try {
            String req = "update commentaire set nbdislike=?  where commentaire_id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, nb);
            ps.setInt(2, id_com);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getnbdislike(int id) {
        int s = 0;
        try {

            String req = "select * from commentaire where commentaire_id=" + id;
            rs = st.executeQuery(req);
            while (rs.next()) {
                s = rs.getInt(6);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public int checklikeuser(int iduser, int idcom) {
        int s = 0;
        try {

            String req = "select * from likee where user_id  =" + iduser + " and commentaire_id  =" + idcom;
            rs = st.executeQuery(req);
            while (rs.next()) {
                s = rs.getInt(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public boolean addlike(int iduser, int idcom) {
        try {
            String req = "insert into likee (user_id,commentaire_id) values " + "('" + iduser + "','" + idcom + "')";
            Statement st = cnx.createStatement();
            try {
                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(scommentaireService.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        } catch (SQLException ex) {
            Logger.getLogger(scommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    public int checkdislikeuser(int iduser, int idcom) {
        int s = 0;
        try {

            String req = "select * from dislikee where user_id=" + iduser + " and commentaire_id =" + idcom;
            rs = st.executeQuery(req);
            while (rs.next()) {
                s = rs.getInt(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public boolean adddislike(int iduser, int idcom) {
        try {
            String req = "insert into dislikee (user_id,commentaire_id) values " + "('" + iduser + "','" + idcom + "')";
            Statement st = cnx.createStatement();
            try {
                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(scommentaireService.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        } catch (SQLException ex) {
            Logger.getLogger(scommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    public boolean deletelike(int id) {
        try {
            String req = "delete from likee where likee_id  =?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(scommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    public boolean deletedislike(int id) {
        try {
            String req = "delete from dislikee where dislikee_id  =?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(scommentaireService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    public int getnbscom(int id) {
        int s = 0;
        try {

            String req = "select * from commentaire where commentaire_id  =" + id;
            rs = st.executeQuery(req);
            while (rs.next()) {
                s = rs.getInt(7);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void setnbscom(int id_commentaire, int nb) {
        try {
            String req = "update commentaire set nbscom=?  where commentaire_id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, nb);
            ps.setInt(2, id_commentaire);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
