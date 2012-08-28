package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.RMA;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class RMAButtonPanel extends JPanel {

    private JScrollPane purchaseOrderListHolder;
    private JTable purchaseOrderTable;

    public RMAButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new RMAListPanel());
    }

    public class RMAListPanel extends JPanel {
        public RMAListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Raw Materials List"));

            purchaseOrderListHolder = new JScrollPane();
            purchaseOrderTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(RMA.class);
            final ObjectTableModel<RMA> tableModel = new ObjectTableModel<RMA>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            purchaseOrderTable.setModel(tableModel);

            purchaseOrderListHolder.setViewportView(purchaseOrderTable);

            add(purchaseOrderListHolder);
        }
    }

    private List<RMA> getData() {
        List<RMA> list = new ArrayList<RMA>();
        return list;
    }
}
