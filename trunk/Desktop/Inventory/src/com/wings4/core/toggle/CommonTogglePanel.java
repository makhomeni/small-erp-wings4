package com.wings4.core.toggle;

import com.wings4.core.panel.PurchaseOrderButtonPanel;
import com.wings4.util.IconFactory;
import com.wings4.util.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/20/12
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */

public class CommonTogglePanel extends AppTogglePanel{
    
    private String menuName;
    private String toggleText;
    private String toggleIcon;
    private String toggleBannerIcon;
    private String toggleDescription;
    private JPanel buttonPanel;

    public CommonTogglePanel(String menuName, String toggleText, String toggleIcon, String toggleBannerIcon,
                             String toggleDescription, JPanel buttonPanel) {
        this.menuName = menuName;
        this.toggleText = toggleText;
        this.toggleIcon = toggleIcon;
        this.toggleBannerIcon = toggleBannerIcon;
        this.toggleDescription = toggleDescription;
        this.buttonPanel = buttonPanel;
    }

    public CommonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab(this.menuName, IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(buttonPanel), this.menuName);

    }

    public String getToggleText(){
        return this.toggleText;
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon(this.toggleIcon);
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon(this.toggleBannerIcon);
    }

    public String getToggleDescription(){
        return toggleDescription;
    }
}
