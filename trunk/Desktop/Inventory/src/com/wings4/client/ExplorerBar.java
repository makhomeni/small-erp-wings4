package com.wings4.client;

import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.outlookbar.JFlatOutlookBar;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExplorerBar extends JFlatOutlookBar {

    private static ExplorerBar explorerBar;

    public static ExplorerBar getInstance(){
        if(explorerBar == null){
            explorerBar = new ExplorerBar();
        }

        return explorerBar;
    }

    private ExplorerBar() {
        addTab("Button Control", IconFactory.getSwingIcon("component/button_16.png"),
                makeScrollPane(new DemoButtonControlOutlook()), "Button Control Component");
    }
}
