package com.wings4.client;

import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class WarehouseButtonTogglePanel extends DemoTogglePanel {

    public WarehouseButtonTogglePanel()
    {
    }

    public void initialize()
    {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("CheckBox", IconFactory.getSwingIcon("component/button_16.png"),
                new DemoToggleTemplate(new DemoCheckBoxPanel()), "CheckBox");
    }

    public String getToggleText()
    {
        return "CheckBox";
    }

    public Icon getToggleIcon()
    {
        return IconFactory.getSwingIcon("component/check_box_32.png");
    }

    public Icon getToggleBannerIcon()
    {
        return IconFactory.getSwingIcon("component/check_box_32.png");
    }

    public String getToggleDescription()
    {
        return "Multi-style CheckBox";
    }
}
