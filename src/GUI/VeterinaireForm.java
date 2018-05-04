/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.User;
import Service.ServicesVeterinaire;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mimouna
 */
public class VeterinaireForm {

    private Form f;
    private Resources theme;
    private UIBuilder uiBuilder;
    private Container c;

    public VeterinaireForm() {
        f = new Form("veterinaire");
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

        ServicesVeterinaire sv = new ServicesVeterinaire();
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        List<User> listR = sv.findVeterinaire();
        for (User r : listR) {

            Container img = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            //img.add(theme.getImage("a.png"));
            EncodedImage enc = null;
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
            }
            Image image = URLImage.createToStorage(enc, r.getNom(), "http://localhost/Mobile/PETMYPETemna/web/images/" + r.getNom() + ".jpg");
            img.add(image);

            Container info = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container info2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label nom = new Label(r.getNom());
            Label prenom = new Label(r.getPrenom());
            Label adresse = new Label(r.getAdresse());
            // Label telephone = new Label (r.getTelephone());

            info.add(nom);
            info.add(prenom);
            info.add(adresse);
            Label imge = new Label(image.fill(150, 150));
            info.setLeadComponent(nom);
            info2.add(imge);
            f.add(info2);
            f.add(info);
            
            
            
            nom.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        VeterinaireDetailForm p = new VeterinaireDetailForm(r);
                        p.getF().show();
                    } catch (IOException ex) {
                    }
                }
            });

        }

        Button stat = new Button("stat");
        f.add(stat);
        stat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServicesVeterinaire sv = new ServicesVeterinaire();
                System.out.println(sv.nbreRDV().get(0));

                List<User> liste = new ArrayList<User>();
                liste = sv.getStat();
                double[] values = new double[]{12, 14, 11, 10, 19};
                
                int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
                DefaultRenderer renderer = buildCategoryRenderer(colors);
                renderer.setZoomButtonsVisible(true);
                renderer.setZoomEnabled(true);
                renderer.setChartTitleTextSize(20);
                renderer.setDisplayValues(true);
                renderer.setShowLabels(true);
                SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
                r.setGradientEnabled(true);
                r.setGradientStart(0, ColorUtil.BLUE);
                r.setGradientStop(0, ColorUtil.GREEN);
                r.setHighlighted(true);

                // Create the chart ... pass the values and renderer to the chart object.
                PieChart chart = new PieChart(buildCategoryDataset(liste), renderer);
                ChartComponent c = new ChartComponent(chart);
                Form f = new Form("Budget", new BorderLayout());
                f.add(BorderLayout.CENTER, c);
                f.show();
            }

            private DefaultRenderer buildCategoryRenderer(int[] colors) {
                DefaultRenderer renderer = new DefaultRenderer();
                renderer.setLabelsTextSize(15);
                renderer.setLegendTextSize(15);
                renderer.setMargins(new int[]{20, 30, 15, 0});
                for (int color : colors) {
                    SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                    r.setColor(color);
                    renderer.addSeriesRenderer(r);
                }
                return renderer;
            }

            protected CategorySeries buildCategoryDataset(List<User> l) {
                CategorySeries series = new CategorySeries("Reservation");
                int k = 0;
                for (User value : l) {
                    series.add(value.getNom() + ++k, value.getStat());
                }

                return series;
            }

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
