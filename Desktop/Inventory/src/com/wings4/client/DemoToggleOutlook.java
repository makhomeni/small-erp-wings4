package com.wings4.client;

import com.nepxion.swing.outlookbar.JFlatOutlook;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoToggleOutlook extends JFlatOutlook{
    public DemoToggleOutlook(){
    }

    public void addButton(AbstractButton button)
    {
        super.addButton(button);

        DemoToggleButtonGroup.getInstance().add(button);
    }
}
