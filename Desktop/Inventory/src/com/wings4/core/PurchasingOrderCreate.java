/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PurchasingOrderCreate.java
 *
 * Created on Aug 6, 2012, 10:50:47 AM
 */
package com.wings4.core;

import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.style.framework.*;
import com.wings4.util.JEclipseTabbedPane;
import com.wings4.Login;
import com.wings4.util.InventoryConstants;
import com.wings4.util.InventoryInternalBase;
import com.wings4.util.RESTFeed;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Masum
 */
public class PurchasingOrderCreate extends InventoryInternalBase {

    /** Creates new form PurchasingOrderCreate */
    public PurchasingOrderCreate() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        purchaseOrderTabbedPane = new JEclipseTabbedPane();
        purchaseOrderPane = new javax.swing.JPanel();
        jobNameLabel = new javax.swing.JLabel();
        jobNameText = new javax.swing.JTextField();
        orderQuantityLabel = new javax.swing.JLabel();
        orderQuantityText = new javax.swing.JTextField();
        createDateLabel = new javax.swing.JLabel();
        createdDateText = new javax.swing.JTextField();
        createdByLabel = new javax.swing.JLabel();
        createByText = new javax.swing.JTextField();
        statusLabel = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        isArchivedLabel = new javax.swing.JLabel();
        isArchivedCheckBox = new javax.swing.JCheckBox();
        porderCreateButton = new javax.swing.JButton();
        poInformationCancelButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        vendorLabel = new javax.swing.JLabel();
        organizationLabel = new javax.swing.JLabel();
        shippingAddressLabel = new javax.swing.JLabel();
        poDetailsButton = new javax.swing.JButton();
        poDetailsCancelButton = new javax.swing.JButton();
        vendorComboBox = new javax.swing.JComboBox();
        organizationComboBox = new javax.swing.JComboBox();
        shippingAdressComboBox = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        paymentTermLabel = new javax.swing.JLabel();
        deliveryTermLabel = new javax.swing.JLabel();
        paymentTermComboBox = new javax.swing.JComboBox();
        deliveryTermComboBox = new javax.swing.JComboBox();
        dueDateLabel = new javax.swing.JLabel();
        dueDateTextField = new javax.swing.JTextField();
        poTermsConditionButton = new javax.swing.JButton();
        poCancelButton = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Purchasing Order Create");

        jobNameLabel.setText("Job Name");

        orderQuantityLabel.setText("Order Quantity");

        createDateLabel.setText("Created Date");

        createdByLabel.setText("Created By");

        statusLabel.setText("Status");

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        isArchivedLabel.setText("Is Archived");

