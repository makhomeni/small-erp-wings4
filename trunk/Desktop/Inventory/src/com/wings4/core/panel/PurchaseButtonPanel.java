package com.wings4.core.panel;

import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.PurchaseCreateTogglePanel;
import com.wings4.dao.CommonDao;
import com.wings4.model.PurchaseOrder;
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
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseButtonPanel extends JPanel {

    private JScrollPane purchaseListHolder;
    private JTable purchaseTable;
    private JButton createPurchaseButton;
    private JToolBar purchaseToolbar;

    public PurchaseButtonPanel() {
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


            purchaseToolbar = new JToolBar();
            //create a button for new purchase order add option
            createPurchaseButton = new JButton();

            //button

            createPurchaseButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton purchaseButton = new GeneralToggleActionButton(new
                            PurchaseCreateTogglePanel());
                    purchaseButton.doClick();
                }
            });

            createPurchaseButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createPurchaseButton.setFocusable(false);
            createPurchaseButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createPurchaseButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            purchaseToolbar.add(createPurchaseButton);



            purchaseListHolder = new JScrollPane();
            purchaseTable = new JTable();


            //set the resolver to get the fields
            AnnotationResolver resolver = new AnnotationResolver(PurchaseOrder.class);
            final ObjectTableModel<PurchaseOrder> tableModel = new ObjectTableModel<PurchaseOrder>(
                    resolver, "vendor,organization");

            tableModel.setData(CommonDao.findAllPurchaseOrders());
            purchaseTable.setModel(tableModel);

            purchaseListHolder.setViewportView(purchaseTable);
            add(purchaseToolbar, BorderLayout.PAGE_START);
            add(purchaseListHolder, BorderLayout.CENTER);

        }
    }

    private List<PurchaseOrder> getData() {
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        return list;
    }
}
