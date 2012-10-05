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
import com.wings4.dao.MaterialDao;
import com.wings4.model.Category;
import com.wings4.model.Customer;
import com.wings4.model.Organization;
import org.jdesktop.swingx.JXTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 9/13/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerCreateButtonPanel  extends JPanel {

    public CustomerCreateButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new CustomerCreate());
    }

    public class CustomerCreate extends JPanel {
        public CustomerCreate() {
            DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
            builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 100px)");
            builder.appendColumn("5dlu");
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 10px)");

            AnnotationResolver organizationResolver = new AnnotationResolver( Organization.class);
            final ObjectTableModel<Organization> tableModelOrganization = new ObjectTableModel< Organization>(
                    organizationResolver, "id,organizationName");
            tableModelOrganization.setData(CommonDao.findAllOrganizations());

            final  JTextField firstNameText = new JXTextField();
            final  JTextField lastNameText = new JXTextField();
            final  JTextField emailIdText = new JXTextField();
            final  JTextField mobileNumberText = new JXTextField();
            final  JTextField phoneNumberText = new JXTextField();
            final  JTextField addressText = new JXTextField();
            final  JTextField billingAddressText = new JXTextField();

            final TableComboBox organizationCombo = new TableComboBox(tableModelOrganization);
//            final TableComboBox contactPersonCombo = new TableComboBox(tableModel);
//            final TableComboBox referralCombo = new TableComboBox(tableModel);
//            final TableComboBox billingAddressCombo = new TableComboBox(tableModel);

//            final JTextField categoryNameText = new JTextField();
//            final TableComboBox parentCategory = new TableComboBox(tableModel);
            JButton submitCustomer = new JButton();
            JButton cancelCustomer = new JButton();


            builder.append("First Name:", firstNameText);
            builder.nextLine();

            builder.append("Last Name:", lastNameText);
            builder.nextLine();

            builder.append("Email Address:", emailIdText);
            builder.nextLine();

            builder.append("Mobile Number:", mobileNumberText);
            builder.nextLine();

            builder.append("Phone Number:", phoneNumberText);
            builder.nextLine();

            builder.append("Address:", addressText);
            builder.nextLine();

            builder.append(" Billing Address :",billingAddressText);
            builder.nextLine();

            builder.append("Organization:", organizationCombo);
            builder.nextLine();
//


            submitCustomer.setText("Submit");
            cancelCustomer.setText("Cancel");

            builder.append(submitCustomer);
            builder.append(cancelCustomer);

            add(builder.getPanel());

            submitCustomer.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {

                    Customer customer = new Customer();
                    customer.setFirstName(firstNameText.getText());
                    customer.setLastName(lastNameText.getText());
                    customer.setEmailId(emailIdText.getText());
                    customer.setOrganization(organizationCombo.getSelectedItem().toString());
                    customer.setMobileNumber(mobileNumberText.getText());
                    customer.setPhoneNumber(phoneNumberText.getText());
                    customer.setAddress(addressText.getText());
                    customer.setBillingAddress(billingAddressText.getText());

                    if(CommonDao.saveCustomer(customer))
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
