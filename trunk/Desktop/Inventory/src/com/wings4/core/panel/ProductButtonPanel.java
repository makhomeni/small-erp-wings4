package com.wings4.core.panel;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.jidesoft.grid.TableUtils;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.ProductCreateTogglePanel;
import com.wings4.dao.CommonDao;
import com.wings4.dao.MaterialDao;
import com.wings4.model.Product;
import com.wings4.util.InventoryConstants;
import com.wings4.util.PrintUtilities;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
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
            reportProductButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    try{
                        FastReportBuilder fastReportBuilder = new FastReportBuilder();
                        DynamicReport dynamicReport = fastReportBuilder.addColumn("productId", "productId", Integer.class.getName(),30)
                                .addColumn("productName", "productName", String.class.getName(), 100)
                                .setTitle("Product Report")
                                .setSubtitle("The Report is Generated at " + new Date())
                                .setPrintBackgroundOnOddRows(true)
                                .setUseFullPageWidth(true)
                                .build();
                        JRDataSource productDataSource = new JRBeanCollectionDataSource(MaterialDao.findAllProducts());

                        JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport,
                                new ClassicLayoutManager(), productDataSource);
                        JasperViewer.viewReport(jasperPrint);
                    } catch (Exception ex){

                    }
                }
            });
            productToolBar.add(reportProductButton);



            AnnotationResolver resolver = new AnnotationResolver(Product.class);
            final ObjectTableModel<Product> tableModel = new ObjectTableModel<Product>(
                    resolver, "productId,productName,stockKeepingUnit,universalProductCode,productCategory");

            tableModel.setData(CommonDao.findAllProducts());
            productTable.setModel(tableModel);

            productScrollPane.setViewportView(productTable);
            TableUtils.synchronizeTableColumn(productTable,productTable);

            add(productToolBar, BorderLayout.PAGE_START);
            add(productScrollPane, BorderLayout.CENTER);
        }
    }
}
