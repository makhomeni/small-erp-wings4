package com.wings4.core.toggle;

import com.wings4.core.panel.CategoryCreateButtonPanel;
import com.wings4.core.panel.CustomerCreateButtonPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerCreateTogglePanel extends AppTogglePanel {

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Customer", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new CustomerCreateButtonPanel()), "Customer");

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
