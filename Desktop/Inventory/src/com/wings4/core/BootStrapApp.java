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
}
