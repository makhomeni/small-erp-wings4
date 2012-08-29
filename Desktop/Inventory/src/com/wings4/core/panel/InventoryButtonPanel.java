package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.InventoryRegister;

import javax.swing.*;
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

    private JScrollPane customerListHolder;
    private JTable customerTable;

    public InventoryButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new InventoryListPanel());
    }

    public class InventoryListPanel extends JPanel {
        public InventoryListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Inventory List"));

            customerListHolder = new JScrollPane();
            customerTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(InventoryRegister.class);
            final ObjectTableModel<InventoryRegister> tableModel = new ObjectTableModel<InventoryRegister>(
                    resolver, "id,productName,onHand" +
                    "phoneNumber,address,contact,reference,billingAddress");

            tableModel.setData(getData());
            customerTable.setModel(tableModel);

            customerListHolder.setViewportView(customerTable);

            add(customerListHolder);
        }
    }

    private List<InventoryRegister> getData() {
        List<InventoryRegister> list = new ArrayList<InventoryRegister>();
        return list;
    }
}
