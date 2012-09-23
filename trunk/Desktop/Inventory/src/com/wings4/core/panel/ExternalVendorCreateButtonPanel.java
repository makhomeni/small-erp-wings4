package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.wings4.dao.CommonDao;
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

            JTextField vendorFirstNameText = new JTextField();
            JTextField vendorLastNameText = new JTextField();
            JTextField addressNameText = new JTextField();
            JTextField extendedAddressNameText = new JTextField();
            JTextField countryText = new JTextField();
            JTextField mobileNumberText = new JTextField();
            JTextField phoneNumberText = new JTextField();
            JTextField emailIdText = new JTextField();
            JTextArea description = new JTextArea();

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
                    CommonDao.saveLocalVendor(new LocalVendor());
                }
            });

            builder.append(submit);
            builder.append(cancel);

            add(builder.getPanel());
        }
    }
}
