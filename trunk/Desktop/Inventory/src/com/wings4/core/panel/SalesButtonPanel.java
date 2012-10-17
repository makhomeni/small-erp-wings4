package com.wings4.core.panel;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.ProductCreateTogglePanel;
import com.wings4.core.toggle.SalesCreateTogglePanel;
import com.wings4.dao.JobDao;
import com.wings4.dao.MaterialDao;
import com.wings4.model.Sales;
import com.wings4.util.InventoryConstants;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

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

    private JToolBar salesToolBar;
    private JButton createSalesButton;
    private JButton reportSalesButton;

    public SalesButtonPanel() {

        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new SalesPanel());
    }

    public class SalesPanel extends JPanel {
        public SalesPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder("Sales"));

            salesListHolder = new JScrollPane();
            salesTable = new JTable();

            salesToolBar = new JToolBar();
            createSalesButton = new JButton();
            reportSalesButton = new JButton();


            createSalesButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createSalesButton.setFocusable(false);
            createSalesButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createSalesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            salesToolBar.add(createSalesButton);

            createSalesButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton salesCreateButton = new GeneralToggleActionButton(new
                            SalesCreateTogglePanel());
                    salesCreateButton.doClick();
                }
            });

            reportSalesButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("document-print.png"))));
            reportSalesButton.setFocusable(false);
            reportSalesButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportSalesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            reportSalesButton.addActionListener(new JSecurityAction() {
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
            salesToolBar.add(reportSalesButton);

            AnnotationResolver resolver = new AnnotationResolver(Sales.class);
            final ObjectTableModel<Sales> tableModel = new ObjectTableModel<Sales>(
                    resolver, "id,customerName,productName,salesDate,quantity,price");

            tableModel.setData(JobDao.findAllSales());
            salesTable.setModel(tableModel);

            salesListHolder.setViewportView(salesTable);

            add(salesToolBar, BorderLayout.PAGE_START);
            add(salesListHolder, BorderLayout.CENTER);
        }
    }
}
