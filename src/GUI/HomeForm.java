/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ServiceProduit;
import com.codename1.components.ImageViewer;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;


/**
 *
 * @author jabou
 */
public class HomeForm {

    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
    private Form home;
    private Image image;
    private ImageViewer img;

    public HomeForm() {

        home = new Form("", new FlowLayout(CENTER, CENTER));
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        home = (Form) c;
        //menu
        Toolbar tb = home.getToolbar();
        Image logo = theme.getImage("dog (1).png");
        Container TopBar = BorderLayout.east(new Label(logo));
        TopBar.add(BorderLayout.WEST, new Label("PETMYPET", "SideMenuTagline"));
        TopBar.setUIID("SideCommand");
        ServiceProduit sp = new ServiceProduit();
        tb.addComponentToSideMenu(TopBar);
        tb.addCommandToLeftBar("Panier", theme.getImage(String.valueOf(sp.findcountpanier()) + ".png"), e -> new PanierForm().getF().show());
        tb.addCommandToLeftBar("Déconnexion", theme.getImage(""), e -> new PanierForm().getF().show());
        tb.addMaterialCommandToSideMenu("home", FontImage.MATERIAL_HOME, e -> new HomeForm().getHome().show());
        tb.addMaterialCommandToSideMenu("Adoption", FontImage.MATERIAL_WEB, e -> new AdoptionForm().getF().show());
        tb.addMaterialCommandToSideMenu("Ventes", FontImage.MATERIAL_WEB, e -> new ProduitForm().getF().show());
        tb.addMaterialCommandToSideMenu("CentreDressage", FontImage.MATERIAL_WEB, e -> new CentreDressageForm().getCentreD().show());
        tb.addMaterialCommandToSideMenu("Petsitter", FontImage.MATERIAL_WEB, e -> new ReservationPetsitterForm().getPet().show());
        tb.addMaterialCommandToSideMenu("Veterinaire", FontImage.MATERIAL_WEB, e -> new VeterinaireForm().getF().show());
        tb.addMaterialCommandToSideMenu("Centre Toilettage", FontImage.MATERIAL_WEB, e -> new CentreToilettageForm().getF().show());
        tb.addMaterialCommandToSideMenu("Concours", FontImage.MATERIAL_HOME, e -> new eventsform().getCentreD().show());
        tb.addMaterialCommandToSideMenu("conseils", FontImage.MATERIAL_HOME, e -> new Conseilsform().getCentreD().show());
        tb.addMaterialCommandToSideMenu("WishList", FontImage.MATERIAL_WEB, e -> new WishListForm().getF().show());
        Image homeimage = theme.getImage("mobileHome.png");
        Label homeimg = new Label(homeimage);
        home.add(homeimg);
    }

    public Form getHome() {
        return home;
    }

    public void setHome(Form home) {
        this.home = home;
    }

}
