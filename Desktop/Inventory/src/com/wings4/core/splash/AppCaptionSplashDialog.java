package com.wings4.core.splash;

import com.nepxion.swing.splash.JCaptionSplashDialog;
import com.wings4.util.IconFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppCaptionSplashDialog extends JCaptionSplashDialog {

    public AppCaptionSplashDialog(){
        super(IconFactory.getSwingIcon(("splash.png")), new int[] {15, 200}, new int[] {220, 90}, new String[]
            {
                "This is Developed by Wings4",
                "A Complete and Full-length Inventory Management System",
                "Any kind of Inventory can be controlled, configured and managed by this",
                "The following can be done by this software",
                "1. Create Product and Insert into Stock",
                "2. Create Purchase Order along with Procurement",
                "3. Create Sales Order along with Sales",
                "4. Integration of Customer and Supplier via StockDynamic Report Generation",
                "5. Integration"
            }
        );
    }
}
