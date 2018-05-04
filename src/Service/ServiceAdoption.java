/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Animal;
import Entity.Ligne;
import Entity.Produit;
import Entity.Session;
import Entity.WishList;
import GUI.ProduitDetailForm;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Skeez
 */
public class ServiceAdoption {

    private ConnectionRequest con;

    public List<Animal> findAnimal() {
        List<Animal> animals = new ArrayList<>();

        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PmpPourMobile/web/app_dev.php/animal/afficherfront");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Animals = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Animals);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Animals.get("root");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Animal animal = new Animal();
                            float id = Float.parseFloat(obj.get("id").toString());
                            float demande = Float.parseFloat(obj.get("demande").toString());
                            animal.setId((int) id);
                            animal.setNom(obj.get("nom").toString());
                            animal.setEspece(obj.get("espece").toString());
                            animal.setRace(obj.get("race").toString());
                            animal.setImage(obj.get("image").toString());
                            animal.setDemande((int) demande);
                            animal.setAge(obj.get("age").toString());
                            /* animal.set(obj.get("prix").toString());
                            animal.setAnimal(obj.get("animal").toString());
                            animal.setImage(obj.get("image").toString());
                            animal.setQuantite(Integer.parseInt(obj.get("quantite").toString())); */
                            animals.add(animal);/* animal.set(obj.get("prix").toString());
                            animal.setAnimal(obj.get("animal").toString());
                            animal.setImage(obj.get("image").toString());
                            animal.setQuantite(Integer.parseInt(obj.get("quantite").toString())); */
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return animals;
    }

    public void adopter(int id) {
        int idClient = Session.getCurrentSession();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PmpPourMobile/web/app_dev.php/adopter/" + id + "/" + idClient);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public List<WishList> findWishList() {
        List<WishList> wishlistss = new ArrayList<>();
        int idClient = Session.getCurrentSession();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PmpPourMobile/web/app_dev.php/wishlist/"+idClient);
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser j = new JSONParser();
                Map<String, Object> wishlists = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(wishlists);
                List<Map<String, Object>> list = (List<Map<String, Object>>) wishlists.get("root");
                System.out.println("info : " + list);
                if (list != null) {
                    for (Map<String, Object> obj : list) {
                        WishList wishlist = new WishList();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float idAnimal = Float.parseFloat(obj.get("idAnimal").toString());
                        float idClient1 = Float.parseFloat(obj.get("idClient").toString());
                        wishlist.setId((int) id);
                        wishlist.setIdAnimal((int)idAnimal);
                        wishlist.setIdClient((int) idClient1);
                        wishlistss.add(wishlist);
                    }
                }
            }catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return wishlistss;
    }

    public List<Animal> findAnimalWishlist() {
        List<WishList> w=new ArrayList<>();
        w = findWishList();
        System.out.println(w.toString());
       List<Animal> animals = new ArrayList<>();
        for (WishList r : w) {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PmpPourMobile/web/app_dev.php/animal/afficherAnimalwish/"+r.getIdAnimal());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Animals = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Animals);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Animals.get("root");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Animal animal = new Animal();
                            float id = Float.parseFloat(obj.get("id").toString());
                            float demande = Float.parseFloat(obj.get("demande").toString());
                            animal.setId((int) id);
                            animal.setNom(obj.get("nom").toString());
                            animal.setEspece(obj.get("espece").toString());
                            animal.setRace(obj.get("race").toString());
                            animal.setImage(obj.get("image").toString());
                            animal.setDemande((int) id);
                            
                            animals.add(animal);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        }
        
        
        return animals;
    }
    public void supprimerWishList(int idAnimal, int idClient)
    {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PmpPourMobile/web/app_dev.php/supprimerwishlist/"+idAnimal+"/"+idClient);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public boolean dejaAjoute(int idAnimal, int idClient) {
        List wl= findWishList();
        Iterator a= wl.iterator();
        boolean b =false;
        while(a.hasNext())
        {
           WishList w= (WishList) a.next();
            if(w.getIdAnimal()==idAnimal)
                b= true;
            
        }
        return b;
    } 
    
    public List<String> infoUser()
    {
        List<String> email = new ArrayList<>();
        int idClientt = Session.getCurrentSession();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PmpPourMobile/web/app_dev.php/user/"+idClientt);
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser j = new JSONParser();
                Map<String, Object> emails = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(emails);
                List<Map<String, Object>> list = (List<Map<String, Object>>) emails.get("root");
                System.out.println("info : " + list);
                if (list != null) {
                    for (Map<String, Object> obj : list) {
                        
                        String mail=obj.get("email").toString();
                        email.add(mail);
                       
                    }
                }
            }catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return email;
    }
}
