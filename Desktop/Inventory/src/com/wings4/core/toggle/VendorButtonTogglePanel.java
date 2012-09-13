package com.wings4.core.toggle;

import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.ExternalVendorButtonPanel;
import com.wings4.core.panel.LocalVendorButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 8:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class VendorButtonTogglePanel extends AppTogglePanel {

    public VendorButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Local Vendor", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new LocalVendorButtonPanel()), "Local Vendor");

        toggleTabbedPane.addTab("External Vendor", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new ExternalVendorButtonPanel()), "External Vendor");

    }

    public String getToggleText(){
        return "Vendor";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("local_supplier.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("local_supplier.png");
    }

    public String getToggleDescription(){
        return "Vendor";
    }
}

