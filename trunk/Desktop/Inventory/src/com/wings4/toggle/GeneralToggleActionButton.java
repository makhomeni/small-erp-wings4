package com.wings4.toggle;

import com.nepxion.swing.toggle.JToggleActionButton;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class GeneralToggleActionButton extends JToggleActionButton{
    public GeneralToggleActionButton(AppTogglePanel togglePanel){
        super(AppToggleContentPanel.getInstance(), togglePanel);
    }
}
