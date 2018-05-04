/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Session;
import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bn-sk
 */
public class ServiceUser {

    

    public User authentificate(String username) {
        ConnectionRequest con = new ConnectionRequest();
        User user = new User();
        user.setId(0);
        con.setUrl("http://localhost/Mobile/PETMYPETSALY/web/app_dev.php/auth/"+username);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser jsonp = new JSONParser();
                    
                    Map<String, Object> users = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" + users.get("root"));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

                    for (Map<String, Object> obj : list) {
                        int id=Math.round(Float.parseFloat(obj.get("id").toString()));
                        Session.start(id);
                        user.setId(id);
                        user.setUsername(obj.get("username").toString());
                        user.setPassword(obj.get("password").toString());
                    }
                } catch (IOException ex) {
                   
                }
                    

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }       

}
