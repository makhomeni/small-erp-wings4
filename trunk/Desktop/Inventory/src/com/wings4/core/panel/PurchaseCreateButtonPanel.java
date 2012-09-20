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
public class PurchaseCreateButtonPanel extends JPanel {

    public PurchaseCreateButtonPanel() {
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


            AnnotationResolver vendorResolver = new AnnotationResolver(Vendor.class);



            final ObjectTableModel<Vendor> tableModelVendor = new ObjectTableModel< Vendor>(
                    vendorResolver, "id,firstName");




            tableModelVendor.setData(CommonDao.findAllVendors());




            final TableComboBox vendorCombo = new TableComboBox(tableModelVendor);







            JButton submitPurchaseOrder = new JButton();
            JButton cancelPurchaseOrder = new JButton();


            builder.append("Vendor:", vendorCombo);
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


                    purchaseOrder.setVendor(vendorCombo.getSelectedItem().toString());


                    if (CommonDao.savePurchaseOrder(purchaseOrder)) {
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
