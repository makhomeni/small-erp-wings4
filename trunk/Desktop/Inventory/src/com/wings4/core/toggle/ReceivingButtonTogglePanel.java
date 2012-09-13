package com.wings4.core.toggle;

import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.core.panel.ReceivingButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 8:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReceivingButtonTogglePanel extends AppTogglePanel {

    public ReceivingButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Receiving", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new ReceivingButtonPanel()), "Receiving");

    }

    public String getToggleText(){
        return "Receiving";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("picking.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("picking.png");
    }

    public String getToggleDescription(){
        return "Receiving";
    }
}
