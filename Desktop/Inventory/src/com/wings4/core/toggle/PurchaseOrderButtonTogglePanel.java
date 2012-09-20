package com.wings4.core.toggle;

import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.core.panel.PurchaseOrderButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 8:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderButtonTogglePanel extends AppTogglePanel {

    public PurchaseOrderButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Purchase Order", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new PurchaseOrderButtonPanel()), "Purchase Order");

    }

    public String getToggleText(){
        return "Purchase Order";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("purchase_order.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("purchase_order.png");
    }

    public String getToggleDescription(){
        return "Purchase Order";
    }
}
