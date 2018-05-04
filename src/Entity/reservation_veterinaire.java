/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;


/**
 *
 * @author Mimouna
 */
public class reservation_veterinaire {
    private int id ;
    private int id_user ;
    private int id_veterinaire ;
    private String date_debut ;
    private String date_fin ;
    private String description ;

    public reservation_veterinaire(int id, int id_user, int id_veterinaire, String date_debut, String date_fin, String description) {
        this.id = id;
        this.id_user = id_user;
        this.id_veterinaire = id_veterinaire;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public reservation_veterinaire(int id_user, int id_veterinaire, String date_debut, String date_fin, String description) {
        this.id_user = id_user;
        this.id_veterinaire = id_veterinaire;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public reservation_veterinaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_veterinaire() {
        return id_veterinaire;
    }

    public void setId_veterinaire(int id_veterinaire) {
        this.id_veterinaire = id_veterinaire;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "reservation_veterinaire{" + "id=" + id + ", id_user=" + id_user + ", id_veterinaire=" + id_veterinaire + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", description=" + description + '}';
    }
    
    
}
