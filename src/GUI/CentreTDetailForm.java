/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Commentaire;
import Entity.Produit;
import Entity.Session;
import Entity.centreToilettage;
import Service.ServiceCentreT;
import Service.ServiceCommentaire;
import Service.ServiceProduit;
import Service.ServicesVeterinaire;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
public class CentreTDetailForm {

    private Form f;
    private Resources theme;
    private Label Libelle, adresse, tel, description;
    private Container c;

    public CentreTDetailForm(centreToilettage ct) {
        f = new Form(BoxLayout.y());
        UIBuilder uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f = (Form) c;
        
        Container ct2 = new Container(BoxLayout.y());
        ct2.add(theme.getImage("a.ctng"));

        SpanLabel slL = new SpanLabel(ct.getLibelle());
        slL.getStyle().setFgColor(0x00000);
        Libelle = new Label(ct.getLibelle());
        Libelle.getAllStyles().setFgColor(0x00000);

        SpanLabel slA = new SpanLabel(ct.getAdresse());
        slA.getStyle().setFgColor(0x00000);
        adresse = new Label(ct.getAdresse());
        adresse.getAllStyles().setFgColor(0x5F4747);

        tel = new Label(String.valueOf(ct.getTel()));
        tel.getStyle().setFgColor(0x00000);
        
        SpanLabel slD = new SpanLabel(ct.getDescription());
        
        description = new Label(ct.getDescription());
        description.getAllStyles().setFgColor(0x5F4747);

        EncodedImage enc = null;
        try {
            enc = EncodedImage.create("/giphy.gif");
            
        } catch (IOException ex) {
        }
        Image image = URLImage.createToStorage(enc, ct.getImage(), "http://localhost/PETMYPET/web/images/" + ct.getImage());
        //image.fill(500,200);
        ct2.add(image);
        ct2.add(slA);
        slD.getStyle().setFgColor(0x000000);
        ct2.add(slD);
        ct2.add(slL);

        Container Cs = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField commentaireTXT = new TextField("", "rédiger un commentaire", 20, TextArea.ANY);
        Container EspComm = new Container();
        ct2.add(commentaireTXT);
        Button commenter = new Button("commenter");
//        if (commentaireTXT.getText()==null)
//        {
//            Dialog.show("erreur", "contenu est vide", "", cancelText)
//        }
        commenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                Commentaire c = new Commentaire();
                c.setIdUser(Session.getCurrentSession());
                c.setIdCentre(ct.getId());
                c.setContenu(commentaireTXT.getText());
                ServiceCentreT sct = new ServiceCentreT();
                sct.ajoutCommentaire(c);
                f.refreshTheme();

            }
        });

        Cs.add(commenter);

        ct2.add(Cs);

        f.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CentreToilettageForm h = new CentreToilettageForm();
                h.getF().show();
            }
        });
        Label commentaireLab = new Label("les Commentaires : ");
        commentaireLab.getAllStyles().setFgColor(0x5F4747);
        f.add(commentaireLab);
        f.add(ct2);
        
        ServiceCommentaire sc = new ServiceCommentaire();
        List<Commentaire> listR = sc.getAllCommentaires();
         ServicesVeterinaire sv= new ServicesVeterinaire();
        
        for (Commentaire r : listR)
        {
            Container c1 = new Container(BoxLayout.y());
            c1.setUIID("ContainerCommentaire");
            c1.setWidth(200);
            String name= sv.getName(r.getId()).get(0);
            SpanLabel l = new SpanLabel(name);
            l.getAllStyles().setFgColor(0x5F4747);
            
            SpanLabel d = new SpanLabel(r.getContenu());
            d.getAllStyles().setFgColor(0x000000);
            
            c1.add(l);
            c1.add(d);
            f.add(c1);
            //f.refreshTheme(true);
        }

        
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
