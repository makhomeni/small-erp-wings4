package com.wings4.client;

import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/21/12
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryButtonTogglePanel extends DemoTogglePanel {

    public CategoryButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Button", IconFactory.getSwingIcon("component/button_16.png"),
                new DemoToggleTemplate(new CategoryCreate()), "Button");

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
