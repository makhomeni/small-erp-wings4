package com.wings4.core.toggle;

import com.nepxion.swing.outlookbar.JFlatOutlook;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppToggleOutlook extends JFlatOutlook{
    public AppToggleOutlook(){
    }

    public void addButton(AbstractButton button){
        super.addButton(button);

        AppToggleButtonGroup.getInstance().add(button);
    }
}
