package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.ExternalVendor;
import com.wings4.model.LocalVendor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/29/12
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExternalVendorButtonPanel extends JPanel {

    private JScrollPane vendorListHolder;
    private JTable vendorTable;

    public ExternalVendorButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new ExternalVendorListPanel());
    }

    public class ExternalVendorListPanel extends JPanel {
        public ExternalVendorListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("LocalVendor List"));

            vendorListHolder = new JScrollPane();
            vendorTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(ExternalVendor.class);
            final ObjectTableModel<ExternalVendor> tableModel = new ObjectTableModel<ExternalVendor>(
                    resolver, "id,name,organization,address," +
                    "phoneNumber,email,country,carrier");

            tableModel.setData(getData());
            vendorTable.setModel(tableModel);

            vendorListHolder.setViewportView(vendorTable);

            add(vendorListHolder);
        }
    }

    private List<ExternalVendor> getData() {
        List<ExternalVendor> list = new ArrayList<ExternalVendor>();
        return list;
    }
}
