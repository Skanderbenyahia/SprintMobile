/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.CentreDressage;
import Entity.Session;
import Entity.jaimeD;
import Service.CentreDressageService;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import com.codename1.googlemaps.MapContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jabou
 */
public class CentreDressageDetailForm {

    private Resources theme;
    private Container c;
    private Container mapC;
    private UIBuilder uiBuilder;
    private Form centreDetail;

    private static final String HTML_API_KEY = "AIzaSyBfMSkwICqtZsx26_5hVkFYVakW1c1fUTQ";

    public CentreDressageDetailForm(CentreDressage cD) {

        try {
            CentreDressageService s = new CentreDressageService();
            centreDetail = new Form();
            uiBuilder = new UIBuilder();
            theme = UIManager.initFirstTheme("/LoginForm");
            c = uiBuilder.createContainer(theme, "HomeGUI");
            centreDetail = (Form) c;

            //menu
            Toolbar tb = centreDetail.getToolbar();
            tb.addCommandToLeftBar("Back", theme.getImage("back-command.png"), e -> new CentreDressageForm().getCentreD().show());

            Container ct = new Container(BoxLayout.y());
            Container ct2 = new Container(BoxLayout.y());
            Container ct3 = new Container(BoxLayout.y());
            
            Label Libelle = new Label(cD.getLibelle());
            SpanLabel sp = new SpanLabel("Description : " + cD.getDescription());
            Label adresse = new Label("Adresse :" + cD.getAdresse());
            Label tel = new Label("Telephone " + String.valueOf(cD.getTel()));
            Button map = new Button("Map");
            Button jaime = new Button();
            jaime.setIcon(theme.getImage("jaime.png"));
            Button jaimepas = new Button();
            jaimepas.setIcon(theme.getImage("jaimepas.png"));
            EncodedImage enc = null;
            enc = EncodedImage.create("/giphy.gif");
            Image image = URLImage.createToStorage(enc, cD.getImage(), "http://localhost/Mobile/PETMYPETSALY/images/" + cD.getImage());
            Label img = new Label(image.fill(150, 150));

            ct.add(img);
            ct2.add(Libelle);
            ct2.add(adresse);
            ct2.add(sp);
            ct2.add(tel);
            ct3.add(jaime);
            ct3.add(jaimepas);
            ct2.add(map);
            centreDetail.add(ct);
            centreDetail.add(ct2);
            centreDetail.add(ct3);

            //condition jaime 
            List<jaimeD> jaimes = new ArrayList<>();
            jaimes = s.Conditionjaime(cD.getId(), Session.getCurrentSession());
            if (jaimes.isEmpty() == true) {
                jaime.setVisible(true);
                jaimepas.setVisible(false);
                centreDetail.refreshTheme();
            } else {
                jaime.setVisible(false);
                jaimepas.setVisible(true);
                centreDetail.refreshTheme();
            }

            //jaime
            jaime.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    s.Jaime(cD.getId(), Session.getCurrentSession(), 1, 0);
                    centreDetail.refreshTheme();
                    jaime.setVisible(false);
                    jaimepas.setVisible(true);
                }
            });

            //jaimeplus
            jaimepas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CentreDressageService s = new CentreDressageService();
                    s.JaimePas(Session.getCurrentSession(), cD.getId(), 0, 1);
                    centreDetail.refreshTheme();
                    jaime.setVisible(true);
                    jaimepas.setVisible(false);
                }
            });

            //map
            map.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    mapC = uiBuilder.createContainer(theme, "HomeGUI");
                    Form hi = new Form();
                    hi = (Form) mapC;

                    hi.setLayout(new BorderLayout());
                    MapContainer cnt = new MapContainer(HTML_API_KEY);
                    cnt.setCameraPosition(new Coord(cD.getLat(), cD.getLng()));
                    System.out.println(cD.getLat());
                    System.out.println(cD.getLng());
                    Style s = new Style();
                    s.setFgColor(0xff0000);
                    s.setBgTransparency(0);
                    FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));
                    cnt.addMarker(
                            EncodedImage.createFromImage(markerImg, false),
                            cnt.getCameraPosition(),
                            "Hi marker",
                            "Optional long description",
                            x -> {
                                ToastBar.showMessage("You clicked " + cD.getLibelle(), FontImage.MATERIAL_PLACE);
                            }
                    );

                    cnt.addTapListener(e -> {
                        TextField enterName = new TextField();
                        Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
                        InteractionDialog dlg = new InteractionDialog("Add Marker");
                        dlg.getContentPane().add(wrapper);
                        enterName.setDoneListener(e2 -> {
                            String txt = enterName.getText();
                            cnt.addMarker(
                                    EncodedImage.createFromImage(markerImg, false),
                                    cnt.getCoordAtPosition(e.getX(), e.getY()),
                                    enterName.getText(),
                                    "",
                                    e3
                                    -> {
                                ToastBar.showMessage("" + cD.getLibelle(), FontImage.MATERIAL_PLACE);
                            }
                            );
                            dlg.dispose();
                        });
                        dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
                        enterName.startEditingAsync();
                    });
                    Container root = LayeredLayout.encloseIn(BorderLayout.center(cnt));
                    hi.add(BorderLayout.CENTER, root);
                    hi.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), e -> new CentreDressageForm().getCentreD().show());
                    hi.show();
                }
            }
            );
        } catch (IOException ex) {
        }
    }

    public Form getCentreDetail() {
        return centreDetail;
    }

    public void setCentreDetail(Form centreDetail) {
        this.centreDetail = centreDetail;
    }

}
