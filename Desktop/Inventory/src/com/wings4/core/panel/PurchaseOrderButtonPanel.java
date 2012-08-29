package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.PurchaseOrder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderButtonPanel extends JPanel {

    private JScrollPane purchaseOrderListHolder;
    private JTable purchaseOrderTable;

    public PurchaseOrderButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new PurchaseOrderListPanel());
    }

    public class PurchaseOrderListPanel extends JPanel {
        public PurchaseOrderListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Purchase Order List"));

            purchaseOrderListHolder = new JScrollPane();
            purchaseOrderTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(PurchaseOrder.class);
            final ObjectTableModel<PurchaseOrder> tableModel = new ObjectTableModel<PurchaseOrder>(
                    resolver, "id,vendor,shippingAddress,organization,shippingMethod,paymentTerm");

            tableModel.setData(getData());
            purchaseOrderTable.setModel(tableModel);

            purchaseOrderListHolder.setViewportView(purchaseOrderTable);

            add(purchaseOrderListHolder);
        }
    }

    private List<PurchaseOrder> getData() {
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        return list;
    }
}
