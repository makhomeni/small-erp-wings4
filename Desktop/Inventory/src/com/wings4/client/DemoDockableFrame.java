package com.wings4.client;

import com.nepxion.swing.framework.JFrameWorkFrame;
import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.tray.JTray;
import com.wings4.util.InventoryConstants;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoDockableFrame extends JFrameWorkFrame {

    public DemoDockableFrame() {
        super("Nepxion Swing", IconFactory.getSwingIcon(InventoryConstants.resourceDirectory.concat("category.png"))); // new Dimension(850, 650)

        Dashboard hierarchy = new Dashboard();
        setHierarchy(hierarchy);
    }
    
    public static void main(String[] args){

        new DataContextInitializer().initialize();
        new UIContextInitializer().initialize();
        new RibbonContextInitializer().initialize();

        final DemoSplashWindow splashWindow = new DemoSplashWindow();
        splashWindow.setVisible(true);

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DemoDockableFrame frame = new DemoDockableFrame();
                frame.setExtendedState(MAXIMIZED_BOTH);

                //JTray tray = new JTray(frame);

                splashWindow.setVisible(false);
                //tray.setVisible(true);
                frame.setVisible(true);
                frame.toFront();
            }
        });
    }
}
