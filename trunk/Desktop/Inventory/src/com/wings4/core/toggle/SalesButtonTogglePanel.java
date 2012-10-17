package com.wings4.core.toggle;

import com.wings4.core.panel.PurchaseOrderButtonPanel;
import com.wings4.core.panel.SalesButtonPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 8:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class SalesButtonTogglePanel extends AppTogglePanel {

    public SalesButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Sales", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new SalesButtonPanel()), "Sales");

    }

    public String getToggleText(){
        return "Sales";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("purchase_order.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("purchase_order.png");
    }

    public String getToggleDescription(){
        return "Sales";
    }
}
