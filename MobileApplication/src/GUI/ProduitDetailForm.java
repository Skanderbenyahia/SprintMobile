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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author bn-sk
 */
public class ProduitDetailForm {

    private Form f;
    private Resources theme;
    private Label Libelle,prix,quantite;
    private Button ajouter;
    private Container c;
    public ProduitDetailForm(Produit p) {
        f = new Form();
        UIBuilder uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f = (Form) c;

        Container ct2 = new Container();
        ct2.add(theme.getImage("a.png"));
        SpanLabel sp = new SpanLabel(p.getDescription());
        Libelle=new Label(p.getLibelle());
        prix=new Label(String.valueOf(p.getPrix()));
        quantite=new Label(String.valueOf(p.getQuantite()));
        ajouter=new Button("Ajouter au Panier");
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               ServiceProduit sp=new ServiceProduit();
                sp.ajouterPanier(p.getId(), 1,Session.getCurrentSession());
            }
        });
        ct2.add(sp);
        ct2.add(prix);
        ct2.add(quantite);
        ct2.add(ajouter);
        f.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeForm h=new HomeForm();
                h.getHome().show();
            }
        });
        f.add(ct2);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
