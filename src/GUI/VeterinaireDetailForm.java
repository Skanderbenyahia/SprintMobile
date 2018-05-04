/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Session;
import Entity.User;
import Entity.reservation_veterinaire;
import Service.ServicesVeterinaire;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.spinner.TimeSpinner;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mimouna
 */
public class VeterinaireDetailForm {

    private Form f;
    private Resources theme;
    private Label nom, prenom, adresse, image;
    private Button ajouter;
    private Container c;

    public VeterinaireDetailForm(User u) throws IOException {

        f = new Form();
        UIBuilder uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f = (Form) c;

        Toolbar tb = f.getToolbar();
        tb.addCommandToLeftBar("Back", theme.getImage("back-command.png"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VeterinaireForm h = new VeterinaireForm();
                h.getF().show();
            }
        });

        Container ct2 = new Container(BoxLayout.y());

        SpanLabel spNom = new SpanLabel(u.getNom());
        nom = new Label(u.getNom());

        SpanLabel spPrenom = new SpanLabel(u.getPrenom());
        prenom = new Label(u.getPrenom());

        SpanLabel spAdresse = new SpanLabel(u.getAdresse());
        adresse = new Label(u.getAdresse());

        Container img = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        EncodedImage enc = null;
        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
        }
        Image image = URLImage.createToStorage(enc, u.getNom(), "http://localhost/PETMYPET/web/images/" + u.getNom() + "jpg");

        Button rdv = new Button("rendez-vous");
        rdv.setWidth(200);
        /* temps & date */

        Picker p = new Picker();
        Date date_courante = new Date();

        TimeSpinner t = new TimeSpinner();

        ComboBox<String> description = new ComboBox<>();
        description.addItem("operation");
        description.addItem("consultation");
        ct2.add(spNom);
        ct2.add(spPrenom);
        ct2.add(spAdresse);
        ct2.add(image);
        ct2.add(p);
        ct2.add(t);
        ct2.add(description);
        ct2.add(rdv);

        rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Date date_courante = new Date();

                SimpleDateFormat formatT = new SimpleDateFormat("yyyy-MM-dd");
                String date_debut = formatT.format(p.getDate());
                ServicesVeterinaire s = new ServicesVeterinaire();
                List<reservation_veterinaire> r = new ArrayList<>();
                r = s.datepetexistant(date_debut, u.getId());

                int check = (int) (p.getDate().getTime() - date_courante.getTime());
                if (check < 0) {
                    System.out.println(date_debut.compareTo(date_debut) + " test 4");
                    Dialog.show("Erors", "date deja passée", "ok", null);

                } else {

                    if (r.isEmpty()) {
                        s.reserverVeterinaire(date_debut, u.getId(), Session.getCurrentSession(), description.getSelectedItem());
                        Dialog.show("good", " Rendez-vous pris avec succés", "ok", null);
                    } else {
                        Dialog.show("Erors", "Date deja prise", "ok", null);
                    }
                }
            }
        });

        f.add(ct2);

        Button pdf = new Button("PDF");
        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    OutputStream os = Storage.getInstance().createOutputStream("emna.pdf");
                    com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                    PdfWriter.getInstance(document, os);
                    document.open();
                    document.add(new Paragraph("Détails de veternaire ", FontFactory.getFont(FontFactory.TIMES)));
                    document.add(new Paragraph(new Date().toString()));
                    document.add(new Paragraph("-----------------------------------------------------------------"));
                    com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
                    com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Details du veterinaire"));
                    cell.setColspan(4);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.GREEN);
                    table.addCell(cell);
                    table.addCell("nom");
                    table.addCell(u.getNom());
                    table.addCell("Prenom");
                    table.addCell(u.getPrenom());
                    table.addCell("telephone");
                    table.addCell(u.getTelehone() + "");
                    document.add(table);
                    document.close();

                } catch (DocumentException | IOException ex) {
                }
            }
        });

        f.add(pdf);
    }

    /* back */
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
