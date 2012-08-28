package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Picking;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class PickingButtonPanel extends JPanel {

    private JScrollPane pickingListHolder;
    private JTable pickingTable;

    public PickingButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new PickingListPanel());
    }

    public class PickingListPanel extends JPanel {
        public PickingListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Picking List"));

            pickingListHolder = new JScrollPane();
            pickingTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Picking.class);
            final ObjectTableModel<Picking> tableModel = new ObjectTableModel<Picking>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            pickingTable.setModel(tableModel);

            pickingListHolder.setViewportView(pickingTable);

            add(pickingListHolder);
        }
    }

    private List<Picking> getData() {
        List<Picking> list = new ArrayList<Picking>();
        return list;
    }
}
