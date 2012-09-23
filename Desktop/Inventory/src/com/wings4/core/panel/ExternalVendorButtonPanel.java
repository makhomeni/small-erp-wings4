package com.wings4.core.panel;

import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.ExternalVendorCreateTogglePanel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.dao.CommonDao;
import com.wings4.model.ExternalVendor;
import com.wings4.util.InventoryConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private JToolBar externalVendorToolBar;
    private JButton createExternalVendorButton;
    private JButton reportExternalVendorButton;

    public ExternalVendorButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new ExternalVendorListPanel());
    }

    public class ExternalVendorListPanel extends JPanel {
        public ExternalVendorListPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder(InventoryConstants.EXTERNAL_VENDOR));

            vendorListHolder = new JScrollPane();
            vendorTable = new JTable();

            vendorListHolder = new JScrollPane();
            vendorTable = new JTable();

            createExternalVendorButton = new JButton();
            reportExternalVendorButton = new JButton();
            externalVendorToolBar = new JToolBar();

            createExternalVendorButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createExternalVendorButton.setFocusable(false);
            createExternalVendorButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createExternalVendorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            externalVendorToolBar.add(createExternalVendorButton);

            createExternalVendorButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton productCreateButton = new GeneralToggleActionButton(new
                            ExternalVendorCreateTogglePanel());
                    productCreateButton.doClick();
                }
            });

            reportExternalVendorButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("document-print.png"))));
            reportExternalVendorButton.setFocusable(false);
            reportExternalVendorButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportExternalVendorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            externalVendorToolBar.add(reportExternalVendorButton);

            AnnotationResolver resolver = new AnnotationResolver(ExternalVendor.class);
            final ObjectTableModel<ExternalVendor> tableModel = new ObjectTableModel<ExternalVendor>(
                    resolver, "id,name,organization,address," +
                    "phoneNumber,email,country,carrier");

            tableModel.setData(CommonDao.findAllExternalVendors());
            vendorTable.setModel(tableModel);

            vendorListHolder.setViewportView(vendorTable);

            add(externalVendorToolBar, BorderLayout.PAGE_START);
            add(vendorListHolder, BorderLayout.CENTER);
        }
    }
}
