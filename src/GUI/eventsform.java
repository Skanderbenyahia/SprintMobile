/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import Entities.Concour;
import Entity.Concour;
import Entity.Session;
import Service.concoursService;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.teknikindustries.bulksms.SMS;
//import Service.concoursSercice;
import java.util.ArrayList;

/**
 *
 * @author jabou
 */
public class eventsform {

    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
    private Form centreD;

    public eventsform() {
        concoursService ser = new concoursService();
        ArrayList<Concour> concours = ser.getList2();
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

        for (Concour c : concours) {
            Container ct = new Container(BoxLayout.y());
            Container ct1 = new Container(BoxLayout.y());
            Container ct2 = new Container(BoxLayout.y());

            Label idd = new Label("Id: " + String.valueOf(c.getId()));
            Label desc = new Label("" + c.getDescription());
            Label desc2 = new Label("places: " + String.valueOf(c.getnbredeplaces()));
            Label desc1 = new Label("date : " + c.getDate());
            Label im = new Label(theme.getImage("download.jpg").fill(100, 100));
            Button btn = new Button("Participer");

            ct.add(im);
            ct1.add(desc);
            ct1.add(desc1);
            ct1.add(desc2);
            ct2.add(btn);

            //participer
            btn.addActionListener(e -> {
                String exite = ser.existe(c.getId());
                String t = "true";
                System.out.println(exite);
                if (exite.contains(t)) {
                    ser.participer(c.getId(), Session.getCurrentSession());
                    Dialog.show("Merci", "Merci pour votre participation !", "ok", null);
                    SMS smstut = new SMS();
                    smstut.SendSMS("emna", "00000000", "welcome to concour  : " + c.getDescription() + " " + " prevue le :" + c.getDate() + ". de 9h a 16h ", "21621378265", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                    //hedi   00000000
                } else {
                    Dialog.show("Desole", "Ce concour est pleins !", "ok", null);
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
