package com.wings4.client;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoToggleButtonGroup extends ButtonGroup{
    private static DemoToggleButtonGroup buttonGroup;

    public static DemoToggleButtonGroup getInstance(){
        if (buttonGroup == null){
            buttonGroup = new DemoToggleButtonGroup();
        }

        return buttonGroup;
    }

    private DemoToggleButtonGroup(){
    }
}
