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

    public SalesControlOutlook() {
        GeneralToggleActionButton salesOrderButton = new GeneralToggleActionButton(new SalesOrderButtonTogglePanel());
        addButton(salesOrderButton);

        GeneralToggleActionButton pickingButton = new GeneralToggleActionButton(new PickingButtonTogglePanel());
        addButton(pickingButton);

        GeneralToggleActionButton shippingButton = new GeneralToggleActionButton(new ShippingButtonTogglePanel());
        addButton(shippingButton);

        GeneralToggleActionButton customerButton = new GeneralToggleActionButton(new CustomerButtonTogglePanel());
        addButton(customerButton);

        GeneralToggleActionButton rmaButton = new GeneralToggleActionButton(new RMAButtonTogglePanel());
        addButton(rmaButton);

        GeneralToggleActionButton pricingButton = new GeneralToggleActionButton(new PricingButtonTogglePanel());
        addButton(pricingButton);

        GeneralToggleActionButton discountButton = new GeneralToggleActionButton(new DiscountButtonTogglePanel());
        addButton(discountButton);

        ButtonManager.updateUI(this, new Dimension(50, 70), new int[]{VERTICAL, CENTER});
        salesOrderButton.doClick();

    }

}
