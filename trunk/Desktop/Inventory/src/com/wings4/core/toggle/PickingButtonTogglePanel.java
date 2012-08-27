package com.wings4.core.toggle;

import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/27/12
 * Time: 11:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class PickingButtonTogglePanel extends AppTogglePanel {

    public PickingButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Picking", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new CategoryButtonPanel()), "Picking");

    }

    public String getToggleText(){
        return "Picking";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("picking.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("picking.png");
    }

    public String getToggleDescription(){
        return "Picking";
    }
}
