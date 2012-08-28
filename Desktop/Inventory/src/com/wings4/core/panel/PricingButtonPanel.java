package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Pricing;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class PricingButtonPanel extends JPanel {

    private JScrollPane pricingListHolder;
    private JTable pricingTable;

    public PricingButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new PricingListPanel());
    }

    public class PricingListPanel extends JPanel {
        public PricingListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Pricing List"));

            pricingListHolder = new JScrollPane();
            pricingTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Pricing.class);
            final ObjectTableModel<Pricing> tableModel = new ObjectTableModel<Pricing>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            pricingTable.setModel(tableModel);

            pricingListHolder.setViewportView(pricingTable);

            add(pricingListHolder);
        }
    }

    private List<Pricing> getData() {
        List<Pricing> list = new ArrayList<Pricing>();
        return list;
    }

}
