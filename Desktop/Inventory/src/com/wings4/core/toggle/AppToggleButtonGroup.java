package com.wings4.core.toggle;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppToggleButtonGroup extends ButtonGroup{
    private static AppToggleButtonGroup buttonGroup;

    public static AppToggleButtonGroup getInstance(){
        if (buttonGroup == null){
            buttonGroup = new AppToggleButtonGroup();
        }

        return buttonGroup;
    }

    private AppToggleButtonGroup(){
    }
}
