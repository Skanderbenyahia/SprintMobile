/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Animal;
import Service.ServiceAdoption;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
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
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author Skeez
 */
public class AnimalDetailForm {

    private Form f;
    private Resources theme;
    private Label nom, race, age, demande;
    private Button adopter;
    private ShareButton sb;
    private Container c;
    private UIBuilder uiBuilder;

    public AnimalDetailForm(Animal p) throws IOException {
        f = new Form();
        theme = UIManager.initFirstTheme("/LoginForm");
        uiBuilder = new UIBuilder();
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f = (Form) c;
        Toolbar tb = f.getToolbar();
        Image logo = theme.getImage("dog(1).png");
        Container TopBar = BorderLayout.east(new Label(logo));
        TopBar.add(BorderLayout.WEST, new Label("PETMYPET", "SideMenuTagline"));
        TopBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(TopBar);
        
        Container ct = new Container(BoxLayout.y());
        Container ct2 = new Container(BoxLayout.y());
        Container ct3 = new Container();

        EncodedImage enc = EncodedImage.create("/giphy.gif");
        Image image = URLImage.createToStorage(enc, p.getImage(), "http://localhost/Mobile/PmpPourMobile/web/images/" + p.getImage());
        Label img = new Label(image.fill(100, 100));

        SpanLabel sp = new SpanLabel(p.getDescription());

        nom = new Label("Nom: " + p.getNom());
        nom.getStyle().setFgColor(0xf67777);
        race = new Label("Race: " + String.valueOf(p.getRace()));
        race.getStyle().setFgColor(0xf67777);
        age = new Label("Age: " + String.valueOf(p.getAge()));
        age.getStyle().setFgColor(0xf67777);
        demande = new Label("Demandes: " + String.valueOf(p.getDemande()));
        demande.getStyle().setFgColor(0xf67777);
        adopter = new Button("Adopter");
        sb = new ShareButton();
        sb.setTextToShare("aaaaaaa");
        Button s = new Button("partage fb");
        s.addActionListener((evt) -> {
            String token = "EAACEdEose0cBAAirFrZBGvjbTfL5pFjU2ZB5iLw4PRxPvbQhMAOq6JIV8qc0fYkWCrCqaJfwfsuPKgdeSuqEjlumXtZC66GqMrhTZAjBpTmVgyfoL4UUMy1kIHv7WPxgKAvyVZAwrfr5mHbUOdZCHNWa2VDfX8MGGZBpgQFtI6xzaZAKLd2B3zEROAZBCk4cTIJgZD";
            FacebookClient fb = new DefaultFacebookClient(token);
            InputStream fis = null;

            try {
                fis = Storage.getInstance().createInputStream(p.getImage());
            } catch (IOException ex) {

            }

            FacebookType rr = fb.publish("me/photos", FacebookType.class,
                    BinaryAttachment.with("photo.jpg", fis), Parameter.with("message", "Cherrrrrrrrrr"));
            System.out.println("fgkjqsioughfsdniohsdfidshvkdfj");

        });
        adopter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceAdoption sa = new ServiceAdoption();
                sa.adopter(p.getId());

                AdoptionEmail e = new AdoptionEmail();
                e.getF().show();

            }
        });
        ct.add(img);
        ct2.add(sp);
        ct2.add(nom);
        ct2.add(race);
        ct2.add(age);
        ct2.add(demande);
        ct2.add(sb);
        ct3.add(adopter);

        f.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AdoptionForm p = new AdoptionForm();
                p.getF().show();
            }
        });
        f.add(ct);
        f.add(ct2);
        f.add(ct3);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
