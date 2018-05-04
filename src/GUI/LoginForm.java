/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.User;
import Service.ServiceUser;
import com.codename1.components.WebBrowser;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author jabou
 */
public class LoginForm {

    private Resources theme;
    private Container c;
    private UIBuilder uiBuilder;
    private Form login;
    private TextField username;
    private TextField password;
    private Button connecter;
    private Button inscription;

    public LoginForm() {

        login = new Form();
        uiBuilder = new UIBuilder();
        theme = UIManager.initFirstTheme("/LoginForm");
        c = uiBuilder.createContainer(theme, "LoginGUI");
        username = (TextField) uiBuilder.findByName("UsernameTF", c);
        password = (TextField) uiBuilder.findByName("PasswordTF", c);
        password.setConstraint(TextField.PASSWORD);
        connecter = (Button) uiBuilder.findByName(("connercterButton"), c);
        inscription = (Button) uiBuilder.findByName(("inscriptionButton"), c);
        login = (Form) c;

        connecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((username.getText().equalsIgnoreCase("")) && (password.getText().equalsIgnoreCase(""))) {
                    Dialog.show("Error!", "Saisie le nom d'utilisateur et mot de passe", "Ok", null);
                } else {
                    ServiceUser us = new ServiceUser();
                    User u = new User();
                    u = us.authentificate(username.getText());
                    if (u.getId() == 0) {
                        username.setText("");
                        password.setText("");
                        Dialog.show("Error!", "Login ou mot de passe incorrect!", "Ok", null);
                    } else {
                        String hashed = u.getPassword().substring(0, 2) + "a" + u.getPassword().substring(3);
                        if (BCrypt.checkpw(password.getText(), hashed)) {
                            HomeForm hf = new HomeForm();
                            hf.getHome().show();
                        }

                    }

                }
            }
        });

        /*inscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                WebDriver driver = new FirefoxDriver();
                driver.get("www.google.fr");

            }
        });*/
    }

    public Form getLogin() {
        return login;
    }

    public void setLogin(Form login) {
        this.login = login;
    }

}
