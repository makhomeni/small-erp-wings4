package com.wings4.util;

import com.jidesoft.plaf.LookAndFeelFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/16/12
 * Time: 11:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class LookAndFeelTheme extends LookAndFeelFactory {

    public static void setTheme(int theme){
        installJideExtension(theme);
    }
}
