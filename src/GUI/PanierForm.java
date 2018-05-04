/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ligne;
import Entity.Session;
import Service.ServiceProduit;
import com.codename1.components.ToastBar;
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
public class PanierForm {

    private Form f;
    private Resources theme;
    private UIBuilder uiBuilder;
    private Container c;
    private Button commander;

    public PanierForm() {
        f = new Form("Ligne");
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
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
        ServiceProduit sp = new ServiceProduit();
        Container c1 = new Container(BoxLayout.x());
        Container c2=new Container();
        List<Ligne> listP = sp.findPanier();
        for (Ligne l : listP) {
            try {
                Container ct = new Container(BoxLayout.x());
                EncodedImage enc = EncodedImage.create("/giphy.gif");
                Image image = URLImage.createToStorage(enc, l.getImage(), "http://localhost/Mobile/PETMYPETSkan/web/images/" + l.getImage());
                Label img=new Label(image.fill(100, 100));
                Label prix=new Label(String.valueOf(l.getPrix()*l.getQuantite()));
                ct.add(img);
                Button supp = new Button(image);
                FontImage.setMaterialIcon(supp, FontImage.MATERIAL_DELETE);
                supp.addActionListener(e -> {
                    if (Dialog.show("Confirmer", "Etes vous sure ?", "Supprimer", "Annuler")) {
                        sp.suppproduit(Session.getCurrentSession());
                    }
                });
                c2.add(supp);
                ct.add(prix);
                f.add(ct);
            } catch (IOException ex) {
            }
        }
        
        commander = new Button("commander");
        if(sp.findPanier().isEmpty())
        {
            commander.setEnabled(false);
        }
        commander.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Double total = sp.findtotal();
                int x = sp.findreduction();
                
                StripeForm sf = new StripeForm(sp.findtotal());
                sf.getF().show();
            }
        });

        int x = sp.findreduction();
        double pr=sp.findtotal();
        
        if (x % 3 == 0 && x != 0) {
            pr = pr - (pr * 0.2);
        }
        
        Label tot = new Label(String.valueOf(pr));
        f.add(tot);
        f.add(commander);
        f.add(c1);

        int a = sp.findreduction();
        if (a % 3 == 0 && x != 0 && a == 1) {
            ToastBar.showMessage("Vous avez une nouvelle notification", FontImage.MATERIAL_STAR, 6000);
            a = 0;
        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
