package com.wings4.core.toggle;

import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.core.panel.CustomerButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/27/12
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerButtonTogglePanel extends AppTogglePanel {

    public CustomerButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Customer", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new CustomerButtonPanel()), "Customer");

    }

    public String getToggleText(){
        return "Customer";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("customer.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("customer.png");
    }

    public String getToggleDescription(){
        return "Customer";
    }
}
