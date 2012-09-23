package com.wings4.core.panel;

import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.ExternalVendorCreateTogglePanel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.model.ExternalVendor;
import com.wings4.model.LocalVendor;
import com.wings4.util.InventoryConstants;

import javax.swing.*;
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
    private JToolBar localVendorToolBar;
    private JButton createLocalVendorButton;
    private JButton reportLocalVendorButton;

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
                            ExternalVendorCreateTogglePanel());
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
