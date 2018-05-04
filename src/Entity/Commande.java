/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author bn-sk
 */
public class Commande {
    private int id;
    private int id_client;
    private String description;
    private Date dateCommande;
    private float amount;

    public Commande() {
    }

    public Commande(int id, int id_client, String description, Date dateCommande, float amount) {
        this.id = id;
        this.id_client = id_client;
        this.description = description;
        this.dateCommande = dateCommande;
        this.amount = amount;
    }

    public Commande(int id_client, String description, Date dateCommande, float amount) {
        this.id_client = id_client;
        this.description = description;
        this.dateCommande = dateCommande;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", id_client=" + id_client + ", description=" + description + ", dateCommande=" + dateCommande + ", amount=" + amount + '}';
    }

    
    
    
}
