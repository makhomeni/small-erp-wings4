package com.wings4.core.toggle;

import com.wings4.core.panel.CategoryCreateButtonPanel;
import com.wings4.core.panel.SalesOrderCreatePanel;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class SalseOrderCreateTogglePanel  extends AppTogglePanel {

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Sales Order", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new SalesOrderCreatePanel()), "Sales Order");

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
