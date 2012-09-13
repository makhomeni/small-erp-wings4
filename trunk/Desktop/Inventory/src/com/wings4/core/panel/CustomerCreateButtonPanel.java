package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.combobox.TableComboBox;
import com.jidesoft.dialog.JideOptionPane;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.dao.MaterialDao;
import com.wings4.model.Category;
import com.wings4.model.Customer;
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

            AnnotationResolver resolver = new AnnotationResolver( Customer.class);
            final ObjectTableModel< Customer> tableModel = new ObjectTableModel< Customer>(
                    resolver, "id,firstName,lastName,emailId,organization,mobileNumber,phoneNumber,address,contact,reference,billingAddress");
//            tableModel.setData(MaterialDao.findAllCategories());

            final  JTextField firstNameText = new JXTextField();
            final  JTextField lastNameText = new JXTextField();
            final  JTextField emailIdText = new JXTextField();
            final  JTextField mobileNumberText = new JXTextField();
            final  JTextField phoneNumberText = new JXTextField();
            final  JTextField addressText = new JXTextField();

            final TableComboBox organizationCombo = new TableComboBox(tableModel);
            final TableComboBox contactPersonCombo = new TableComboBox(tableModel);
            final TableComboBox referralCombo = new TableComboBox(tableModel);
            final TableComboBox billingAddressCombo = new TableComboBox(tableModel);

            final JTextField categoryNameText = new JTextField();
            final TableComboBox parentCategory = new TableComboBox(tableModel);
            JButton submitCategory = new JButton();
            JButton cancelCategory = new JButton();


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

            builder.append("Organization:", organizationCombo);
            builder.nextLine();

            builder.append("Contact Person:", contactPersonCombo);
            builder.nextLine();

            builder.append("Referral :", referralCombo);
            builder.nextLine();

            builder.append("Billing Address:", billingAddressCombo);
            builder.nextLine();

            submitCategory.setText("Submit");
            cancelCategory.setText("Cancel");

            builder.append(submitCategory);
            builder.append(cancelCategory);

            add(builder.getPanel());

            submitCategory.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    Category category = new Category();
                    category.setCategoryName(categoryNameText.getText());
                    try {
                        category.setParentCategory(parentCategory.getSelectedItem().toString());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if(MaterialDao.saveCategory(category))
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
