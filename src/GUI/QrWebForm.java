/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.WebBrowser;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author bn-sk
 */
public class QrWebForm {
    private Form f;
    private Resources theme;
    private Container c;

    public QrWebForm(String url) {
        
        f = new Form();
            UIBuilder uiBuilder = new UIBuilder();
            theme = UIManager.initFirstTheme("/LoginForm");
            c = uiBuilder.createContainer(theme, "HomeGUI");
            f = (Form) c;
                        f.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), x->new ProduitForm().getF().show()); 

            WebBrowser web=new WebBrowser(url);
            f.add(web);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
