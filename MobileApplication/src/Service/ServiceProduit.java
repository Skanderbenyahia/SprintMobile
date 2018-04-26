/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Commande;
import Entity.Ligne;
import Entity.PaymentOrder;
import Entity.Produit;
import Entity.Session;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bn-sk
 */
public class ServiceProduit {

    private ConnectionRequest con;
    static double total=0;
    public List<Produit> findProduit() {
        List<Produit> produits = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/PETMYPET/web/app_dev.php/produits");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("root");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Produit produit = new Produit();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            produit.setId(id);
                            produit.setLibelle(obj.get("libelle").toString());
                            produit.setDescription(obj.get("description").toString());
                            int prix = Math.round(Float.parseFloat(obj.get("prix").toString()));
                            produit.setPrix(prix);
                            produit.setAnimal(obj.get("animal").toString());
                            produit.setImage(obj.get("Image").toString());
                            int quantite = Math.round(Float.parseFloat(obj.get("quantite").toString()));
                            produit.setQuantite(quantite);
                            produits.add(produit);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return produits;
    }
    
    public List<Ligne> findPanier() {
        List<Ligne> lignes = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/PETMYPET/web/app_dev.php/panier");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("root");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Ligne ligne = new Ligne();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            ligne.setId(id);
                            ligne.setId_client(Session.getCurrentSession());
                            int id_produit = Math.round(Float.parseFloat(obj.get("Produit").toString().substring(4,6)));
                            ligne.setId_produit(id_produit);
                            ligne.setImage(obj.get("Image").toString());
                            int prix = Math.round(Float.parseFloat(obj.get("prix").toString()));
                            ligne.setPrix(prix);
                            int quantite = Math.round(Float.parseFloat(obj.get("quantite").toString()));
                            ligne.setQuantite(quantite);
                            lignes.add(ligne);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lignes;
    }

    public void ajouterPanier(int id_produit,int quantite,int id_client) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PETMYPET/web/app_dev.php/ajouterPanier/"+id_produit+"/"+quantite+"/"+id_client;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public double findtotal() {
        
        con = new ConnectionRequest();
        con.setUrl("http://localhost/PETMYPET/web/app_dev.php/Panier/total/"+Session.getCurrentSession());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("root");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            total=Double.parseDouble(obj.get("total").toString());
                            
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return total;
    }
    
    
    
    public void commanderPanier()
    {
        Commande c=new Commande();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/PETMYPET/web/app_dev.php/Panier/total/"+Session.getCurrentSession());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Produits = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Produits);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Produits.get("root");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Float total=Float.parseFloat(obj.get("total").toString());
                            c.setAmount(total);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        String Url = "http://localhost/PETMYPET/web/app_dev.php/Panier/commander/"+Session.getCurrentSession()+"/"+c.getAmount();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
    
    
    public void createPayment(PaymentOrder payment) {

        
        try {
            Charge charge = payment.createCharge("sk_test_iPtaJ5udtTjyX6WXdtUjomIp", payment.getAmmount(), payment.getName(), payment.getCardnumber(), payment.getExp_month(), payment.getExp_year(), payment.getCvv());
            System.out.println("charge : " + charge.getStatus());
            if (charge.getStatus().equalsIgnoreCase("succeeded")) {
                ServiceProduit ps = new ServiceProduit();
                ps.commanderPanier();
               /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Commande éffectué avec succées !");
                alert.showAndWait();*/
                System.out.println("success");

            }

        } catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException | APIException ex) {
            if (ex.getMessage() != null || !(ex.getMessage().equals(""))) {
                /*Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Commande Erroné ! Veuillez vérifier vos coordonées bancaires");
                alert.showAndWait();*/
                System.out.println("error");
            }
        }
    }
}
