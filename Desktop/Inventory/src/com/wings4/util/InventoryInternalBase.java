/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.util;

import com.jidesoft.plaf.LookAndFeelFactory;

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
        LookAndFeelManager.setAlloyLookAndFeel();
        LookAndFeelFactory.installJideExtension(LookAndFeelFactory.ECLIPSE3X_STYLE);
    }
    
    public void setDefaultBehavior(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        URL imageUrl = ClassLoader.getSystemResource("com/wings4/resource/logo.png");
        
        Icon favIcon = new javax.swing.ImageIcon (ClassLoader.getSystemResource("com/wings4/resource/logo.png"));
        this.setFrameIcon(favIcon);

    }
    
}
