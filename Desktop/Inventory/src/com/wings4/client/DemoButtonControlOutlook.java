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
public class DemoButtonControlOutlook extends DemoToggleOutlook {
    public DemoButtonControlOutlook(){
        DemoToggleActionButton buttonButton = new DemoToggleActionButton(new DemoButtonTogglePanel());
        addButton(buttonButton);

//        DemoToggleActionButton checkBoxButton = new DemoToggleActionButton(new DemoCheckBoxTogglePanel());
//        addButton(checkBoxButton);
//
//        DemoToggleActionButton radioButtonButton = new DemoToggleActionButton(new DemoRadioButtonTogglePanel());
//        addButton(radioButtonButton);
//
//        DemoToggleActionButton comboBoxButton = new DemoToggleActionButton(new DemoComboBoxTogglePanel());
//        addButton(comboBoxButton);
//
//        DemoToggleActionButton spinnerButton = new DemoToggleActionButton(new DemoSpinnerTogglePanel());
//        addButton(spinnerButton);
//
//        DemoToggleActionButton slideButton = new DemoToggleActionButton(new DemoSlideTogglePanel());
//        addButton(slideButton);

        ButtonManager.updateUI(this, new Dimension(50, 70), new int[]{VERTICAL, CENTER});

        buttonButton.doClick();
    }
}
