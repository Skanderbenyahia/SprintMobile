/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Animal;
import Entity.Session;
import Service.ServiceAdoption;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author Skeez
 */
public class WishListForm {

    private Form f;
    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;

    public WishListForm() {
        f = new Form("Animal");
        theme = UIManager.initFirstTheme("/LoginForm");
        uiBuilder = new UIBuilder();
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f = (Form) c;
        Toolbar tb = f.getToolbar();
        tb.addCommandToLeftBar("Back", theme.getImage("back-command.png"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeForm h = new HomeForm();
                h.getHome().show();
            }
        });
        ServiceAdoption sp = new ServiceAdoption();
        List<Animal> listR = sp.findAnimalWishlist();
        for (Animal r : listR) {

            try {
                Container ct = new Container(BoxLayout.y());
                Container ct2 = new Container(BoxLayout.y());
                Container ct3 = new Container();
                EncodedImage enc = EncodedImage.create("/giphy.gif");
                Image image = URLImage.createToStorage(enc, r.getImage(), "http://localhost/Mobile/PmpPourMobile/web/images/" + r.getImage());
                Label img = new Label(image.fill(150, 150));
                
                Label l = new Label(r.getNom());
                Label lrace = new Label(r.getRace());
                Label description = new Label(r.getDescription());
                Button detail = new Button();
                detail.setText("Détail");
                detail.addActionListener((evt) -> {
                    try {
                        AnimalDetailForm f = new AnimalDetailForm(r);
                        f.getF().show();
                    } catch (IOException ex) {
                    }
                });
                
                ct.add(img);
                ct2.add(l);
                ct2.add(lrace);
                ct2.add(description);
                ct3.add(detail);
                Button supprimer = new Button();
                supprimer.setText("Supprimer");
                ct3.add(supprimer);
                supprimer.addActionListener((evt) -> {
                    ServiceAdoption sa = new ServiceAdoption();
                    int idClient = Session.getCurrentSession();
                    int idAnimal = r.getId();
                    sa.supprimerWishList(idAnimal, idClient);
                    WishListForm ff = new WishListForm(); //Logger.getLogger(WishListForm.class.getName()).log(Level.SEVERE, null, ex);
                    ff.getF().show();
                    
                });
                
                f.add(ct);
                f.add(ct2);
                f.add(ct3);
            } catch (IOException ex) {            }

        }

       
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
