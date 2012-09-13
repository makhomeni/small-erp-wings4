package com.wings4.core.outlook;

import com.nepxion.swing.button.ButtonManager;
import com.wings4.core.toggle.*;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestControlOutlook extends AppToggleOutlook {
    public TestControlOutlook(){
        GeneralToggleActionButton dashboardButton = new GeneralToggleActionButton(new TestButtonTogglePanel());
        addButton(dashboardButton);

        GeneralToggleActionButton warehouseButton = new GeneralToggleActionButton(new WarehouseButtonTogglePanel());
        addButton(warehouseButton);

        GeneralToggleActionButton reportsButton = new GeneralToggleActionButton(new ReportsButtonTogglePanel());
        addButton(reportsButton);
//
//        GeneralToggleActionButton radioButtonButton = new GeneralToggleActionButton(new DemoRadioButtonTogglePanel());
//        addButton(radioButtonButton);
//
//        GeneralToggleActionButton comboBoxButton = new GeneralToggleActionButton(new DemoComboBoxTogglePanel());
//        addButton(comboBoxButton);
//
//        GeneralToggleActionButton spinnerButton = new GeneralToggleActionButton(new DemoSpinnerTogglePanel());
//        addButton(spinnerButton);
//
//        GeneralToggleActionButton slideButton = new GeneralToggleActionButton(new DemoSlideTogglePanel());
//        addButton(slideButton);

        ButtonManager.updateUI(this, new Dimension(50, 70), new int[]{VERTICAL, CENTER});

        dashboardButton.doClick();
    }
}
