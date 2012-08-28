package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Picking;
import com.wings4.model.Shipping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShippingButtonPanel extends JPanel {

    private JScrollPane shippingListHolder;
    private JTable shippingTable;

    public ShippingButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new ShippingListPanel());
    }

    public class ShippingListPanel extends JPanel {
        public ShippingListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Shipping List"));

            shippingListHolder = new JScrollPane();
            shippingTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Shipping.class);
            final ObjectTableModel<Shipping> tableModel = new ObjectTableModel<Shipping>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            shippingTable.setModel(tableModel);

            shippingListHolder.setViewportView(shippingTable);

            add(shippingListHolder);
        }
    }

    private List<Shipping> getData() {
        List<Shipping> list = new ArrayList<Shipping>();
        return list;
    }
}
