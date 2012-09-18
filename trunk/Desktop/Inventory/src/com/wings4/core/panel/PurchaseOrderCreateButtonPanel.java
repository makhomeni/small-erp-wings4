package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.combobox.TableComboBox;
import com.jidesoft.dialog.JideOptionPane;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.VendorButtonTogglePanel;
import com.wings4.dao.CommonDao;
import com.wings4.dao.MaterialDao;
import com.wings4.model.*;
import org.jdesktop.swingx.JXTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

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
            AnnotationResolver paymentTermResolver = new AnnotationResolver(PaymentTerm.class);

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

            final ObjectTableModel<PaymentTerm> tableModelPaymentTerm = new ObjectTableModel<PaymentTerm>(
                    paymentTermResolver, "paymentTermId,name");

            tableModelOrganization.setData(CommonDao.findAllOrganizations());
            tableModelShippingMethod.setData(CommonDao.findAllShippingMethods());
            tableModelVendor.setData(CommonDao.findAllVendors());
            tableModelPaymentTerm.setData(CommonDao.findAllPaymentTerms());



            final TableComboBox vendorCombo = new TableComboBox(tableModelVendor);

            final  JTextField shippingAddressText = new JXTextField();

            final TableComboBox organizationCombo = new TableComboBox(tableModelOrganization);
            final TableComboBox shippingMethodCombo = new TableComboBox(tableModelShippingMethod);
            final TableComboBox paymentTermCombo = new TableComboBox(tableModelPaymentTerm);


            final TableComboBox parentCategory = new TableComboBox(tableModel);
            JButton submitPurchaseOrder = new JButton();
            JButton cancelPurchaseOrder = new JButton();


            builder.append("Vendor:", vendorCombo);
            builder.nextLine();

            builder.append("Shipping Address", shippingAddressText);
            builder.nextLine();

            builder.append("Organization:", organizationCombo);
            builder.nextLine();

            builder.append("Shipping Method:", shippingMethodCombo);
            builder.nextLine();

            builder.append("Payment Term", paymentTermCombo);
            builder.nextLine();


            submitPurchaseOrder.setText("Submit");
            cancelPurchaseOrder.setText("Cancel");

            builder.append(submitPurchaseOrder);
            builder.append(cancelPurchaseOrder);

            add(builder.getPanel());

            submitPurchaseOrder.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    JideOptionPane.showMessageDialog(null, "Purchase Order Submitted Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    PurchaseOrder purchaseOrder = new PurchaseOrder();
                    System.out.println(organizationCombo.getSelectedItem().toString());
                    purchaseOrder.setOrganization(organizationCombo.getSelectedItem().toString());
                    purchaseOrder.setPaymentTerm(paymentTermCombo.getSelectedItem().toString());
                    purchaseOrder.setShippingAddress(shippingAddressText.getText());
                    purchaseOrder.setShippingMethod(shippingMethodCombo.getSelectedItem().toString());
                    purchaseOrder.setVendor(vendorCombo.getSelectedItem().toString());
                    CommonDao.savePurchaseOrder(purchaseOrder);
//                    Category category = new Category();
//                    category.setCategoryName(categoryNameText.getText());
//                    try {
//                        category.setParentCategory(parentCategory.getSelectedItem().toString());
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//
//                    if(MaterialDao.saveCategory(category))
//                        JideOptionPane.showMessageDialog(null, "Category Saved Successfully", "Success",
//                                JOptionPane.INFORMATION_MESSAGE);
//                    else
//                        JideOptionPane.showMessageDialog(null, "Category Saved Failed", "Failed",
//                                JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

}
