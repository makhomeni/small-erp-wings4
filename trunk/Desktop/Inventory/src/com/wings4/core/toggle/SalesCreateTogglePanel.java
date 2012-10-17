package com.wings4.core.toggle;

import com.wings4.core.panel.SalesCreatePanel;
import com.wings4.core.panel.SalesOrderCreatePanel;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: MASUM
 * Date: 9/27/12
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class SalesCreateTogglePanel  extends AppTogglePanel {

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Sales", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new SalesCreatePanel()), "Sales");

    }

    public String getToggleText(){
        return "Sales";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("sales.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("sales.png");
    }

    public String getToggleDescription(){
        return "Sales";
    }
}
