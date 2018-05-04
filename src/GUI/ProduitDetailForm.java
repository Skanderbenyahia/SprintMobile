/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Produit;
import Entity.Session;
import Service.ServiceProduit;
import com.codename1.components.SpanLabel;
import com.codename1.components.WebBrowser;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;

/**
 *
 * @author bn-sk
 */
public class ProduitDetailForm {

    private Form f;
    private Resources theme;
    private Label Libelle, prix, quantite;
    private Button ajouter;
    private Container c;

    public ProduitDetailForm(Produit p) {
        try {
            f = new Form();
            UIBuilder uiBuilder = new UIBuilder();
            theme = UIManager.initFirstTheme("/LoginForm");
            c = uiBuilder.createContainer(theme, "HomeGUI");
            f = (Form) c;

            Container ct2 = new Container(BoxLayout.y());
            Container ct = new Container(BoxLayout.x());
            Container ct3 = new Container();
            Container ctqr = new Container(new BoxLayout((BorderLayout.CENTER_BEHAVIOR_SCALE)));

            SpanLabel sp = new SpanLabel("Description: " + p.getDescription());
            Libelle = new Label(p.getLibelle());
            prix = new Label("Prix: " + String.valueOf(p.getPrix()));
            quantite = new Label("Quantite: " + String.valueOf(p.getQuantite()));
            ajouter = new Button("Ajouter au Panier");
            EncodedImage enc = EncodedImage.create("/giphy.gif");
            Image image = URLImage.createToStorage(enc, p.getImage(), "http://localhost/Mobile/PETMYPETSkan/web/images/" + p.getImage());
            Label img = new Label(image.fill(120, 120));
            Image qrcode = URLImage.createToStorage(enc, String.valueOf(p.getId()), "http://localhost/Mobile/PETMYPETSkan/web/images/" + p.getId() + ".jpg");
            Label QCodeimg = new Label(qrcode.fill(100, 100));

            ct.add(img);
            ct.add(QCodeimg);
            ct2.add(sp);
            ct2.add(prix);
            ct3.add(ajouter);
            f.add(ct);
            f.add(ct2);
            f.add(ct3);
            f.add(ctqr);

            ajouter.addActionListener(e -> {
                ServiceProduit sp1 = new ServiceProduit();
                sp1.ajouterPanier(p.getId(), 1, Session.getCurrentSession());
                if (Dialog.show("Redirection", "Vous voulez aller a votre panier ?", "oui", "non")) {
                    PanierForm hi = new PanierForm();
                    hi.getF().show();
                }
            });

            f.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), e->new ProduitForm().getF().show()); 
            ctqr.setLeadComponent(img);
            QCodeimg.addPointerPressedListener(e -> {

                QrWebForm webform = new QrWebForm("http://localhost/Mobile/PETMYPETSkan/web/app_dev.php/afficherProduitDetail/" + p.getId());
                webform.getF().show();
            });

        } catch (IOException ex) {
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
