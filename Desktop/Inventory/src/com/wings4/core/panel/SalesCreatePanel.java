package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.combobox.TableComboBox;
import com.jidesoft.dialog.JideOptionPane;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.SalesButtonTogglePanel;
import com.wings4.dao.CommonDao;
import com.wings4.dao.JobDao;
import com.wings4.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: MASUM
 * Date: 9/28/12
 * Time: 12:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SalesCreatePanel   extends JPanel {

    public SalesCreatePanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new SalesCreate());
    }

    public class SalesCreate extends JPanel {
        public SalesCreate() {
            DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
            builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 100px)");
            builder.appendColumn("5dlu");
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 10px)");


            AnnotationResolver vendorResolver = new AnnotationResolver(Customer.class);
            AnnotationResolver salesResolver = new AnnotationResolver(SalesOrder.class);


            final ObjectTableModel<Customer> tableModelVendor = new ObjectTableModel<Customer>(
                    vendorResolver, "id,firstName");

            final ObjectTableModel<SalesOrder> tableModelSalesOrder = new ObjectTableModel<SalesOrder>(
                    salesResolver, "id,jobName");


            tableModelVendor.setData(CommonDao.findAllCustomers());
            tableModelSalesOrder.setData(JobDao.findAllSalesOrder());



            final TableComboBox vendorCombo = new TableComboBox(tableModelVendor);
            final TableComboBox salesOrderCombo = new TableComboBox(tableModelSalesOrder);

            final JTextField priceTextField = new JTextField();
            final JTextField quantity = new JTextField();
            String[] purchaseTypeValue = {"Sales", "Sample", "Loan"};
            final JComboBox purchaseTypeCombo = new JComboBox(purchaseTypeValue);





            JButton submitPurchase = new JButton();
            JButton cancelPurchase = new JButton();


            builder.append("Customer:", vendorCombo);
            builder.nextLine();

            builder.append("Sales Order:", salesOrderCombo);
            builder.nextLine();

            builder.append("Price:", priceTextField);
            builder.nextLine();

            builder.append("Quantity:", quantity);
            builder.nextLine();

            builder.append("Purchase Type:", purchaseTypeCombo);
            builder.nextLine();


            submitPurchase.setText("Submit");
            cancelPurchase .setText("Cancel");

            builder.append(submitPurchase);
            builder.append(cancelPurchase);

            add(builder.getPanel());

            submitPurchase.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {


                    Sales sales = new Sales();

                    sales.setCustomerName(vendorCombo.getSelectedItem().toString());
                    sales.setSalesOrder(salesOrderCombo.getSelectedItem().toString());
                    sales.setPrice(Double.parseDouble(priceTextField.getText()));
                    sales.setQuantity(Integer.parseInt(quantity.getText()));
                    sales.setSalesType(purchaseTypeCombo.getSelectedItem().toString());
                    Customer customer = CommonDao.findCustomerById(Integer.parseInt(vendorCombo.getSelectedItem().toString()));
                    String billingName = customer.getFirstName() + " " + customer.getLastName();
                    String billingAddress = customer.getBillingAddress();

//                    purchaseOrder.setVendorId(vendorCombo.getSelectedItem().toString());

                    DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
                    String autoName = dateFormatter.format(new Date());

                    if (JobDao.saveSales(sales)) {
                        JideOptionPane.showMessageDialog(null, "Sales Saved Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                        try{
//                            JasperReport jasperReport = (JasperReport) JRLoader.loadObject("com/wings4/reports/invoice.jasper");



                            Map parameters = new HashMap();

                            parameters.put("INV_SERIAL",autoName);
                            parameters.put("BILL_NUMBER", "BILL_NAF_SAL_" + autoName);
                            parameters.put("PARTY", billingName);
                            parameters.put("ADDRESS_PARTY", billingAddress);
                            parameters.put("LOGO_PATH", "com/wings4/resource/");
                            
                            Double quantity = sales.getQuantity().doubleValue();
                            Double unit = sales.getPrice();

                            List<Invoice> salesInvoiceList = new ArrayList<Invoice>();
                            Invoice invoice = new Invoice();
                            invoice.setPkid(1);
                            invoice.setName(sales.getProductName());
                            invoice.setQty(quantity);
                            invoice.setUnit(unit);
                            invoice.setRemarks(quantity * unit);
                            salesInvoiceList.add(invoice);
                            JRDataSource productDataSource = new JRBeanCollectionDataSource(salesInvoiceList);
                            JasperDesign jasperDesign = JRXmlLoader
                                    .load(new File("src//com/wings4//reports//invoice_bill.jrxml"));
                            JasperReport jasperReport = JasperCompileManager
                                    .compileReport(jasperDesign);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, productDataSource);
                            JasperViewer.viewReport(jasperPrint, false);
//                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, productDataSource);
                            JasperViewer.viewReport(jasperPrint);

                            GeneralToggleActionButton salesButton = new GeneralToggleActionButton(new
                                    SalesButtonTogglePanel());
                            salesButton.doClick();
                        } catch (Exception ex){
                            ex.printStackTrace();
                        }
                    } else {
                        JideOptionPane.showMessageDialog(null, "Sales Save Failed", "Failure",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
        }
    }

}