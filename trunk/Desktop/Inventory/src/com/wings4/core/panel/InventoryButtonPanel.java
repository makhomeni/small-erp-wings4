package com.wings4.core.panel;

import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.InventoryCreateTogglePanel;
import com.wings4.core.toggle.ProductCreateTogglePanel;
import com.wings4.model.InventoryRegister;
import com.wings4.util.InventoryConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/29/12
 * Time: 10:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryButtonPanel extends JPanel {

    private JScrollPane inventoryListHolder;
    private JTable inventoryTable;

    private JToolBar inventoryToolBar;
    private JButton createInventoryButton;
    private JButton reportInventoryButton;

    public InventoryButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new InventoryListPanel());
    }

    public class InventoryListPanel extends JPanel {
        public InventoryListPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder("Inventory List"));

            inventoryListHolder = new JScrollPane();
            inventoryTable = new JTable();


            inventoryToolBar = new JToolBar();
            createInventoryButton = new JButton();
            reportInventoryButton = new JButton();

            createInventoryButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createInventoryButton.setFocusable(false);
            createInventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createInventoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            inventoryToolBar.add(createInventoryButton);

            createInventoryButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton inventoryCreateButton = new GeneralToggleActionButton(new
                            InventoryCreateTogglePanel());
                    inventoryCreateButton.doClick();
                }
            });

            reportInventoryButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("document-print.png"))));
            reportInventoryButton.setFocusable(false);
            reportInventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportInventoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            inventoryToolBar.add(reportInventoryButton);

            AnnotationResolver resolver = new AnnotationResolver(InventoryRegister.class);
            final ObjectTableModel<InventoryRegister> tableModel = new ObjectTableModel<InventoryRegister>(
                    resolver, "id,productName,onHand,onOrder,allocated,committed,unavailable,backOrdered,dropShip," +
                    "availableForSale,availableToPick");

            tableModel.setData(getData());
            inventoryTable.setModel(tableModel);

            inventoryListHolder.setViewportView(inventoryTable);

            add(inventoryToolBar, BorderLayout.PAGE_START);
            add(inventoryListHolder, BorderLayout.CENTER);
        }
    }

    private List<InventoryRegister> getData() {
        List<InventoryRegister> list = new ArrayList<InventoryRegister>();
        return list;
    }
}
