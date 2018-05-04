/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.CentreDressage;
import Entity.Produit;
import Entity.ReservationPetsitter;
import Entity.jaimeD;
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
 * @author jabou
 */
public class CentreDressageService {
     private ConnectionRequest con;
      public List<CentreDressage> findCentreD() {
        List<CentreDressage> centres = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/afficherCentreDressageJson");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Centre = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Centre.get("root");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            CentreDressage c = new CentreDressage();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            c.setId(id);
                            c.setAdresse(obj.get("adresse").toString());
                            int tel = Math.round(Float.parseFloat(obj.get("tel").toString()));
                            c.setTel(tel);
                            c.setLat(Math.round(Float.parseFloat(obj.get("lat").toString())));
                            c.setLng(Math.round(Float.parseFloat(obj.get("lng").toString())));
                            c.setLibelle(obj.get("libelle").toString());
                            c.setDescription(obj.get("description").toString());
                            c.setImage(obj.get("image").toString());
                            centres.add(c);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return centres;
    }
      
       public void Jaime(int id_centre, int id_user,int etat, int etat2) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/jaime/" + id_centre + "/" + id_user + "/" + etat + "/" + etat2;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
        public void JaimePas(int id_user,int id_centre,int etat, int etat2) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/jaimepas/" + id_user + "/" + id_centre + "/" + etat + "/" + etat2;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public jaimeD countjaim(int id_centre)
      {
          jaimeD jaime = new jaimeD();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/countjaime/"+id_centre);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Centre = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Centre.get("root");
                    System.out.println("info : " + list);
                    if (list != null) {
                        for (Map<String, Object> obj : list) { 
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            jaime.setId(id);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
              return jaime;

      }
      
       public List<jaimeD> Conditionjaime(int id_centre,int id_user) {
        List<jaimeD> jaimes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/jaimeConditionJSON/"+id_centre +"/"+ id_user;
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
                            jaimeD r=new jaimeD();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            r.setId(id);
                            jaimes.add(r);
                      }
                    }
                } catch (IOException ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return jaimes;
    }

}
