package com.wings4.core.toggle;

import com.wings4.util.InventoryConstants;
import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.ProductButtonPanel;
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
        toggleTabbedPane.addTab(InventoryConstants.PRODUCT, IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new ProductButtonPanel()), InventoryConstants.PRODUCT);

    }

    public String getToggleText(){
        return InventoryConstants.PRODUCT;
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("product.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("product.png");
    }

    public String getToggleDescription(){
        return InventoryConstants.PRODUCT;
    }
}
