/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author ronnie
 */
public class InventoryInternalBase extends JInternalFrame {

    public InventoryInternalBase(String title) {
        super(title);
        setDefaultBehavior();
        setLocation(100,100);
        setClosable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeel();
    }

    public InventoryInternalBase() {
        setDefaultBehavior();
        setLocation(100,100);
        setClosable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
        
        Icon favIcon = new javax.swing.ImageIcon (ClassLoader.getSystemResource("com/wings4/resource/logo.png"));
        this.setFrameIcon(favIcon);

    }
    
}
