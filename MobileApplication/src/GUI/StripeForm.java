/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.PaymentOrder;
import Service.ServiceProduit;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author bn-sk
 */
public class StripeForm {

    private Form f;
    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
  
    

    public StripeForm(Double total) {
        
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "LoginGUI");
        
        TextField name=new TextField();
        TextField cc=new TextField();
        TextField cvv=new TextField();
        TextField exp_m=new TextField();
        TextField exp_y=new TextField();
        Button commander=new Button("Valider");
        
        commander.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceProduit sp=new ServiceProduit();
                PaymentOrder payment = new PaymentOrder(cc.getText(), cvv.getText(), exp_m.getText(), exp_y.getText(), total);
                sp.createPayment(payment);
            }
        });
        f.add(name);
        f.add(cc);
        f.add(cvv);
        f.add(exp_m);
        f.add(exp_y);
        f.add(commander);
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
