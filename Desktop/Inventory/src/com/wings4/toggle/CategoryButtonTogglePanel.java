package com.wings4.toggle;

import com.wings4.panel.CategoryButtonPanel;
import com.wings4.util.IconFactory;
import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/21/12
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryButtonTogglePanel extends AppTogglePanel {

    public CategoryButtonTogglePanel() {
    }

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Category", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new CategoryButtonPanel()), "Category");

    }

    public String getToggleText(){
        return "Category";
    }

    public Icon getToggleIcon(){
        return IconFactory.getSwingIcon("category.png");
    }

    public Icon getToggleBannerIcon(){
        return IconFactory.getSwingIcon("category.png");
    }

    public String getToggleDescription(){
        return "Category";
    }
}
