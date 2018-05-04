/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.CentreDressage;
import Entity.User;
import Service.CentreDressageService;
import Service.ReservationPetsitterService;
import com.codename1.ui.Container;
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
 * @author jabou
 */
public class ReservationPetsitterForm {

    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
    private Form pet;

    public ReservationPetsitterForm() {
        pet = new Form();
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        pet = (Form) c;

        //menu
        Toolbar tb = pet.getToolbar();
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
        ReservationPetsitterService c = new ReservationPetsitterService();
        List<User> users = c.findPetsitter();

        for (User r : users) {
            try {
                Container ct = new Container(BoxLayout.y());
                Container ct2 = new Container(BoxLayout.y());
                Label libelle = new Label(r.getNom());
                EncodedImage enc = null;

                enc = EncodedImage.create("/giphy.gif");

                Image image = URLImage.createToStorage(enc, r.getNom(), "http://localhost/Mobile/PETMYPETSALY/web/images/" + r.getNom() + ".jpeg");
                Label img = new Label(image.fill(150, 150));
                ct.add(libelle);
                ct2.add(img);
                ct.setLeadComponent(libelle);
                libelle.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        try {
                            ReservationPetsitterDetailForm detail = new ReservationPetsitterDetailForm(r);
                            detail.getPetD().show();
                        } catch (IOException ex) {
                        }

                    }
                });
                pet.add(ct2);
                pet.add(ct);
            } catch (IOException ex) {
            }
        }

    }

    public Form getPet() {
        return pet;
    }

}
