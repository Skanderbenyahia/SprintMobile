/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ServiceAdoption;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.util.List;



/**
 *
 * @author Skeez
 */
public class AdoptionEmail {
    
    private Form f;
    private Resources theme;
    private TextField emailRefuge;
    private TextField nom;
    private TextField prenom;
    private TextField num;
    private TextField motivation;
    private TextField adresse;
    private Button envoyez;
    private Container c;
    private UIBuilder uiBuilder;
    
    public AdoptionEmail() {
        f = new Form("Email");
        theme = UIManager.initFirstTheme("/LoginForm");
        uiBuilder = new UIBuilder();
        c = uiBuilder.createContainer(theme, "HomeGUI");
        
         Toolbar tb = f.getToolbar();
        Image logo = theme.getImage("dog (1).png");
        Container TopBar = BorderLayout.east(new Label(logo));
        TopBar.add(BorderLayout.WEST, new Label("PETMYPET", "SideMenuTagline"));
        TopBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(TopBar);
        tb.addMaterialCommandToSideMenu("home", FontImage.MATERIAL_HOME, e -> new HomeForm().getHome().show());
        tb.addMaterialCommandToSideMenu("Ventes", FontImage.MATERIAL_WEB, e-> new ProduitForm().getF().show()); 
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_WEB, e-> new PanierForm().getF().show()); 
        tb.addMaterialCommandToSideMenu("Adoption", FontImage.MATERIAL_WEB, e-> {
            new AdoptionForm().getF().show();
        }); 
        tb.addMaterialCommandToSideMenu("CentreDressage", FontImage.MATERIAL_WEB, e-> new CentreDressageForm().getCentreD().show());
        tb.addMaterialCommandToSideMenu("Petsitter", FontImage.MATERIAL_WEB, e->new ReservationPetsitterForm().getPet().show());
        tb.addMaterialCommandToSideMenu("WishList", FontImage.MATERIAL_WEB, e->{
            new WishListForm().getF().show(); // Logger.getLogger(AdoptionEmail.class.getName()).log(Level.SEVERE, null, ex);
        });
        Validator v= new Validator();
        
        num = new TextField();
        v.addConstraint(num, new LengthConstraint(8));
        Container c = new Container();
        nom = new TextField();
        v.addConstraint(nom, new LengthConstraint(1));
        prenom = new TextField();
        v.addConstraint(prenom, new LengthConstraint(1));
        motivation = new TextField();
        v.addConstraint(motivation, new LengthConstraint(1));
        adresse = new TextField();
       // v.addConstraint(nom, new GroupConstraint(new ));
        emailRefuge= new TextField();
        envoyez = new Button();
        v.addSubmitButtons(envoyez);
        v.setShowErrorMessageForFocusedComponent(true);
        nom.setHint("Nom");
        prenom.setHint("Prenom");
        num.setHint("Numéro de tel ");
        motivation.setHint("Motivation");
        adresse.setHint("Adresse");
        envoyez.setText("Envoyer");
        emailRefuge.setText("refuge@gmail.com");
        c.add(nom);
        c.add(prenom);
        c.add(motivation);
        c.add(adresse);
        c.add(num);
        
        c.add(envoyez);
        f.add(c);
        ServiceAdoption saa= new ServiceAdoption();
        List<String> l= saa.infoUser();
          envoyez.addActionListener((evt) -> {
            Message m = new Message("Nom: " + nom.getText() + " Prenom: " + prenom.getText() + " Adresse: " + adresse.getText() + " Motivation: " + motivation.getText()+"Mail de l'utilisateur: "+l.get(0));
            Display.getInstance().sendMessage(new String[]{emailRefuge.getText()}, "Demande d'adoption ", m);
        });
        
    }
    
    public Form getF() {
        return f;
    }
    
    public void setF(Form f) {
        this.f = f;
    }
    
}
