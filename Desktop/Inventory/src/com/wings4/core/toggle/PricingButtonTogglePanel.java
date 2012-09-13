package com.wings4.core.toggle;

import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.core.panel.PricingButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/27/12
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class PricingButtonTogglePanel extends AppTogglePanel {

    public PricingButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Pricing", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new PricingButtonPanel()), "Pricing");

    }

    public String getToggleText(){
        return "Pricing";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("pricing.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("pricing.png");
    }

    public String getToggleDescription(){
        return "Pricing";
    }
}

