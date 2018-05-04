/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entity.ReservationPetsitter;
import Entity.User;
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
public class ReservationPetsitterService {

    private ConnectionRequest con;
    public static String result;
    public List<User> findPetsitter() {
        List<User> users = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/petsitterJson");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("root");
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            User c = new User();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            c.setId(id);
                            c.setNom(obj.get("nom").toString());
                            c.setPrenom(obj.get("prenom").toString());
                             int tel = Math.round(Float.parseFloat(obj.get("telephone").toString()));
                            c.setTelehone(tel);
                            c.setEmail(obj.get("email").toString());
                            users.add(c);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return users;
    }

    public void Reserver(int id_petsitter, String d, String f, int id_client, float prix, float encaisser) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/reserverPetsitterrJSON/" + d + "/" + f + "/" + prix + "/" + encaisser + "/" + id_petsitter + "/" + id_client;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public List<ReservationPetsitter> datepetexistant(int id_petsitter, String d) {
        List<ReservationPetsitter> reservations = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/ExistantJSON/"+d +"/"+ id_petsitter;
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
                            ReservationPetsitter r=new ReservationPetsitter();
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

}
