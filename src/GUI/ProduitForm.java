/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Produit;
import Entity.Session;
import Service.ServiceProduit;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author bn-sk
 */
public class ProduitForm {

    private Form f;
    private Resources theme;
    private UIBuilder uiBuilder;
    private Container c;

    public ProduitForm() {
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f = (Form) c;

        //menu
        Toolbar tb = f.getToolbar();
        Image logo = theme.getImage("dog (1).png");
        Container TopBar = BorderLayout.east(new Label(logo));
        TopBar.add(BorderLayout.WEST, new Label("PETMYPET", "SideMenuTagline"));
        TopBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(TopBar);
        tb.addMaterialCommandToSideMenu("home", FontImage.MATERIAL_HOME, e -> new HomeForm().getHome().show());
        tb.addMaterialCommandToSideMenu("Ventes", FontImage.MATERIAL_WEB, e -> new ProduitForm().getF().show());
        tb.addMaterialCommandToSideMenu("CentreDressage", FontImage.MATERIAL_WEB, e -> new CentreDressageForm().getCentreD().show());
        tb.addMaterialCommandToSideMenu("Petsitter", FontImage.MATERIAL_WEB, e -> new ReservationPetsitterForm().getPet().show());
        tb.addMaterialCommandToSideMenu("Concours", FontImage.MATERIAL_HOME, e -> new eventsform().getCentreD().show());
        tb.addMaterialCommandToSideMenu("conseils", FontImage.MATERIAL_HOME, e -> new Conseilsform().getCentreD().show());


        ServiceProduit sp = new ServiceProduit();

        List<Produit> listR = sp.findProduit();
        for (Produit r : listR) {

            try {
                Container ct = new Container(BoxLayout.y());
                Container ct2 = new Container(BoxLayout.y());
          
                Label l = new Label(r.getLibelle());
                EncodedImage enc = EncodedImage.create("/giphy.gif");
                Image image = URLImage.createToStorage(enc, r.getImage(), "http://localhost/Mobile/PETMYPETSkan/web/images/" + r.getImage());
                Label img=new Label(image.fill(150, 150));

                ct.add(img);
                ct2.add(l);
                ct.setLeadComponent(l);
                l.addPointerPressedListener(e -> {

                    ProduitDetailForm p = new ProduitDetailForm(r);
                    p.getF().show();

                });
                f.add(ct);
                f.add(ct2);
            } catch (IOException ex) {
            }

        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
