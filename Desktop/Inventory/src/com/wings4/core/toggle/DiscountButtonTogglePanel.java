package com.wings4.core.toggle;

import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.core.panel.DiscountButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/27/12
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class DiscountButtonTogglePanel extends AppTogglePanel {

    public DiscountButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Discount", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new DiscountButtonPanel()), "Discount");

    }

    public String getToggleText(){
        return "Discount";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("discount.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("discount.png");
    }

    public String getToggleDescription(){
        return "Discount";
    }
}

