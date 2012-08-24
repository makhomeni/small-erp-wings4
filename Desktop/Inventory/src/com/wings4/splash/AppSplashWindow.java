package com.wings4.splash;

import com.wings4.util.IconFactory;
import com.nepxion.swing.splash.JSplashWindow;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppSplashWindow extends JSplashWindow{
    public AppSplashWindow(){
        super(IconFactory.getSwingIcon("splash.png"));
    }
}
