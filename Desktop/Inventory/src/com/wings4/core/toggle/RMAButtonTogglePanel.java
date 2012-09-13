package com.wings4.core.toggle;

import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryButtonPanel;
import com.wings4.core.panel.RMAButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/27/12
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class RMAButtonTogglePanel  extends AppTogglePanel {

    public RMAButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("RMA", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new RMAButtonPanel()), "RMA");

    }

    public String getToggleText(){
        return "RMA";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("rma.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("rma.png");
    }

    public String getToggleDescription(){
        return "RMA";
    }
}

