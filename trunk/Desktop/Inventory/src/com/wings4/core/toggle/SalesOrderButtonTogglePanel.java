package com.wings4.core.toggle;

import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/27/12
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SalesOrderButtonTogglePanel extends AppTogglePanel {

    public SalesOrderButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Sales Order", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new CategoryButtonPanel()), "Sales Order");

    }

    public String getToggleText(){
        return "Sales Order";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("sales.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("sales.png");
    }

    public String getToggleDescription(){
        return "Sales Order";
    }
}
