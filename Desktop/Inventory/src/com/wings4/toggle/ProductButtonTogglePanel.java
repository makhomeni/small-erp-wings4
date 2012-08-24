package com.wings4.toggle;

import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;
import com.wings4.panel.ProductButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/22/12
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductButtonTogglePanel extends AppTogglePanel {

    public ProductButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Product", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new ProductButtonPanel()), "Product");

    }

    public String getToggleText(){
        return "Product";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("product.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("product.png");
    }

    public String getToggleDescription(){
        return "Product";
    }
}
