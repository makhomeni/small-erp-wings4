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
import org.jdesktop.swingx.JXTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderCreateButtonPanel extends JPanel {

    public PurchaseOrderCreateButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new PurchaseOrderCreate());
    }

    public class PurchaseOrderCreate extends JPanel {
        public PurchaseOrderCreate() {
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
            AnnotationResolver purchaseOrderResolver = new AnnotationResolver( PurchaseOrder.class);
            AnnotationResolver organizationResolver = new AnnotationResolver( Organization.class);
            AnnotationResolver shippingMethodResolver = new AnnotationResolver(ShippingMethod.class);
            AnnotationResolver vendorResolver = new AnnotationResolver(Vendor.class);
            AnnotationResolver productResolver = new AnnotationResolver(Product.class);
            AnnotationResolver paymentTermResolver = new AnnotationResolver(PaymentTerm.class);
            AnnotationResolver deliveryTermResolver = new AnnotationResolver(DeliveryTerm.class);

            final ObjectTableModel<PurchaseOrder> tableModel = new ObjectTableModel<PurchaseOrder>(
//                    ,organization,shippingMethod,paymentTerm
                    purchaseOrderResolver, "id,vendor");

//            tableModel.setData(MaterialDao.findAllCategories());
//            final ObjectTableModel<Organization> 
            final ObjectTableModel< Customer> tableModelCustomer = new ObjectTableModel< Customer>(
                    customerResolver, "id,firstName,lastName,emailId,organization,mobileNumber,phoneNumber,address,contact,reference,billingAddress");

            final ObjectTableModel<Organization> tableModelOrganization = new ObjectTableModel< Organization>(
                    organizationResolver, "id,organizationName");
            final ObjectTableModel<ShippingMethod> tableModelShippingMethod = new ObjectTableModel< ShippingMethod>(
                    shippingMethodResolver, "id,shippingMethod");

            final ObjectTableModel<Vendor> tableModelVendor = new ObjectTableModel< Vendor>(
                    vendorResolver, "id,firstName");

            final ObjectTableModel<Product> tableModelProduct = new ObjectTableModel<Product>(
                    productResolver, "productId,productName");


            final ObjectTableModel<PaymentTerm> tableModelPaymentTerm = new ObjectTableModel<PaymentTerm>(
                    paymentTermResolver, "paymentTermId,name");

            final ObjectTableModel<DeliveryTerm> tableModelDeliveryTerm= new ObjectTableModel<DeliveryTerm>(
                    deliveryTermResolver, "id,terms");

            tableModelOrganization.setData(CommonDao.findAllOrganizations());
            tableModelShippingMethod.setData(CommonDao.findAllShippingMethods());
            tableModelVendor.setData(CommonDao.findAllVendors());
            tableModelProduct.setData(CommonDao.findAllProducts());
            tableModelPaymentTerm.setData(CommonDao.findAllPaymentTerms());
            tableModelDeliveryTerm.setData(CommonDao.findAllDeliveryTerms());



            final TableComboBox vendorCombo = new TableComboBox(tableModelVendor);
            final TableComboBox productCombo = new TableComboBox(tableModelProduct);

            final  JTextField shippingAddressText = new JXTextField();
            final  JTextField jobNameTest = new JXTextField();

            final TableComboBox organizationCombo = new TableComboBox(tableModelOrganization);
            final TableComboBox shippingMethodCombo = new TableComboBox(tableModelShippingMethod);
            final TableComboBox paymentTermCombo = new TableComboBox(tableModelPaymentTerm);
            final TableComboBox deliveryTermCombo = new TableComboBox(tableModelDeliveryTerm);

            final JTextField orderQuantity = new JTextField();


            final TableComboBox parentCategory = new TableComboBox(tableModel);
            JButton submitPurchaseOrder = new JButton();
            JButton cancelPurchaseOrder = new JButton();


            builder.append("Vendor:", vendorCombo);
            builder.nextLine();

            builder.append("Product:", productCombo);
            builder.nextLine();

            builder.append("Job Name:", jobNameTest);
            builder.nextLine();

            builder.append("Shipping Address", shippingAddressText);
            builder.nextLine();

            builder.append("Organization:", organizationCombo);
            builder.nextLine();

            builder.append("Shipping Method:", shippingMethodCombo);
            builder.nextLine();

            builder.append("Payment Term", paymentTermCombo);
            builder.nextLine();

            builder.append("Delivery Term", deliveryTermCombo);
            builder.nextLine();

            builder.append("Order Quantity", orderQuantity);
            builder.nextLine();


            submitPurchaseOrder.setText("Submit");
            cancelPurchaseOrder.setText("Cancel");

            builder.append(submitPurchaseOrder);
            builder.append(cancelPurchaseOrder);

            add(builder.getPanel());

            submitPurchaseOrder.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {

                    PurchaseOrder purchaseOrder = new PurchaseOrder();

                    System.out.println(organizationCombo.getSelectedItem().toString());

                    purchaseOrder.setOrganizationId(organizationCombo.getSelectedItem().toString());
                    purchaseOrder.setPaymentTermId(paymentTermCombo.getSelectedItem().toString());
                    purchaseOrder.setShippingAddress(shippingAddressText.getText());
                    purchaseOrder.setShippingMethodId(shippingMethodCombo.getSelectedItem().toString());
                    purchaseOrder.setVendorId(vendorCombo.getSelectedItem().toString());
                    purchaseOrder.setProductId(productCombo.getSelectedItem().toString());
                    purchaseOrder.setDeliveryTermId(deliveryTermCombo.getSelectedItem().toString());
                    purchaseOrder.setOrderQuantity(orderQuantity.getText());
                    purchaseOrder.setJobName(jobNameTest.getText());

                    if (JobDao.savePurchaseOrder(purchaseOrder)) {
                        JideOptionPane.showMessageDialog(null, "Purchase Order Saved Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JideOptionPane.showMessageDialog(null, "Purchase Order Saved Failed", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
        }
    }

}
