/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;

/**
 *
 * @author jabou
 */
public class HomeForm {

    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
    private Form home;

    public HomeForm() {

        home = new Form("Acceuil", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        home = (Form) c;
        Toolbar tb = home.getToolbar();
        Image logo = theme.getImage("dog (1).png");
        Container TopBar = BorderLayout.east(new Label(logo));
        TopBar.add(BorderLayout.WEST, new Label("PETMYPET", "SideMenuTagline"));
        TopBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(TopBar);
        tb.addMaterialCommandToSideMenu("home", FontImage.MATERIAL_HOME, e -> new HomeForm().getHome().show());
        //tb.addMaterialCommandToSideMenu("Centre Dressage", FontImage.MATERIAL_HOME, e -> new CentreDressageForm().getCentreD().show());

        tb.addMaterialCommandToSideMenu("Adoption", FontImage.MATERIAL_WEB, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });

        tb.addMaterialCommandToSideMenu("Ventes", FontImage.MATERIAL_WEB, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ProduitForm p = new ProduitForm();
                p.getF().show();
            }
        });

        tb.addMaterialCommandToSideMenu("Services", FontImage.MATERIAL_WEB, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });

        tb.addMaterialCommandToSideMenu("Centre de toilettages", FontImage.MATERIAL_WEB, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                    CentreToilettageForm  ct = new CentreToilettageForm();
                    ct.getF().show();
               

            }
        });
        
          tb.addMaterialCommandToSideMenu("Veterinaires", FontImage.MATERIAL_WEB, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                    VeterinaireForm  ct = new VeterinaireForm();
                    ct.getF().show();
               

            }
        });
        
        
        
        
        
        
        tb.addMaterialCommandToSideMenu("Events", FontImage.MATERIAL_WEB, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_WEB, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    PanierForm p=new PanierForm();
                    p.getF().show();
            }
        });

        
    }

    public Form getHome() {
        return home;
    }

    public void setHome(Form home) {
        this.home = home;
    }

}
