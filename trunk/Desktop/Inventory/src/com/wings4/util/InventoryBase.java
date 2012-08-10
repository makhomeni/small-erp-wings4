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
        
 
        try {
            UIManager.setLookAndFeel("com.peterswing.white.PeterSwingWhiteLookAndFeel");
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

    public InventoryBase(String title) throws HeadlessException {
        super(title);
        setDefaultBehavior();
        setLocation(100,100);
        
        try {
            UIManager.setLookAndFeel("com.peterswing.white.PeterSwingWhiteLookAndFeel");
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
        this.setIconImage(favIcon);
        
        
    }
    
}
