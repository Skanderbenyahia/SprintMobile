/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import Entity.Conseils;
import Entity.Session;
import Service.concoursService;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import Service.conseilsService;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.teknikindustries.bulksms.SMS;

/**
 *
 * @author jabou
 */
public class Conseilsform {

    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
    private Form centreD;

    public Conseilsform() {
        conseilsService ser = new conseilsService();
        ArrayList<Conseils> concours = ser.getList2();
        centreD = new Form();
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        centreD = (Form) c;

        //Menu
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

        for (int i = 0; i < concours.size(); i++) {

            Container ct = new Container(BoxLayout.y());
            Container ct1 = new Container(BoxLayout.y());
            Container ct2 = new Container(BoxLayout.y());
            Label reclamtion = new Label("reclamation:");
            TextField rec = new TextField();
            Label desc = new Label("" + concours.get(i).getTitre());
            SpanLabel desc1 = new SpanLabel("" + concours.get(i).getTexte());
            Label desc2 = new Label("" + concours.get(i).getType());
            Label im = new Label(theme.getImage("download.jpg").fill(100, 100));
            Button btn = new Button("reclamer");

            ct.add(im);
            ct1.add(desc);
            ct1.add(desc2);
            ct1.add(desc1);
            ct2.add(reclamtion);
            ct2.add(rec);
            ct2.add(btn);
            //reclamer 
            btn.addActionListener(e -> {
                System.out.println(e);

                if (rec.getText().isEmpty()) {
                    Dialog.show("Attention", "svp recrire votre reclamation", "ok", null);
                } else {

                    Message m = new Message(rec.getText());
                    m.getAttachments().put("", "text/plain");
                    m.getAttachments().put("", "image/png");
                    Display.getInstance().sendMessage(new String[]{"Admin@admin.tn"}, "reclamation sur une conseil", m);

                }
            });

            centreD.add(ct);
            centreD.add(ct1);
            centreD.add(ct2);

        }

    }

    public Form getCentreD() {
        return centreD;
    }

    public void setCentreD(Form centreD) {
        this.centreD = centreD;
    }

}
