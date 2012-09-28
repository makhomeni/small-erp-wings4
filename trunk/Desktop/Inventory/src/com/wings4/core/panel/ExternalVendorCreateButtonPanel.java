package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.dialog.JideOptionPane;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.wings4.dao.CommonDao;
import com.wings4.dao.JobDao;
import com.wings4.model.ExternalVendor;
import com.wings4.model.LocalVendor;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/23/12
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExternalVendorCreateButtonPanel extends JPanel {

    public ExternalVendorCreateButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new ExternalVendorCreate());
    }

    public class ExternalVendorCreate extends JPanel {

        public ExternalVendorCreate() {
            DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
            builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 100px)");
            builder.appendColumn("5dlu");
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 10px)");

            final JTextField vendorFirstNameText = new JTextField();
            final JTextField vendorLastNameText = new JTextField();
            final JTextField addressNameText = new JTextField();
            final JTextField extendedAddressNameText = new JTextField();
            final JTextField countryText = new JTextField();
            final JTextField mobileNumberText = new JTextField();
            final JTextField phoneNumberText = new JTextField();
            final JTextField emailIdText = new JTextField();
            final JTextArea description = new JTextArea();

            JButton submit = new JButton();
            JButton cancel = new JButton();

            builder.append("First Name", vendorFirstNameText);
            builder.nextLine();

            builder.append("Last Name", vendorLastNameText);
            builder.nextLine();

            builder.append("Address 1", addressNameText);
            builder.nextLine();

            builder.append("Address 2", extendedAddressNameText);
            builder.nextLine();

            builder.append("Country", countryText);
            builder.nextLine();

            builder.append("Mobile Number", mobileNumberText);
            builder.nextLine();

            builder.append("Phone Number", phoneNumberText);
            builder.nextLine();

            builder.append("Email ID", emailIdText);
            builder.nextLine();

            builder.append("Description", description);
            builder.nextLine();

            submit.setText("Submit");
            cancel.setText("Cancel");

            submit.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
//                    CommonDao.saveExternalVendor(new ExternalVendor());
                    String localVendorName = vendorFirstNameText.getText().concat(" ").concat(vendorLastNameText.getText());
                    String address = addressNameText.getText().concat(" ").concat(extendedAddressNameText.getText());
                    String phoneNumber = mobileNumberText.getText().concat(" ").concat(phoneNumberText.getText());
                    String email = emailIdText.getText();
                    String descriptionTxt = description.getText();
                    String country = countryText.getText();

                    ExternalVendor externalVendor = new ExternalVendor();
                    externalVendor.setName(localVendorName);
                    externalVendor.setAddress(address);
                    externalVendor.setPhoneNumber(phoneNumber);
                    externalVendor.setEmail(email);
                    externalVendor.setDescription(descriptionTxt);
                    externalVendor.setCountry(country);
//                    CommonDao.saveLocalVendor(externalVendor);


                    if (CommonDao.saveExternalVendor(externalVendor)) {
                        JideOptionPane.showMessageDialog(null, "External Vendor Saved Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JideOptionPane.showMessageDialog(null, "External Vendor Save Failed", "Failure",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            });

            builder.append(submit);
            builder.append(cancel);

            add(builder.getPanel());
        }
    }
}
