package com.wings4.core.toggle;

import com.wings4.core.panel.DemoCheckBoxPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class WarehouseButtonTogglePanel extends AppTogglePanel {

    public WarehouseButtonTogglePanel()
    {
    }

    public void initialize()
    {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("CheckBox", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new DemoCheckBoxPanel()), "CheckBox");
    }

    public String getToggleText()
    {
        return "Warehouse";
    }

    public Icon getToggleIcon()
    {
        return IconFactory.getSwingIcon("warehouse.png");
    }

    public Icon getToggleBannerIcon()
    {
        return IconFactory.getSwingIcon("warehouse.png");
    }

    public String getToggleDescription()
    {
        return "Warehouse List";
    }
}
