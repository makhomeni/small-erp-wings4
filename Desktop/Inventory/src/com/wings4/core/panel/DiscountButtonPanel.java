package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Discount;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class DiscountButtonPanel extends JPanel {

    private JScrollPane discountListHolder;
    private JTable discountTable;

    public DiscountButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new DiscountListPanel());
    }

    public class DiscountListPanel extends JPanel {
        public DiscountListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Discount List"));

            discountListHolder = new JScrollPane();
            discountTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Discount.class);
            final ObjectTableModel<Discount> tableModel = new ObjectTableModel<Discount>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            discountTable.setModel(tableModel);

            discountListHolder.setViewportView(discountTable);

            add(discountListHolder);
        }
    }

    private List<Discount> getData() {
        List<Discount> list = new ArrayList<Discount>();
        return list;
    }
}
