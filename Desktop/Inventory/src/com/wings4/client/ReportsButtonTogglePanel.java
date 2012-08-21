package com.wings4.client;

import com.wings4.util.IconFactory;
import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/21/12
 * Time: 10:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportsButtonTogglePanel extends DemoTogglePanel {

    public ReportsButtonTogglePanel()
    {
    }

    public void initialize()
    {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Reports", IconFactory.getSwingIcon("reportoo.png"),
                new DemoToggleTemplate(new DemoCheckBoxPanel()), "Reports");
    }

    public String getToggleText()
    {
        return "Reports";
    }

    public Icon getToggleIcon()
    {
        return IconFactory.getSwingIcon("report.png");
    }

    public Icon getToggleBannerIcon()
    {
        return IconFactory.getSwingIcon("report.png");
    }

    public String getToggleDescription()
    {
        return "All Kinds of Reports";
    }
}
