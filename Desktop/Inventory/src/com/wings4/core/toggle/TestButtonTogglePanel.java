package com.wings4.core.toggle;

import com.wings4.core.panel.DemoButtonPanel;
import com.wings4.core.panel.DemoToggleButtonPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestButtonTogglePanel  extends AppTogglePanel {

    public TestButtonTogglePanel(){
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
        return "TestTogol";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("dashboard.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("dashboard.png");
    }

    public String getToggleDescription(){
        return "TestTogol";
    }

}
