package com.wings4.core.outlook;

import com.nepxion.swing.button.ButtonManager;
import com.wings4.core.toggle.*;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/21/12
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class MaterialsControlOutlook extends AppToggleOutlook {

    public MaterialsControlOutlook() {
        GeneralToggleActionButton categoryButton = new GeneralToggleActionButton(new CategoryButtonTogglePanel());
        addButton(categoryButton);

        GeneralToggleActionButton productButton = new GeneralToggleActionButton(new ProductButtonTogglePanel());
        addButton(productButton);

        GeneralToggleActionButton stockButton = new GeneralToggleActionButton(new InventoryButtonTogglePanel());
        addButton(stockButton);



        ButtonManager.updateUI(this, new Dimension(50, 70), new int[]{VERTICAL, CENTER});
        categoryButton.doClick();
    }

    public void productExplore(){
        GeneralToggleActionButton categoryButton = new GeneralToggleActionButton(new CategoryButtonTogglePanel());
        addButton(categoryButton);

        GeneralToggleActionButton productButton = new GeneralToggleActionButton(new ProductButtonTogglePanel());
        addButton(productButton);

        GeneralToggleActionButton inventoryButton = new GeneralToggleActionButton(new InventoryButtonTogglePanel());
        addButton(inventoryButton);



        ButtonManager.updateUI(this, new Dimension(50, 70), new int[]{VERTICAL, CENTER});
        categoryButton.doClick();
    }
}
