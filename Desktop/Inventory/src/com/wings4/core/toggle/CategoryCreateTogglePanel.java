package com.wings4.core.toggle;

import com.wings4.util.JEclipseTabbedPane;
import com.wings4.core.panel.CategoryCreateButtonPanel;
import com.wings4.util.IconFactory;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/24/12
 * Time: 1:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryCreateTogglePanel extends AppTogglePanel {

    @Override
    public void initialize() {
        JEclipseTabbedPane toggleTabbedPane = getToggleTabbedPane();
        toggleTabbedPane.addTab("Category", IconFactory.getSwingIcon("component/button_16.png"),
                new AppToggleTemplate(new CategoryCreateButtonPanel()), "Category");

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
