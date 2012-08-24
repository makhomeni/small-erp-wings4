package com.wings4.toggle;

import com.wings4.panel.DemoButtonPanel;
import com.wings4.panel.DemoToggleButtonPanel;
import com.wings4.util.IconFactory;
import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class DashboardButtonTogglePanel extends AppTogglePanel {

    public DashboardButtonTogglePanel(){
    }

    public void initialize(){
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Button", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new DemoButtonPanel()), "Button");
        toggleTabbedPane.addTab("ToggleButton", IconFactory.getSwingIcon("component/toggle_button_16.png"),
                new AppToggleTemplate(new DemoToggleButtonPanel()), "ToggleButton");
//        toggleTabbedPane.addTab("MenuButton", IconFactory.getSwingIcon("component/menu_16.png"),
//                new AppToggleTemplate(new DemoMenuButtonPanel()), "MenuButton");
//        toggleTabbedPane.addTab("SplitButton", IconFactory.getSwingIcon("component/popup_menu_16.png"),
//                new AppToggleTemplate(new DemoSplitButtonPanel()), "SplitButton");
//        toggleTabbedPane.addTab("SelectorMenuButton", IconFactory.getSwingIcon("component/popup_menu_16.png"),
//                new AppToggleTemplate(new DemoSelectorMenuButtonPanel()), "SelectorMenuButton");
    }

    public String getToggleText(){
        return "Dashboard";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("dashboard.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("dashboard.png");
    }

    public String getToggleDescription(){
        return "Dashboard";
    }

}
