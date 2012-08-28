package com.wings4.core;

import com.wings4.core.outlook.GeneralControlOutlook;
import com.wings4.core.outlook.MaterialsControlOutlook;
import com.wings4.core.outlook.PurchaseControlOutlook;
import com.wings4.core.outlook.SalesControlOutlook;
import com.wings4.util.IconFactory;
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
        addTab("General", IconFactory.getSwingIcon("ggg.png"),
                makeScrollPane(new GeneralControlOutlook()), "General");
        addTab("Materials", IconFactory.getSwingIcon("component/button_16.png"),
                makeScrollPane(new MaterialsControlOutlook()), "Materials");
        addTab("Sales", IconFactory.getSwingIcon("component/button_16.png"),
                makeScrollPane(new SalesControlOutlook()), "Sales");
        addTab("Purchasing", IconFactory.getSwingIcon("component/button_16.png"),
                makeScrollPane(new PurchaseControlOutlook()), "Purchasing");
        addTab("Manufacturing", IconFactory.getSwingIcon("component/button_16.png"),
                makeScrollPane(new GeneralControlOutlook()), "Manufacturing");
    }
}
