/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Animal;
import Entity.Session;
import Service.ServiceAdoption;
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
import com.codename1.ui.layouts.BorderLayout;
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
public class AdoptionForm {

    private Form f;
    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;

    public AdoptionForm() {
        f = new Form("Animal");
        theme = UIManager.initFirstTheme("/LoginForm");
        uiBuilder = new UIBuilder();
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f = (Form) c;
        
        Toolbar tb = f.getToolbar();
        Image logo = theme.getImage("dog (1).png");
        Container TopBar = BorderLayout.east(new Label(logo));
        TopBar.add(BorderLayout.WEST, new Label("PETMYPET", "SideMenuTagline"));
        TopBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(TopBar);
        tb.addMaterialCommandToSideMenu("home", FontImage.MATERIAL_HOME, e -> new HomeForm().getHome().show());
        tb.addMaterialCommandToSideMenu("Ventes", FontImage.MATERIAL_WEB, e -> new ProduitForm().getF().show());
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_WEB, e -> new PanierForm().getF().show());
        tb.addMaterialCommandToSideMenu("Adoption", FontImage.MATERIAL_WEB, e -> {
            new AdoptionForm().getF().show();
        }); 
        tb.addMaterialCommandToSideMenu("CentreDressage", FontImage.MATERIAL_WEB, e -> new CentreDressageForm().getCentreD().show());
        tb.addMaterialCommandToSideMenu("Petsitter", FontImage.MATERIAL_WEB, e -> new ReservationPetsitterForm().getPet().show());
        tb.addMaterialCommandToSideMenu("WishList", FontImage.MATERIAL_WEB, e -> {
            new WishListForm().getF().show();
        });
        
        ServiceAdoption sp = new ServiceAdoption();
        List<Animal> listR = sp.findAnimal();
        for (Animal r : listR) {
            
            
            try {
                Container ct = new Container(BoxLayout.y());
                Container ct2 = new Container(BoxLayout.y());
                Container ct3 = new Container();
                
                EncodedImage enc = EncodedImage.create("/giphy.gif");
                Image image = URLImage.createToStorage(enc, r.getImage(), "http://localhost/Mobile/PmpPourMobile/web/images/" + r.getImage());
                Label img=new Label(image.fill(150, 150));
                
                Label l = new Label(r.getNom());
                Label lrace= new Label(r.getRace());
                Label description=new Label(r.getDescription());
                Button detail = new Button();
                detail.setText("Détail");
                detail.addActionListener((evt) -> {
                    try {
                        AnimalDetailForm f= new AnimalDetailForm(r);
                        ServiceAdoption saa=new ServiceAdoption();
                        
                        if(saa.dejaAjoute(r.getId(),Session.getCurrentSession()))
                        {
                            detail.setEnabled(false);
                            
                            Dialog.show("Adopté", "Vous avez déja fait une demande !", "Ok", "Annuler");
                        }
                        else{
                            f.getF().show();
                        }
                    } catch (IOException ex) {
                        
                    }
                });
                
                ct.add(img);
                ct2.add(l);
                ct2.add(lrace);
                ct2.add(description);
                ct3.add(detail);
                f.add(ct);
                f.add(ct2);
                f.add(ct3);
            }
            /* Animal aaa= listR.get(0);
            Label txt= new Label();
            txt.setText(aaa.getNom());
            f.add(txt);*/
            //f.add(c1);
            catch (IOException ex) {
            }

        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
