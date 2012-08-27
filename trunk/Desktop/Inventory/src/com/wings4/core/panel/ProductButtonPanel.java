package com.wings4.core.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/22/12
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductButtonPanel extends JPanel {

    private JScrollPane productScrollPane;
    private JTable productTable;

    public ProductButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new ProductListPanel());
    }

    public class ProductListPanel extends JPanel {
        public ProductListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Product List"));

            productScrollPane = new JScrollPane();
            productTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Product.class);
            final ObjectTableModel<Product> tableModel = new ObjectTableModel<Product>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            productTable.setModel(tableModel);

            productScrollPane.setViewportView(productTable);

            add(productScrollPane);
        }
    }

    private List<Product> getData() {
        List<Product> list = new ArrayList<Product>();
        return list;
    }
}
