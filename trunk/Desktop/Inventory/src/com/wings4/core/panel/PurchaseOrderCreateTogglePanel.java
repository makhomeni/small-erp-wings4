package com.wings4.core.panel;

import com.wings4.core.toggle.AppTogglePanel;
import com.wings4.core.toggle.AppToggleTemplate;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderCreateTogglePanel extends AppTogglePanel {

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Customer", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new PurchaseOrderCreateButtonPanel()), "Purchase Order");

    }

    public String getToggleText(){
        return "Purchase Order";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("purchase.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("purchase.png");
    }

    public String getToggleDescription(){
        return "Purchase Order";
    }
}
