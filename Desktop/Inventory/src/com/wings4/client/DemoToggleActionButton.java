package com.wings4.client;

import com.nepxion.swing.toggle.JToggleActionButton;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoToggleActionButton extends JToggleActionButton{
    public DemoToggleActionButton(DemoTogglePanel togglePanel){
        super(DemoToggleContentPanel.getInstance(), togglePanel);
    }
}
