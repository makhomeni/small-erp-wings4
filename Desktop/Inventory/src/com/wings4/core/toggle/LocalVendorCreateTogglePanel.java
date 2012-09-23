package com.wings4.core.toggle;

import com.wings4.core.panel.InventoryCreateButtonPanel;
import com.wings4.core.panel.LocalVendorCreateButtonPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.InventoryConstants;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/23/12
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocalVendorCreateTogglePanel extends AppTogglePanel {

    public LocalVendorCreateTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab(InventoryConstants.LOCAL_VENDOR_ADD, IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new LocalVendorCreateButtonPanel()), InventoryConstants.LOCAL_VENDOR_ADD);

    }

    public String getToggleText(){
        return InventoryConstants.LOCAL_VENDOR_ADD;
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("inventory.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("inventory.png");
    }

    public String getToggleDescription(){
        return InventoryConstants.LOCAL_VENDOR_ADD;
    }
}

