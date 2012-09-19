package com.wings4.core.outlook;

import com.nepxion.swing.button.ButtonManager;
import com.wings4.core.toggle.*;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 8:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseControlOutlook extends AppToggleOutlook {

    //puchase order menu
    GeneralToggleActionButton purchaseOrderButton = new GeneralToggleActionButton(new PurchaseOrderButtonTogglePanel());
    //Receiving menu
    GeneralToggleActionButton receivingButton = new GeneralToggleActionButton(new ReceivingButtonTogglePanel());
    //vendor menu
    GeneralToggleActionButton vendorButton = new GeneralToggleActionButton(new VendorButtonTogglePanel());

    public PurchaseControlOutlook() {
        //each menus are added here
        addButton(purchaseOrderButton);
        addButton(receivingButton);
        addButton(vendorButton);

        defaultValueClick();
    }

    public void defaultValueClick(){
        ButtonManager.updateUI(this, new Dimension(50, 70), new int[]{VERTICAL, CENTER});
        purchaseOrderButton.doClick();
    }
}
