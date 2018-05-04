/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Commentaire;
import Entity.Produit;
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
public class ServiceCommentaire {

    private ConnectionRequest con;
    static double total = 0;

    public List<Commentaire> getAllCommentaires() {
        List<Commentaire> commentaires = new ArrayList<>();
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/PETMYPETemna/web/app_dev.php/afficherCommentairesJSON");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> Commentaires = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(Commentaires);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Commentaires.get("root");
                    if (list != null) {
                        for (Map<String, Object> obj : list) {
                            Commentaire commentaire = new Commentaire();
                            int id = Math.round(Float.parseFloat(obj.get("id").toString()));
                            commentaire.setId(id);
                            System.out.println("hedha l id mtaa l user "+obj.get("idUser"));
                            int  idUser =Math.round(Float.parseFloat(obj.get("idUser").toString()));
                            commentaire.setIdUser(idUser);
                            int idCentre = Math.round(Float.parseFloat(obj.get("idCentre").toString()));
                            commentaire.setIdCentre(idCentre);                            
                            commentaire.setContenu(obj.get("contenu").toString());
                            commentaires.add(commentaire);
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commentaires;
    }

}
