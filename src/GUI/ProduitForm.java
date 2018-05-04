/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Produit;
import Service.ServiceProduit;
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
public class ProduitForm {

    private Form f;
    private Resources theme;
    private UIBuilder uiBuilder;
    private Container c;
    public ProduitForm() {
        f = new Form("Produit");
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f = (Form) c;
        Toolbar tb = f.getToolbar();
        tb.addCommandToLeftBar("Back", theme.getImage("back-command.png"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeForm h=new HomeForm();
                h.getHome().show();
            }
        });
        ServiceProduit sp = new ServiceProduit();
        Container c1 = new Container();
        List<Produit> listR = sp.findProduit();
        for (Produit r : listR)
        {
            Container ct = new Container();
            Label l = new Label(r.getLibelle());
            ct.add(theme.getImage("a.png"));
            ct.add(l);
            ct.setLeadComponent(l);
            f.add(ct);
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ProduitDetailForm p=new ProduitDetailForm(r);
                    p.getF().show();
                }
            });
        }
        f.add(c1);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
