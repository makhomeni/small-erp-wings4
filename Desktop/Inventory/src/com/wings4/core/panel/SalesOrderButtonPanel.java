package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Product;
import com.wings4.model.SalesOrder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class SalesOrderButtonPanel extends JPanel {

    private JScrollPane productScrollPane;
    private JTable productTable;

    public SalesOrderButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new SalesOrderListPanel());
    }

    public class SalesOrderListPanel extends JPanel {
        public SalesOrderListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Sales Order List"));

            productScrollPane = new JScrollPane();
            productTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(SalesOrder.class);
            final ObjectTableModel<SalesOrder> tableModel = new ObjectTableModel<SalesOrder>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            productTable.setModel(tableModel);

            productScrollPane.setViewportView(productTable);

            add(productScrollPane);
        }
    }

    private List<SalesOrder> getData() {
        List<SalesOrder> list = new ArrayList<SalesOrder>();
        return list;
    }
}
