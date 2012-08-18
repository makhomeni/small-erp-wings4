package com.wings4.client;

import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoButtonTogglePanel extends DemoTogglePanel {

    public DemoButtonTogglePanel(){
    }

    public void initialize(){
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Button", IconFactory.getSwingIcon("component/button_16.png"),
                new DemoToggleTemplate(new DemoButtonPanel()), "Button");
//        toggleTabbedPane.addTab("ToggleButton", IconFactory.getSwingIcon("component/toggle_button_16.png"),
//                new DemoToggleTemplate(new DemoToggleButtonPanel()), "ToggleButton");
//        toggleTabbedPane.addTab("MenuButton", IconFactory.getSwingIcon("component/menu_16.png"),
//                new DemoToggleTemplate(new DemoMenuButtonPanel()), "MenuButton");
//        toggleTabbedPane.addTab("SplitButton", IconFactory.getSwingIcon("component/popup_menu_16.png"),
//                new DemoToggleTemplate(new DemoSplitButtonPanel()), "SplitButton");
//        toggleTabbedPane.addTab("SelectorMenuButton", IconFactory.getSwingIcon("component/popup_menu_16.png"),
//                new DemoToggleTemplate(new DemoSelectorMenuButtonPanel()), "SelectorMenuButton");
    }

    public String getToggleText(){
        return "Button";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("component/button_32.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("component/button_32.png");
    }

    public String getToggleDescription(){
        return "Multi-style Button ToggleButton MenuButton & SplitButton";
    }

}
