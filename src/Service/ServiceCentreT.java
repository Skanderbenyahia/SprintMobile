/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Commentaire;
import Entity.centreToilettage;
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
public class ServiceCentreT {

    private ConnectionRequest con;

    public List<centreToilettage> findCentreT() {
        List<centreToilettage> centresT = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PETMYPETemna/web/app_dev.php/afficherCentreToilettageJSON");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> CentresT = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(CentresT);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) CentresT.get("root");
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            centreToilettage centreT = new centreToilettage();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            centreT.setId(id);
                            centreT.setLibelle(obj.get("libelle").toString());
                            centreT.setDescription(obj.get("description").toString());
                            centreT.setAdresse(obj.get("adresse").toString());
//                          int tel = Math.round(Integer.parseInt(obj.get("tel").toString()));
//                          centreT.setTel(tel);
                            centreT.setImage(obj.get("image").toString());
                            System.out.println(centreT.getAdresse() + "********************");
                            centresT.add(centreT);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return centresT;

    }

    public void ajoutCommentaire(Commentaire c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/PETMYPETemna/web/app_dev.php/addCommentaireJSONAction/" + c.getIdUser() + "/" + c.getIdCentre() + "/" + c.getContenu()    ;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> 
        {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
}
