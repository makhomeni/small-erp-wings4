package com.wings4.core.panel;

import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.LocalVendorCreateTogglePanel;
import com.wings4.dao.CommonDao;
import com.wings4.model.LocalVendor;
import com.wings4.util.InventoryConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

            createLocalVendorButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createLocalVendorButton.setFocusable(false);
            createLocalVendorButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createLocalVendorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            localVendorToolBar.add(createLocalVendorButton);

            createLocalVendorButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton productCreateButton = new GeneralToggleActionButton(new
                            LocalVendorCreateTogglePanel());
                    productCreateButton.doClick();
                }
            });

            reportLocalVendorButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("document-print.png"))));
            reportLocalVendorButton.setFocusable(false);
            reportLocalVendorButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportLocalVendorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            localVendorToolBar.add(reportLocalVendorButton);


            AnnotationResolver resolver = new AnnotationResolver(LocalVendor.class);
            final ObjectTableModel<LocalVendor> tableModel = new ObjectTableModel<LocalVendor>(
                    resolver, "id,name,organization,address,phoneNumber,email");

            tableModel.setData(CommonDao.findAllLocalVendors());
            vendorTable.setModel(tableModel);

            vendorListHolder.setViewportView(vendorTable);

            add(localVendorToolBar, BorderLayout.PAGE_START);
            add(vendorListHolder, BorderLayout.CENTER);
        }
    }

}
