/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.CentreDressage;
import Entity.Ligne;
import Entity.Produit;
import Entity.jaimeD;
import Service.CentreDressageService;
import com.codename1.components.MultiButton;
import static com.codename1.ui.Component.CENTER;
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
public class CentreDressageForm {

    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
    private Form centreD;

    public CentreDressageForm() {

        centreD = new Form();
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        centreD = (Form) c;

        //menu
        Toolbar tb = centreD.getToolbar();
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

        CentreDressageService c = new CentreDressageService();
        List<CentreDressage> centres = c.findCentreD();

        for (CentreDressage r : centres) {
            try {

                Container ct = new Container(BoxLayout.y());
                Container ct2 = new Container(BoxLayout.y());
                Label libelle = new Label(r.getLibelle());
                jaimeD j = new jaimeD();
                j = c.countjaim(r.getId());
                Label jaime = new Label("" + j.getId() + " j'aime");
                EncodedImage enc = EncodedImage.create("/giphy.gif");
                Image image = URLImage.createToStorage(enc, r.getImage(), "http://localhost/Mobile/PETMYPETSALY/web/images/" + r.getImage());
                Label img = new Label(image.fill(150, 150));
                ct2.add(libelle);
                ct.add(img);
                ct2.add(jaime);
                ct2.setLeadComponent(libelle);
                libelle.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        CentreDressageDetailForm detail = new CentreDressageDetailForm(r);
                        detail.getCentreDetail().show();
                    }
                });
                centreD.add(ct);
                centreD.add(ct2);
            } catch (IOException ex) {
            }
        }

    }

    public Form getCentreD() {
        return centreD;
    }

    public void setCentreD(Form centreD) {
        this.centreD = centreD;
    }

}
