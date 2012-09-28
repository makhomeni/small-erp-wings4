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
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 4:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class SalesOrderCreatePanel  extends JPanel {

    public SalesOrderCreatePanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new SalesOrderCreate());
    }

    public class SalesOrderCreate extends JPanel {
        public SalesOrderCreate() {
            DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
            builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 100px)");
            builder.appendColumn("5dlu");
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 10px)");

            AnnotationResolver resolver = new AnnotationResolver(SalesOrder.class);

            AnnotationResolver deliveryTermResolver = new AnnotationResolver(DeliveryTerm.class);
            AnnotationResolver customerResolver = new AnnotationResolver( Customer.class);
            AnnotationResolver shippingMethodResolver = new AnnotationResolver(ShippingMethod.class);
            AnnotationResolver paymentTermResolver = new AnnotationResolver(PaymentTerm.class);
            AnnotationResolver productResolver = new AnnotationResolver(Product.class);



            final ObjectTableModel<DeliveryTerm> tableModelDeliveryTerm= new ObjectTableModel<DeliveryTerm>(
                    deliveryTermResolver, "id,terms");

            final ObjectTableModel<Customer> tableModelCustomer = new ObjectTableModel< Customer>(
                    customerResolver, "id,firstName,lastName,emailId,organization,mobileNumber,phoneNumber,address,contact,reference,billingAddress");

            final ObjectTableModel<ShippingMethod> tableModelShippingMethod = new ObjectTableModel< ShippingMethod>(
                    shippingMethodResolver, "id,shippingMethod");

            final ObjectTableModel<PaymentTerm> tableModelPaymentTerm = new ObjectTableModel<PaymentTerm>(
                    paymentTermResolver, "paymentTermId,name");

            final ObjectTableModel<Product> tableModelProduct = new ObjectTableModel<Product>(
                    productResolver, "productId,productName");


            tableModelDeliveryTerm.setData(CommonDao.findAllDeliveryTerms());
            tableModelShippingMethod.setData(CommonDao.findAllShippingMethods());
            tableModelPaymentTerm.setData(CommonDao.findAllPaymentTerms());
            tableModelCustomer.setData(CommonDao.findAllCustomers());
            tableModelProduct.setData(CommonDao.findAllProducts());


            //tableModel.setData(MaterialDao.findAllCategories());




            final JTextField jobNameText = new JTextField();
            final JTextField orderQuantityText = new JTextField();

            final JTextField address1Text = new JTextField();
            final JTextField address2Text = new JTextField();


            final TableComboBox deliveryTermCombo = new TableComboBox(tableModelDeliveryTerm);
            final TableComboBox customerCombo = new TableComboBox(tableModelCustomer);
            final TableComboBox shippingMethodCombo = new TableComboBox(tableModelShippingMethod);
            final TableComboBox paymentTermCombo = new TableComboBox(tableModelPaymentTerm);
            final TableComboBox productCombo = new TableComboBox(tableModelPaymentTerm);



            JButton submitSalesOrder = new JButton();
            JButton cancelSalesOrder = new JButton();

            builder.append("Job Name :",jobNameText);
            builder.nextLine();
            builder.append("Order Quantity", orderQuantityText);
            builder.nextLine();
            builder.append("Delivery Term :",deliveryTermCombo);
            builder.nextLine();
            builder.append("Customer :",customerCombo);
            builder.nextLine();
            builder.append("Product :",productCombo);
            builder.nextLine();
            builder.append("Address1 :",address1Text);
            builder.nextLine();
            builder.append("Address2 :",address2Text);
            builder.nextLine();
            builder.append("Shipping Method :",shippingMethodCombo);
            builder.nextLine();
            builder.append("Payment Term :",paymentTermCombo);
            builder.nextLine();



            submitSalesOrder.setText("Submit");
            cancelSalesOrder.setText("Cancel");

            builder.append(submitSalesOrder);
            builder.append(cancelSalesOrder);


            add(builder.getPanel());

            submitSalesOrder.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
//                    Category category = new Category();
//                    category.setCategoryName(categoryNameText.getText());
////                    try {
////                        category.setParentCategory(parentCategory.getSelectedItem().toString());
////                    } catch (Exception ex) {
////                        ex.printStackTrace();
////                    }
                    SalesOrder salesOrder = new SalesOrder();

                    salesOrder.setJobName(jobNameText.getText());
                    salesOrder.setOrderQuantity(orderQuantityText.getText());
                    salesOrder.setDeliveryTerm(deliveryTermCombo.getSelectedItem().toString());
                    salesOrder.setCustomer(customerCombo.getSelectedItem().toString());
                    salesOrder.setProduct(productCombo.getSelectedItem().toString());
                    salesOrder.setAddress1(address1Text.getText());
                    salesOrder.setAddress2(address2Text.getText());
                    salesOrder.setShippingMethod(shippingMethodCombo.getSelectedItem().toString());
                    salesOrder.setPaymentTerm(shippingMethodCombo.getSelectedItem().toString());


//                    salesOrder.set

                    if (JobDao.saveSalesOrder(salesOrder))
                        JideOptionPane.showMessageDialog(null, "Category Saved Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JideOptionPane.showMessageDialog(null, "Category Saved Failed", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

}
