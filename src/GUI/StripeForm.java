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
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;

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
        c = uiBuilder.createContainer(theme, "HomeGUI");
        f=(Form)c;
        f.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PanierForm hi=new PanierForm();
                    hi.getF().show();
                }
            });
        TextField name=new TextField();
        name.setHint("Nom");
        TextField cc=new TextField();
        cc.setHint("Numero carte bancaire");
        TextField cvv=new TextField();
        cvv.setHint("code cryptogramme");
        TextField exp_m=new TextField();
        exp_m.setHint("Mois d'expiration");
        TextField exp_y=new TextField();
        exp_y.setHint("Année d'expiration");
        
        
        Button commander=new Button("Valider");
        
        commander.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceProduit sp=new ServiceProduit();
                PaymentOrder payment = new PaymentOrder(cc.getText(), cvv.getText(), exp_m.getText(), exp_y.getText(), total);
                sp.createPayment(payment);
                
                Dialog.show("Payment", "Payement éffectué avec succée !", "ok",null);
            }
        });
        Validator validator=new Validator();
        validator.addConstraint(name, new LengthConstraint(1)).addConstraint(cc, new LengthConstraint(16)).addConstraint(cvv, new LengthConstraint(3)).addConstraint(exp_m, new LengthConstraint(2)).addConstraint(exp_y, new LengthConstraint(4));
        validator.addSubmitButtons(commander);
        validator.setShowErrorMessageForFocusedComponent(true);
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
