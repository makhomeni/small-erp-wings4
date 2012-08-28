package com.wings4.core.toggle;

import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.core.panel.VendorButtonPanel;
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
        toggleTabbedPane.addTab("Vendor", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new VendorButtonPanel()), "Vendor");

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

