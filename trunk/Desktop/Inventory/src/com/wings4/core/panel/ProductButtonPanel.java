package com.wings4.core.panel;

import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.ProductCreateTogglePanel;
import com.wings4.model.Product;
import com.wings4.util.InventoryConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    private JToolBar productToolBar;
    private JButton createProductButton;
    private JButton reportProductButton;

    public ProductButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new ProductListPanel());
    }

    public class ProductListPanel extends JPanel {
        public ProductListPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder("Product List"));

            productScrollPane = new JScrollPane();
            productTable = new JTable();

            productToolBar = new JToolBar();
            createProductButton = new JButton();
            reportProductButton = new JButton();

            createProductButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createProductButton.setFocusable(false);
            createProductButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createProductButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            productToolBar.add(createProductButton);

            createProductButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton productCreateButton = new GeneralToggleActionButton(new
                            ProductCreateTogglePanel());
                    productCreateButton.doClick();
                }
            });

            reportProductButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("document-print.png"))));
            reportProductButton.setFocusable(false);
            reportProductButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportProductButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            productToolBar.add(reportProductButton);



            AnnotationResolver resolver = new AnnotationResolver(Product.class);
            final ObjectTableModel<Product> tableModel = new ObjectTableModel<Product>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode," +
                    "productClassification,productCategory");

            tableModel.setData(getData());
            productTable.setModel(tableModel);

            productScrollPane.setViewportView(productTable);

            add(productToolBar, BorderLayout.PAGE_START);
            add(productScrollPane, BorderLayout.CENTER);
        }
    }

    private List<Product> getData() {
        List<Product> list = new ArrayList<Product>();
        return list;
    }
}
