package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.dao.JobDao;
import com.wings4.model.Sales;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/22/12
 * Time: 11:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class SalesButtonPanel extends JPanel {

    private JScrollPane salesListHolder;
    private JTable salesTable;

    public SalesButtonPanel() {

        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new SalesPanel());
    }

    public class SalesPanel extends JPanel {
        public SalesPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Raw Materials List"));

            salesListHolder = new JScrollPane();
            salesTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Sales.class);
            final ObjectTableModel<Sales> tableModel = new ObjectTableModel<Sales>(
                    resolver, "id,customerName,productName,salesDate,quantity,price");

            tableModel.setData(JobDao.findAllSales());
            salesTable.setModel(tableModel);

            salesListHolder.setViewportView(salesTable);

            add(salesListHolder);
        }
    }
}
