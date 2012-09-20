package com.wings4.core.toggle;

import com.wings4.core.panel.PurchaseCreateButtonPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseCreateTogglePanel extends AppTogglePanel {

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Create Purchase", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new PurchaseCreateButtonPanel()), "Create Purchase");

    }

    public String getToggleText(){
        return "Purchase";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("purchase.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("purchase.png");
    }

    public String getToggleDescription(){
        return "Purchase";
    }
}
