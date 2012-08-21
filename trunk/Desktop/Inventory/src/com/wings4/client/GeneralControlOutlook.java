package com.wings4.client;

import com.nepxion.swing.button.ButtonManager;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class GeneralControlOutlook extends DemoToggleOutlook {
    public GeneralControlOutlook(){
        GeneralToggleActionButton dashboardButton = new GeneralToggleActionButton(new DashboardButtonTogglePanel());
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
