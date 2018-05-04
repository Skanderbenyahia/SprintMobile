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
public class WishList {
    private int id;
    private int idAnimal;
    private int idClient;

    public WishList(int id, int idAnimal, int idClient) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.idClient = idClient;
    }

    public WishList(int idAnimal, int idClient) {
        this.idAnimal = idAnimal;
        this.idClient = idClient;
    }

    public int getId() {
        return id;
    }

    public WishList() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    
    
}
