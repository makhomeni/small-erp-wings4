package com.wings4.core.panel;

import com.jidesoft.navigation.BreadcrumbBar;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.CustomerCreateTogglePanel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.PurchaseCreateTogglePanel;
import com.wings4.dao.CommonDao;
import com.wings4.dao.JobDao;
import com.wings4.model.PurchaseOrder;
import com.wings4.util.IconFactory;
import com.wings4.util.InventoryConstants;
import com.wings4.util.PrintUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderButtonPanel extends JPanel {

    private JScrollPane purchaseOrderListHolder;
    private JTable purchaseOrderTable;
    private JButton createPurchaseOrderButton;
    private JButton createSalesButton;
    private BreadcrumbBar breadCrumbBar;
    private JToolBar purchaseOrderToolbar;
    private JButton reportSalesOrderButton;

    public PurchaseOrderButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new PurchaseOrderListPanel());
    }


    /**
     * this class is for showing purchase order list
     */
    public class PurchaseOrderListPanel extends JPanel {
        public PurchaseOrderListPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder("Purchase Order List"));

            purchaseOrderToolbar = new JToolBar();
            //create a button for new purchase order add option
            createPurchaseOrderButton = new JButton();
            createSalesButton = new JButton();
            reportSalesOrderButton = new JButton();

            //button

            createPurchaseOrderButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton categoryButton = new GeneralToggleActionButton(new
                            PurchaseOrderCreateTogglePanel());
                    categoryButton.doClick();
                }
            });

            createPurchaseOrderButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createPurchaseOrderButton.setFocusable(false);
            createPurchaseOrderButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createPurchaseOrderButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            purchaseOrderToolbar.add(createPurchaseOrderButton);

            reportSalesOrderButton.setIcon(IconFactory.getSwingIcon("document-print.png"));
            reportSalesOrderButton.setFocusable(false);
            reportSalesOrderButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportSalesOrderButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            reportSalesOrderButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    PrintUtilities.printComponent(purchaseOrderListHolder);
                }
            });

            createSalesButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton purchaseButton = new GeneralToggleActionButton(new
                            PurchaseCreateTogglePanel());
                    purchaseButton.doClick();
                }
            });

            createSalesButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createSalesButton.setFocusable(true);
            createSalesButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createSalesButton.setVerticalTextPosition(SwingConstants.BOTTOM);


            reportSalesOrderButton.setIcon(IconFactory.getSwingIcon("document-print.png"));
            reportSalesOrderButton.setFocusable(false);
            reportSalesOrderButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportSalesOrderButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            reportSalesOrderButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    PrintUtilities.printComponent(purchaseOrderListHolder);
                }
            });
            purchaseOrderToolbar.add(createSalesButton);



            purchaseOrderListHolder = new JScrollPane();
            purchaseOrderTable = new JTable();


            //set the resolver to get the fields
            AnnotationResolver resolver = new AnnotationResolver(PurchaseOrder.class);
            final ObjectTableModel<PurchaseOrder> tableModel = new ObjectTableModel<PurchaseOrder>(
                    resolver, "id,vendorId,organizationId");

            tableModel.setData(JobDao.findAllPurchaseOrders());
            purchaseOrderTable.setModel(tableModel);

            purchaseOrderListHolder.setViewportView(purchaseOrderTable);
            add(purchaseOrderToolbar, BorderLayout.PAGE_START);
            add(purchaseOrderListHolder, BorderLayout.CENTER);

        }
    }


}
