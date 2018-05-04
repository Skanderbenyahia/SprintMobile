/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
import Entity.Concour;
import java.util.Date;
/**
 *
 * @author wael
 */
public class concoursService {
    public static String existe;
    public ArrayList<Concour> getList2() {
        ArrayList<Concour> listConcours = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/petmypetwael/web/app_dev.php/mobile/events/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                      
                        Concour c = new Concour();
                        float id = Float.parseFloat(obj.get("id").toString());                      
                        c.setId((int) id);               
                        c.setDescription(obj.get("description").toString());                      
                            c.setDescription(obj.get("description").toString());
                       float nbrp = Float.parseFloat(obj.get("nbredeplaces").toString());
                        c.setnbredeplaces((int) nbrp);
                        
                      
                        c.setnbredeplaces((int) nbrp);
                        
                        listConcours.add(c);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listConcours;
    }
    
    public void participer(int id_concour,int id_user)
    {
       ConnectionRequest con = new ConnectionRequest();
       con.setUrl("http://localhost/Mobile/petmypetwael/web/app_dev.php/mobile/participation/all/"+id_concour+"/"+id_user);
       con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    
    public String existe(int id_concour)
    {
       ConnectionRequest con = new ConnectionRequest();
       con.setUrl("http://localhost/Mobile/petmypetwael/web/app_dev.php/mobile/existe/all/"+id_concour);
       con.addResponseListener((e) -> {
             existe = new String(con.getResponseData());
            System.out.println(existe);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
return existe;
    }
}
