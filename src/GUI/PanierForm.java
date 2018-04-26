/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ligne;
import Entity.Produit;
import Service.ServiceProduit;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
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
        Container c1 = new Container();
        List<Ligne> listP = sp.findPanier();
        for (Ligne l : listP) {
            Container ct = new Container();
            Label l1 = new Label(l.getImage());
            ct.add(theme.getImage("a.png"));
            ct.add(l1);
            ct.setLeadComponent(l1);
            f.add(ct);
        }
        commander=new Button("commander");
        commander.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Double total=sp.findtotal();
                StripeForm sf=new StripeForm(total);
                sf.getF().show();
            }
        });
        f.add(commander);
        f.add(c1);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
