package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Discount;
import com.wings4.model.Receiving;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReceivingButtonPanel extends JPanel {

    private JScrollPane discountListHolder;
    private JTable discountTable;

    public ReceivingButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new ReceivingListPanel());
    }

    public class ReceivingListPanel extends JPanel {
        public ReceivingListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Receiving List"));

            discountListHolder = new JScrollPane();
            discountTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Receiving.class);
            final ObjectTableModel<Receiving> tableModel = new ObjectTableModel<Receiving>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            discountTable.setModel(tableModel);

            discountListHolder.setViewportView(discountTable);

            add(discountListHolder);
        }
    }

    private List<Receiving> getData() {
        List<Receiving> list = new ArrayList<Receiving>();
        return list;
    }
}
