/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.centreToilettage;
import Service.ServiceCentreT;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
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
 * @author Mimouna
 */
public class CentreToilettageForm {

    private Form f;
    private Resources theme;
    private UIBuilder uiBuilder;
    private Container c;

    public CentreToilettageForm() {
        f = new Form("CentreToilettage");
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

        ServiceCentreT sc = new ServiceCentreT();
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        List<centreToilettage> listR = sc.findCentreT();
        for (centreToilettage r : listR) {
            EncodedImage enc = null;
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
            }
            Container img = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Container info = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label(r.getLibelle());
            Label d = new Label(r.getDescription());
            Label A = new Label(r.getAdresse());
            Image image = URLImage.createToStorage(enc, r.getImage(), "http://localhost/Mobile/PETMYPETemna/web/images/" + r.getImage());
            img.add(image);
            Label imge = new Label(image.fill(150, 150));
            info.add(l);
            info.add(A);
            info.add(d);

            info.setLeadComponent(l);
            f.add(info);
            f.add(imge);
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CentreTDetailForm p = new CentreTDetailForm(r);
                    p.getF().show();
                }
            });

        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
