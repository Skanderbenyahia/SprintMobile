/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.ReservationPetsitter;
import Entity.Session;
import Entity.User;
import Service.ReservationPetsitterService;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author jabou
 */
public class ReservationPetsitterDetailForm {

    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
    private Form petD;
    public static final String ACCOUNT_SID = "ACda3fce438aa009d5882baa517226f9bd";
    public static final String AUTH_TOKEN = "381445f911b4c8d5eed9dcb20f4714a8";

    public ReservationPetsitterDetailForm(User u) throws IOException {
        petD = new Form();
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        petD = (Form) c;

        //menu
        Toolbar tb = petD.getToolbar();
        tb.addCommandToLeftBar("Back", theme.getImage("back-command.png"), e -> new ReservationPetsitterForm().getPet().show());

        Container ct = new Container(BoxLayout.y());
        Container ct2 = new Container(BoxLayout.y());
        Label nom = new Label(u.getNom());
        Label prenom = new Label("Description: " + u.getPrenom());
        Label mail = new Label("Email: " + u.getEmail());
        Label tel = new Label("Telephone: " + String.valueOf(u.getTelehone()));
        Button reserver = new Button("Reserver");
        Button sms = new Button("SMS");
        Button appeler = new Button("Appeler");
        EncodedImage enc = EncodedImage.create("/giphy.gif");
        Image image = URLImage.createToStorage(enc, u.getNom(), "http://localhost/Mobile/PETMYPETSALY/web/images/" + u.getNom() + ".jpeg");
        Label img = new Label(image.fill(150, 150));
        Picker p = new Picker();
        Picker p1 = new Picker();

        ct.add(img);
        ct2.add(nom);
        ct2.add(prenom);
        ct2.add(mail);
        ct2.add(tel);
        ct2.add(p);
        ct2.add(p1);
        ct2.add(reserver);
        ct2.add(sms);
        ct2.add(appeler);

        petD.add(ct);
        petD.add(ct2);

        //reserver
        reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                float prix = u.getId();
                float encaisser = (float) (prix * 0.3);
                ReservationPetsitterService rs = new ReservationPetsitterService();
                Date courente=new Date();
                String date_courrente=formatter.format(courente);
                String d = formatter.format(p.getDate());
                String f = formatter.format(p1.getDate());
                
               int check=(int)(p.getDate().getTime()-courente.getTime());
               int check2=(int)(p1.getDate().getTime()-p.getDate().getTime());
               if(check <0)
               {
               Dialog.show("Errors","La date doit etre supérieur à aujourd'hui", "OK", null);
               }
               else if(check2<0)
               {
               Dialog.show("Errors","Date fin doit etre superieur a date debut", "OK", null);
               }
               else 
               {
                List<ReservationPetsitter> r = new ArrayList<>();
                r = rs.datepetexistant(u.getId(), d);
                if (r.isEmpty() == true) {
                    rs.Reserver(u.getId(), d, f, Session.getCurrentSession(), prix, encaisser);
                    Dialog.show("Success", "Votre réservation a été enregistré", "OK", null);
                } else {
                    Dialog.show("Erreurs", "La date est déja prise", "OK", null);
                }
               }
            }
        });

        //sms
        sms.addActionListener(e->{
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21656074534"),
                        new PhoneNumber("+16156174133"), "PETMYPET : Je viens de vous reservé merci de bien vouloir confirmer").create();
                System.err.println("erreur sms");
            });

        //appeler
        appeler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Call call = Call.creator(
                        new PhoneNumber("+21656074534"),
                        new PhoneNumber("+16156174133"),
                        URI.create("http://demo.twilio.com/docs/voice.xml"))
                        .create();
            }
        });

    }

    public Form getPetD() {
        return petD;
    }
}
