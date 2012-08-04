/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ronnie
 */
public class InventoryInternalBase extends JInternalFrame {

    public InventoryInternalBase(String title) {
        super(title);
    }

    public InventoryInternalBase() {
        setDefaultBehavior();
        setLocation(500,200);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
    }
    
    public void setDefaultBehavior(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        URL imageUrl = ClassLoader.getSystemResource("com/wings4/resource/logo.png");
        
        Image favIcon = kit.createImage(imageUrl);
        //this.setF

    }
    
}