        porderCreateButton.setText("Submit");
        porderCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porderCreateButtonActionPerformed(evt);
            }
        });

        poInformationCancelButton.setText("Cancel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(purchaseOrderPane);
        purchaseOrderPane.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(createDateLabel)
                                        .addComponent(jobNameLabel)
                                        .addComponent(orderQuantityLabel)
                                        .addComponent(createdByLabel)
                                        .addComponent(statusLabel)
                                        .addComponent(isArchivedLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(porderCreateButton)
                                                        .addGap(35, 35, 35)
                                                        .addComponent(poInformationCancelButton))
                                                .addComponent(createByText, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                .addComponent(createdDateText, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                .addComponent(orderQuantityText, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                .addComponent(jobNameText, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                .addComponent(statusComboBox, 0, 225, Short.MAX_VALUE))
                                        .addComponent(isArchivedCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jobNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jobNameLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(orderQuantityLabel)
                                        .addComponent(orderQuantityText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(createDateLabel)
                                        .addComponent(createdDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(createByText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createdByLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(statusLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(isArchivedCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(porderCreateButton)
                                                        .addComponent(poInformationCancelButton)))
                                        .addComponent(isArchivedLabel))
                                .addContainerGap(26, Short.MAX_VALUE))
        );

        //purchaseOrderTabbedPane.setUI(new Eclipse3xJideTabbedPaneUI());
        purchaseOrderTabbedPane.setTabShape(0, new JRibbonStyle());
        purchaseOrderTabbedPane.setBoldActiveTab(true);

        purchaseOrderTabbedPane.setTabShape(new JEclipseStyle());

        purchaseOrderTabbedPane.addTab("General Information", IconFactory.getSwingIcon("component/tabbed_pane_16.png"), purchaseOrderPane);


        vendorLabel.setText("LocalVendor");

        organizationLabel.setText("Organization");

        shippingAddressLabel.setText("Shipping Address");

        poDetailsButton.setText("Submit");
        poDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poDetailsButtonActionPerformed(evt);
            }
        });

        poDetailsCancelButton.setText("Cancel");

        vendorComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        organizationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        shippingAdressComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(organizationLabel)
                                        .addComponent(vendorLabel)
                                        .addComponent(shippingAddressLabel))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(vendorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(organizationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(shippingAdressComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(59, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(179, Short.MAX_VALUE)
                                .addComponent(poDetailsButton)
                                .addGap(18, 18, 18)
                                .addComponent(poDetailsCancelButton)
                                .addGap(130, 130, 130))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(vendorLabel)
                                        .addComponent(vendorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(organizationLabel)
                                        .addComponent(organizationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(shippingAddressLabel)
                                        .addComponent(shippingAdressComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(poDetailsButton)
                                        .addComponent(poDetailsCancelButton))
                                .addContainerGap(113, Short.MAX_VALUE))
        );

        purchaseOrderTabbedPane.addTab("Detail", jPanel2);

        paymentTermLabel.setText("Payment Term");

        deliveryTermLabel.setText("Delivery Term");

        paymentTermComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        deliveryTermComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4" }));

        dueDateLabel.setText("Due Date");

        poTermsConditionButton.setText("Submit");

        poCancelButton.setText("Cancel");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(dueDateLabel)
                                        .addComponent(paymentTermLabel)
                                        .addComponent(deliveryTermLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(poTermsConditionButton)
                                                .addGap(30, 30, 30)
                                                .addComponent(poCancelButton))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(dueDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                .addComponent(deliveryTermComboBox, 0, 225, Short.MAX_VALUE)
                                                .addComponent(paymentTermComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentTermLabel)
                    .addComponent(paymentTermComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliveryTermComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryTermLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueDateLabel)
                    .addComponent(dueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(poTermsConditionButton)
                    .addComponent(poCancelButton))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        purchaseOrderTabbedPane.addTab("Terms & Conditions", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(purchaseOrderTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(purchaseOrderTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void porderCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porderCreateButtonActionPerformed
// TODO add your handling code here:
    JSONObject purchasingOrderInformationResourceObject = new JSONObject();
    try{
        purchasingOrderInformationResourceObject.put("jobName",jobNameText.getText());
        purchasingOrderInformationResourceObject.put("orderQuantity",orderQuantityText.getText());
        purchasingOrderInformationResourceObject.put("createDate",createdDateText.getText());
        purchasingOrderInformationResourceObject.put("createdBy",createByText.getText());
        purchasingOrderInformationResourceObject.put("status",statusComboBox.getSelectedItem().toString());
        purchasingOrderInformationResourceObject.put("isArchived",Boolean.valueOf(isArchivedCheckBox.isSelected()));
        
    } catch(JSONException ex){
        
        Logger.getLogger(PurchasingOrderCreate.class.getName()).log(Level.SEVERE, null, ex);  
    }
     String restEndPoint = Login.getRestEndPoint();
        String resource = "purchasingOrderInformationRegister";
        
        RESTFeed restFeed = new RESTFeed(InventoryConstants.MEDIA_JSON,
                InventoryConstants.MEDIA_JSON,InventoryConstants.POST, 
                restEndPoint, resource);
    restFeed.setJsonObject(purchasingOrderInformationResourceObject);
    try {
        restFeed.restInitialization();
    } catch (IOException ex) {
        Logger.getLogger(PurchasingOrderCreate.class.getName()).log(Level.SEVERE, null, ex);
    }                
    
}//GEN-LAST:event_porderCreateButtonActionPerformed

private void poDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poDetailsButtonActionPerformed
// TODO add your handling code here:
    
    
}//GEN-LAST:event_poDetailsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchasingOrderCreate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PurchasingOrderCreate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PurchasingOrderCreate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PurchasingOrderCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PurchasingOrderCreate().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField createByText;
    private javax.swing.JLabel createDateLabel;
    private javax.swing.JLabel createdByLabel;
    private javax.swing.JTextField createdDateText;
    private javax.swing.JComboBox deliveryTermComboBox;
    private javax.swing.JLabel deliveryTermLabel;
    private javax.swing.JLabel dueDateLabel;
    private javax.swing.JTextField dueDateTextField;
    private javax.swing.JCheckBox isArchivedCheckBox;
    private javax.swing.JLabel isArchivedLabel;
    private javax.swing.JPanel purchaseOrderPane;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private JEclipseTabbedPane purchaseOrderTabbedPane;
    private javax.swing.JLabel jobNameLabel;
    private javax.swing.JTextField jobNameText;
    private javax.swing.JLabel orderQuantityLabel;
    private javax.swing.JTextField orderQuantityText;
    private javax.swing.JComboBox organizationComboBox;
    private javax.swing.JLabel organizationLabel;
    private javax.swing.JComboBox paymentTermComboBox;
    private javax.swing.JLabel paymentTermLabel;
    private javax.swing.JButton poCancelButton;
    private javax.swing.JButton poDetailsButton;
    private javax.swing.JButton poDetailsCancelButton;
    private javax.swing.JButton poInformationCancelButton;
    private javax.swing.JButton poTermsConditionButton;
    private javax.swing.JButton porderCreateButton;
    private javax.swing.JLabel shippingAddressLabel;
    private javax.swing.JComboBox shippingAdressComboBox;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JComboBox vendorComboBox;
    private javax.swing.JLabel vendorLabel;
    // End of variables declaration//GEN-END:variables
}
