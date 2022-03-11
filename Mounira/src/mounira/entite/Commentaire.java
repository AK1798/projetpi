/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.entite;

import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author bouss
 */
public class Commentaire {

    private int commentaire_id;
    private int user_id;
    private String commentaire_desc;
    private String commentaire_date;
    private int nblike;
    private int nbdislike;
    private int nbscom;

    public Commentaire() {
    }

    public Commentaire(int user_id, String commentaire_desc, String commentaire_date, int nblike, int nbdislike, int nbscom) {
        this.user_id = user_id;
        this.commentaire_desc = commentaire_desc;
        this.commentaire_date = commentaire_date;
        this.nblike = nblike;
        this.nbdislike = nbdislike;
        this.nbscom = nbscom;
    }

    public Commentaire(int commentaire_id, int user_id, String commentaire_desc, String commentaire_date, int nblike, int nbdislike, int nbscom) {
        this.commentaire_id = commentaire_id;
        this.user_id = user_id;
        this.commentaire_desc = commentaire_desc;
        this.commentaire_date = commentaire_date;
        this.nblike = nblike;
        this.nbdislike = nbdislike;
        this.nbscom = nbscom;

    }

    public int getCommentaire_id() {
        return commentaire_id;
    }

    public void setCommentaire_id(int commentaire_id) {
        this.commentaire_id = commentaire_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCommentaire_desc() {
        return commentaire_desc;
    }

    public void setCommentaire_desc(String commentaire_desc) {
        this.commentaire_desc = commentaire_desc;
    }

    public String getCommentaire_date() {
        return commentaire_date;
    }

    public void setCommentaire_date(String commentaire_date) {
        this.commentaire_date = commentaire_date;
    }

    public int getNblike() {
        return nblike;
    }

    public void setNblike(int nblike) {
        this.nblike = nblike;
    }

    public int getNbdislike() {
        return nbdislike;
    }

    public void setNbdislike(int nbdislike) {
        this.nbdislike = nbdislike;
    }

    public int getNbscom() {
        return nbscom;
    }

    public void setNbscom(int nbscom) {
        this.nbscom = nbscom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.commentaire_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commentaire other = (Commentaire) obj;
        if (this.commentaire_id != other.commentaire_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "commentaire_id=" + commentaire_id + ", user_id=" + user_id + ", commentaire_desc=" + commentaire_desc + ", commentaire_date=" + commentaire_date + ", nblike=" + nblike + ", nbdislike=" + nbdislike + ", nbscom=" + nbscom + '}';
    }


}
