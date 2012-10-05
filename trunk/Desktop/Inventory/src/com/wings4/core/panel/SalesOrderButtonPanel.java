package com.wings4.core.panel;

import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.*;
import com.wings4.dao.CommonDao;
import com.wings4.model.SalesOrder;
import com.wings4.util.IconFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 8/28/12
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class SalesOrderButtonPanel extends JPanel {

    private JScrollPane salesOrderListHolder;
    private JTable salesOrderTable;
    private JToolBar salesOrderToolBar;
    private JButton createSalesOrderButton;
    private JButton salesOrderCreate;
    private JButton reportSalesOrderButton;

    public SalesOrderButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new SalesOrderListPanel());
    }

    public class SalesOrderListPanel extends JPanel {
        public SalesOrderListPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder("Sales Order List"));

            salesOrderListHolder = new JScrollPane();
            salesOrderTable = new JTable();
            salesOrderToolBar = new JToolBar();
            createSalesOrderButton = new JButton();
            salesOrderCreate = new JButton();
            reportSalesOrderButton = new JButton();

            createSalesOrderButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton categoryButton = new GeneralToggleActionButton(new
                            SalseOrderCreateTogglePanel());
                    categoryButton.doClick();
                }
            });

            salesOrderCreate.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton salesButton = new GeneralToggleActionButton(new
                            SalesCreateTogglePanel());
                    salesButton.doClick();
                }
            });

            createSalesOrderButton.setIcon(IconFactory.getSwingIcon("list-add.png"));
            salesOrderCreate.setIcon(IconFactory.getSwingIcon("list-add.png"));

            createSalesOrderButton.setFocusable(false);
            createSalesOrderButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createSalesOrderButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            salesOrderToolBar.add(createSalesOrderButton);


            reportSalesOrderButton.setIcon(IconFactory.getSwingIcon("document-print.png"));
            reportSalesOrderButton.setFocusable(false);
            reportSalesOrderButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportSalesOrderButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            salesOrderToolBar.add(reportSalesOrderButton);

            salesOrderCreate.setFocusable(false);
            salesOrderCreate.setHorizontalTextPosition(SwingConstants.CENTER);
            salesOrderCreate.setVerticalTextPosition(SwingConstants.BOTTOM);
            salesOrderToolBar.add(salesOrderCreate);



            AnnotationResolver resolver = new AnnotationResolver(SalesOrder.class);
            final ObjectTableModel<SalesOrder> tableModel = new ObjectTableModel<SalesOrder>(
                    resolver, "id,jobName,orderQuantity,createdDate,status,priority,isSent,dueDate,isArchived");

            tableModel.setData(CommonDao.findAllSalesOrder());
            salesOrderTable.setModel(tableModel);

            salesOrderListHolder.setViewportView(salesOrderTable);

            add(salesOrderToolBar,BorderLayout.PAGE_START);
            add(salesOrderListHolder, BorderLayout.CENTER);
        }
    }

    private List<SalesOrder> getData() {
        List<SalesOrder> list = new ArrayList<SalesOrder>();
        return list;
    }
}
