package com.wings4.core.toggle;

import com.wings4.core.panel.PurchaseButtonPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.InventoryConstants;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 8:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseButtonTogglePanel extends AppTogglePanel {

    public PurchaseButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab(InventoryConstants.PURCHASE, IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new PurchaseButtonPanel()), InventoryConstants.PURCHASE);

    }

    public String getToggleText(){
        return InventoryConstants.PURCHASE;
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("purchase.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("purchase.png");
    }

    public String getToggleDescription(){
        return InventoryConstants.PURCHASE;
    }
}
