/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Session;
import Entity.User;
import Entity.reservation_veterinaire;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mimouna
 */
public class ServicesVeterinaire {
     public List<User> findVeterinaire() 
     {
        List<User> users = new ArrayList<>();
       ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PETMYPETemna/web/app_dev.php/afficher_veterinaires_front_JSON");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(Users);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("root");
                  
                    if (list != null) {
                        for (Map<String, Object> obj : list) 
                        {
                            User user = new User();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            user.setId(id);
                            user.setNom(obj.get("nom").toString());
                            user.setPrenom(obj.get("prenom").toString());
                            
                            user.setAdresse(obj.get("adresse").toString());
                           
                           
                            users.add(user);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return users;
    }
    
    
   public void reserverVeterinaire(String ddebut,int id_vet , int id_user  ,String description)
   {
        ConnectionRequest con = new ConnectionRequest();
        
        String Url = "http://localhost/Mobile/PETMYPETemna/web/app_dev.php/reserverVeterinairerJSON/" + ddebut + "/" + id_vet + "/" + id_user + "/" + description ;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
   }
   
   public List<reservation_veterinaire> datepetexistant( String ddebut,int id_vet) {
        List<reservation_veterinaire> reservations = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETemna/web/app_dev.php/ExistantJSON/"+ddebut +"/"+ id_vet;
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("root");
                    if (list != null) {
                      for (Map<String, Object> obj : list) {
                            reservation_veterinaire r=new reservation_veterinaire();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            r.setId(id);
                            reservations.add(r);
                      }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return reservations;
    }   
     
      public List<Integer> nbreRDV() 
      {
        List<Integer> nbre = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETemna/web/app_dev.php/nbreRDV";
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("root");
                    if (list != null) {
                      for (Map<String, Object> obj : list) {
                          
                            int id = Math.round(Float.parseFloat(obj.get("1").toString()));
                            nbre.add(id);
                      }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return nbre;
    }   
  
      public List<User> getStat() 
      {
        List<User> u = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETemna/web/app_dev.php/getstat/";
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("root");
                    if (list != null) {
                      for (Map<String, Object> obj : list) {
                          User user=new User();
                            double stat = Double.parseDouble(obj.get("nb").toString());
                            user.setStat(stat);
                            user.setNom(obj.get("nom").toString());
                            u.add(user);
                      }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return u;
    } 
     
    public List<String> getName(int id)
    {
        List<String> names = new ArrayList<>();
       ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PETMYPETemna/web/app_dev.php/usernom/"+Session.getCurrentSession());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> namess = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(namess);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) namess.get("root");
                  
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            String name="";
                            name= obj.get("username").toString();
                            
                            names.add(name);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return names;
        
    }
     
}
