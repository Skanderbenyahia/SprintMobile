/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author Skeez
 */
public class Animal {

    private int id;
    private String nom;
    private String espece;
    private String race;
    private String age;
    private String sexe;
    private String taille;
    private String region;
    private String description;
    private String etat;
    private String image;
    private int demande;

    public Animal(int id, String nom, String espece, String race, String age, String sexe, String taille, String region, String description, String etat, String image, int demande) {
        this.id = id;
        this.nom = nom;
        this.espece = espece;
        this.race = race;
        this.age = age;
        this.sexe = sexe;
        this.taille = taille;
        this.region = region;
        this.description = description;
        this.etat = etat;
        this.image = image;
        this.demande = demande;
    }

    public Animal(String nom, String espece, String race, String age, String sexe, String taille, String region, String description, String etat, String image, int demande) {
        this.nom = nom;
        this.espece = espece;
        this.race = race;
        this.age = age;
        this.sexe = sexe;
        this.taille = taille;
        this.region = region;
        this.description = description;
        this.etat = etat;
        this.image = image;
        this.demande = demande;
    }

    

    public Animal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

   

    

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nom=" + nom + ", race=" + race + ", sexe=" + sexe + ", age=" + age + ", etat=" + etat + ", image=" + image + ", description=" + description + ", demande=" + demande + ", taille=" + taille + ", region=" + region + ", espece=" + espece + '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDemande() {
        return demande;
    }

    public void setDemande(int demande) {
        this.demande = demande;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

}
