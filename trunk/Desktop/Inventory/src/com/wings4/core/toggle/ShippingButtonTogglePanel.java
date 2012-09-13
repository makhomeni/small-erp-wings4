package com.wings4.core.toggle;

import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.core.panel.ShippingButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/27/12
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShippingButtonTogglePanel extends AppTogglePanel {

    public ShippingButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Shipping", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new ShippingButtonPanel()), "Shipping");

    }

    public String getToggleText(){
        return "Shipping";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("shipping.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("shipping.png");
    }

    public String getToggleDescription(){
        return "Shipping";
    }
}

