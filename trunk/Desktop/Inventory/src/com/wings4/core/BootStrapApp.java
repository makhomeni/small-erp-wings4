package com.wings4.core;

import com.nepxion.swing.framework.JFrameWorkFrame;
import com.wings4.core.context.AppUIContext;
import com.wings4.core.context.DataContextInitializer;
import com.wings4.core.splash.AppSplashWindow;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class BootStrapApp extends JFrameWorkFrame {

    public BootStrapApp() {
        super("Wings4 Inventory", IconFactory.getSwingIcon("s_logo.png")); // new Dimension(850, 650)

        Dashboard hierarchy = new Dashboard();
        setHierarchy(hierarchy);
    }
    
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
                BootStrapApp frame = new BootStrapApp();
                frame.setExtendedState(MAXIMIZED_BOTH);
                //frame.setIconImage(com.wings4.util.IconFactory.getSwingImage("scm"));

                //JTray tray = new JTray(frame);
                //tray.setAlwaysShowBalloon(true);

                splashWindow.setVisible(false);
                //tray.setVisible(true);
                frame.setVisible(true);
                frame.toFront();
            }
        });
    }
}
