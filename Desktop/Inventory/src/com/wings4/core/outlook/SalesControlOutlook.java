package com.wings4.core.outlook;

import com.nepxion.swing.button.ButtonManager;
import com.wings4.core.toggle.*;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/27/12
 * Time: 10:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class SalesControlOutlook extends AppToggleOutlook {


    GeneralToggleActionButton salesOrderButton = new GeneralToggleActionButton(new SalesOrderButtonTogglePanel());
    GeneralToggleActionButton salesButton = new GeneralToggleActionButton(new SalesButtonTogglePanel());
    GeneralToggleActionButton pickingButton = new GeneralToggleActionButton(new PickingButtonTogglePanel());
    GeneralToggleActionButton shippingButton = new GeneralToggleActionButton(new ShippingButtonTogglePanel());
    GeneralToggleActionButton customerButton = new GeneralToggleActionButton(new CustomerButtonTogglePanel());
    GeneralToggleActionButton rmaButton = new GeneralToggleActionButton(new RMAButtonTogglePanel());
    GeneralToggleActionButton pricingButton = new GeneralToggleActionButton(new PricingButtonTogglePanel());
    GeneralToggleActionButton discountButton = new GeneralToggleActionButton(new DiscountButtonTogglePanel());

    public SalesControlOutlook() {
        addButton(salesOrderButton);
        addButton(salesButton);
        addButton(pickingButton);
        addButton(shippingButton);
        addButton(customerButton);
        addButton(rmaButton);
        addButton(pricingButton);
        addButton(discountButton);
        defaultValueClick();
    }

    public void defaultValueClick(){
        ButtonManager.updateUI(this, new Dimension(50, 70), new int[]{VERTICAL, CENTER});
        salesOrderButton.doClick();
    }

}
