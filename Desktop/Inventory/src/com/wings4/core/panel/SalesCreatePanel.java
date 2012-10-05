package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.combobox.TableComboBox;
import com.jidesoft.dialog.JideOptionPane;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.dao.CommonDao;
import com.wings4.dao.JobDao;
import com.wings4.model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

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




            AnnotationResolver customerResolver = new AnnotationResolver( Customer.class);
            AnnotationResolver productResolver = new AnnotationResolver(Product.class);






            final ObjectTableModel<Customer> tableModelCustomer = new ObjectTableModel< Customer>(
                    customerResolver, "id,firstName");

            final ObjectTableModel<Product> tableModelProduct = new ObjectTableModel<Product>(
                    productResolver, "productId,productName");



            tableModelCustomer.setData(CommonDao.findAllCustomers());
            tableModelProduct.setData(CommonDao.findAllProducts());
            //tableModel.setData(MaterialDao.findAllCategories());



            final JTextField priceText = new JTextField();
            final JTextField quantityText = new JTextField();
            final JTextField salesOrderText = new JTextField();



            final TableComboBox productCombo = new TableComboBox(tableModelProduct);
            final TableComboBox customerCombo = new TableComboBox(tableModelCustomer);




            JButton submitSales = new JButton();
            JButton cancelSales = new JButton();

            builder.append("Customer:",customerCombo);
            builder.nextLine();
            builder.append("Product :",productCombo);
            builder.nextLine();
            builder.append("Price :", priceText);
            builder.nextLine();
            builder.append("Quantity :",quantityText);
            builder.nextLine();
            builder.append("Sales :",salesOrderText);
            builder.nextLine();




            submitSales.setText("Submit");
            cancelSales.setText("Cancel");

            builder.append(submitSales);
            builder.append(cancelSales);


            add(builder.getPanel());

            submitSales.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    Sales sales = new Sales();
                    sales.setCustomerName(customerCombo.getSelectedItem().toString());
                    sales.setProductName(productCombo.getSelectedItem().toString());
                    sales.setPrice(Double.parseDouble(priceText.getText()));
                    sales.setQuantity(Integer.parseInt(quantityText.getText()));
                    if (JobDao.saveSales(sales)) {
                        JideOptionPane.showMessageDialog(null, "Sales Saved Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JideOptionPane.showMessageDialog(null, "Sales Saved Failed", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

        }
    }

}