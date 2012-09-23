package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.dao.CommonDao;
import com.wings4.model.LocalVendor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocalVendorButtonPanel extends JPanel {

    private JScrollPane vendorListHolder;
    private JTable vendorTable;
    private JToolBar localVendorToolBar;
    private JButton createLocalVendorButton;
    private JButton reportLocalVendorButton;

    public LocalVendorButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new LocalVendorListPanel());
    }

    public class LocalVendorListPanel extends JPanel {
        public LocalVendorListPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder("LocalVendor List"));
            
            localVendorToolBar = new JToolBar();
            createLocalVendorButton = new JButton();
            reportLocalVendorButton = new JButton();

            vendorListHolder = new JScrollPane();
            vendorTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(LocalVendor.class);
            final ObjectTableModel<LocalVendor> tableModel = new ObjectTableModel<LocalVendor>(
                    resolver, "id,name,organization,address," +
                    "phoneNumber,email");

            tableModel.setData(CommonDao.findAllLocalVendors());
            vendorTable.setModel(tableModel);

            vendorListHolder.setViewportView(vendorTable);

            add(vendorListHolder);
        }
    }

}
