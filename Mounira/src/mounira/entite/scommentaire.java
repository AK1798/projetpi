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
public class scommentaire {

    private int Scommentaire_id;
    private int user_id;
    private String Scommentaire_desc;
    private String Scommentaire_date;
    private int commentaire_id;

    public scommentaire() {
    }

    public scommentaire(int user_id, String Scommentaire_desc, String Scommentaire_date, int commentaire_id) {
        this.user_id = user_id;
        this.Scommentaire_desc = Scommentaire_desc;
        this.Scommentaire_date = Scommentaire_date;
        this.commentaire_id = commentaire_id;

    }

    public scommentaire(int Scommentaire_id, int user_id, String Scommentaire_desc, String Scommentaire_date, int commentaire_id) {
        this.Scommentaire_id = Scommentaire_id;
        this.user_id = user_id;
        this.Scommentaire_desc = Scommentaire_desc;
        this.Scommentaire_date = Scommentaire_date;
        this.commentaire_id = commentaire_id;

    }

    public int getScommentaire_id() {
        return Scommentaire_id;
    }

    public void setScommentaire_id(int Scommentaire_id) {
        this.Scommentaire_id = Scommentaire_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getScommentaire_desc() {
        return Scommentaire_desc;
    }

    public void setScommentaire_desc(String Scommentaire_desc) {
        this.Scommentaire_desc = Scommentaire_desc;
    }

    public String getScommentaire_date() {
        return Scommentaire_date;
    }

    public void setScommentaire_date(String Scommentaire_date) {
        this.Scommentaire_date = Scommentaire_date;
    }

    public int getCommentaire_id() {
        return commentaire_id;
    }

    public void setCommentaire_id(int commentaire_id) {
        this.commentaire_id = commentaire_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.Scommentaire_id;
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
        final scommentaire other = (scommentaire) obj;
        if (this.Scommentaire_id != other.Scommentaire_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "scommentaire{" + "Scommentaire_id=" + Scommentaire_id + ", user_id=" + user_id + ", Scommentaire_desc=" + Scommentaire_desc + ", Scommentaire_date=" + Scommentaire_date + ", commentaire_id=" + commentaire_id + '}';
    }

     
}
