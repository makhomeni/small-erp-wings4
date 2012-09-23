package com.wings4.core.toggle;

import com.wings4.core.panel.ExternalVendorCreateButtonPanel;
import com.wings4.core.panel.LocalVendorCreateButtonPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.InventoryConstants;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/23/12
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExternalVendorCreateTogglePanel extends AppTogglePanel {

    public ExternalVendorCreateTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab(InventoryConstants.EXTERNAL_VENDOR_ADD, IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new ExternalVendorCreateButtonPanel()), InventoryConstants.EXTERNAL_VENDOR_ADD);

    }

    public String getToggleText(){
        return InventoryConstants.EXTERNAL_VENDOR_ADD;
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("inventory.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("inventory.png");
    }

    public String getToggleDescription(){
        return InventoryConstants.EXTERNAL_VENDOR_ADD;
    }
}
