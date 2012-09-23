package com.wings4;

import com.wings4.core.BootStrapApp;
import com.wings4.core.context.AppUIContext;
import com.wings4.core.context.DataContextInitializer;
import com.wings4.core.splash.AppSplashWindow;
import com.wings4.util.InventoryBase;
import com.wings4.util.InventoryConstants;
import com.wings4.util.RESTFeed;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author hossaindoula
 */
public class Login extends InventoryBase {

    private static String restEndPoint;

    public static String getRestEndPoint() {
        return restEndPoint;
    }

    public static void setRestEndPoint(String restEndPoint) {
        Login.restEndPoint = restEndPoint;
    }

    public Login() {
        setLocation(500,200);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        usernameLabel = new javax.swing.JLabel();
        usernameText = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        serverAddressLabel = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        passwordText = new javax.swing.JPasswordField();
        portText = new javax.swing.JTextField();
        serverText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);


        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        usernameLabel.setText("Username");

        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });

        passwordLabel.setText("Password");

        serverAddressLabel.setText("Server Address");

        submit.setText("Login");
        submit.setSelected(true);
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        submit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                submitKeyPressed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextActionPerformed(evt);
            }
        });

        portText.setText("8080");
        portText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portTextActionPerformed(evt);
            }
        });

        serverText.setText("localhost");

        jLabel1.setText("Port");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordLabel)
                            .addComponent(usernameLabel)
                            .addComponent(serverAddressLabel)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(portText, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(serverText, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(passwordText, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(usernameText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(submit)
                        .addGap(18, 18, 18)
                        .addComponent(cancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverAddressLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit)
                    .addComponent(cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void usernameTextActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void portTextActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loginButtonAction(){
        String serverAddress = serverText.getText();
        String serverPort = portText.getText();
        JSONObject userObject = new JSONObject();
        String password = new String(passwordText.getPassword());
        try {
            userObject.put("username", usernameText.getText());
            userObject.put("password", password);
        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        restEndPoint = InventoryConstants.serverAddressPrefix.concat(serverAddress.concat(":").concat(serverPort).
                concat("/").concat(InventoryConstants.CONTEXT_PATH).concat(InventoryConstants.EXTRA_PATH));

        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON,
                InventoryConstants.MEDIA_JSON,InventoryConstants.POST,
                restEndPoint, "user");
        restFeed.setJsonObject(userObject);
        try {
            restFeed.restInitialization();
            BootStrapApp frame = new BootStrapApp();
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            this.dispose();
            frame.setVisible(true);
            frame.toFront();
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, InventoryConstants.INVALID_URL_MESSAGE,
                    InventoryConstants.LOGIN_ERROR,JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,InventoryConstants.BAD_CREDENTIAL_MESSAGE,
                    InventoryConstants.LOGIN_ERROR,JOptionPane.ERROR_MESSAGE);
        }

    }

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {
        loginButtonAction();
    }

    private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) {
        loginButtonAction();
    }

    private void submitKeyPressed(KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            loginButtonAction();
        }
    }

    private void formKeyPressed(KeyEvent evt) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        new DataContextInitializer().initialize();
        AppUIContext.initialize();
        //new RibbonContextInitializer().initialize();

        final AppSplashWindow splashWindow = new AppSplashWindow();
        splashWindow.setVisible(true);

        com.jidesoft.utils.Lm.verifyLicense("Wings4", "Inventory", "1234567891012");

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login();

                //BootStrapApp frame = new BootStrapApp();
                //frame.setExtendedState(Frame.MAXIMIZED_BOTH);
                //frame.setIconImage(com.wings4.util.IconFactory.getSwingImage("scm"));

                //JTray tray = new JTray(frame);
                //tray.setAlwaysShowBalloon(true);

                splashWindow.setVisible(false);
                login.setVisible(true);
                //tray.setVisible(true);
                //frame.setVisible(true);
                //frame.toFront();
            }
        });
    }

    private javax.swing.JButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JTextField portText;
    private javax.swing.JLabel serverAddressLabel;
    private javax.swing.JTextField serverText;
    private javax.swing.JButton submit;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameText;
}
