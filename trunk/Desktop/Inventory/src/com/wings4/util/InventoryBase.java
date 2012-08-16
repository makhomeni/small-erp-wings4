/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.util;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author ronnie
 */
public class InventoryBase extends JFrame{

    
    public InventoryBase() {
        setDefaultBehavior();
        setLocation(100,100);
        setDefaultLookAndFeel();
    }

    public InventoryBase(String title) throws HeadlessException {
        super(title);
        setDefaultBehavior();
        setLocation(100,100);
        setDefaultLookAndFeel();
    }
    
    public void setDefaultLookAndFeel(){
        com.incors.plaf.alloy.AlloyLookAndFeel.setProperty("alloy.licenseCode", "2012/09/12#hossaindoula@gmail.com#d0b9db#14dqaq");
        try {
            javax.swing.LookAndFeel alloyLnF = new com.incors.plaf.alloy.AlloyLookAndFeel();
            javax.swing.UIManager.setLookAndFeel(alloyLnF);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            // You may handle the exception here
        }
    }
    
    public void setDefaultBehavior(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        URL imageUrl = ClassLoader.getSystemResource("com/wings4/resource/logo.png");
        Image favIcon = kit.createImage(imageUrl);
        this.setIconImage(favIcon);
        
        
    }
    
}
