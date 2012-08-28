package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Product;
import com.wings4.model.Vendor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class VendorButtonPanel extends JPanel {

    private JScrollPane vendorListHolder;
    private JTable vendorTable;

    public VendorButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new VendorListPanel());
    }

    public class VendorListPanel extends JPanel {
        public VendorListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Vendor List"));

            vendorListHolder = new JScrollPane();
            vendorTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Vendor.class);
            final ObjectTableModel<Vendor> tableModel = new ObjectTableModel<Vendor>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            vendorTable.setModel(tableModel);

            vendorListHolder.setViewportView(vendorTable);

            add(vendorListHolder);
        }
    }

    private List<Vendor> getData() {
        List<Vendor> list = new ArrayList<Vendor>();
        return list;
    }

}
