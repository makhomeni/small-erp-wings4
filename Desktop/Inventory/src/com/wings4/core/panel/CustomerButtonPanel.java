package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Customer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerButtonPanel extends JPanel {

    private JScrollPane customerListHolder;
    private JTable customerTable;

    public CustomerButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new CustomerListPanel());
    }

    public class CustomerListPanel extends JPanel {
        public CustomerListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Customer List"));

            customerListHolder = new JScrollPane();
            customerTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Customer.class);
            final ObjectTableModel<Customer> tableModel = new ObjectTableModel<Customer>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            customerTable.setModel(tableModel);

            customerListHolder.setViewportView(customerTable);

            add(customerListHolder);
        }
    }

    private List<Customer> getData() {
        List<Customer> list = new ArrayList<Customer>();
        return list;
    }
}
