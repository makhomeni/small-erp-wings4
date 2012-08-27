/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CategoryList.java
 *
 * Created on Aug 16, 2012, 3:57:25 PM
 */
package com.wings4.core;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Category;
import com.wings4.util.InventoryConstants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronnie
 */
public class CategoryList extends javax.swing.JFrame {

    /** Creates new form CategoryList */
    public CategoryList() {
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

        jToolBar1 = new javax.swing.JToolBar();
        createCategoryButton = new javax.swing.JButton();
        categoryScrollPane = new javax.swing.JScrollPane();
        categoryTable = new javax.swing.JTable();

        AnnotationResolver resolver = new AnnotationResolver(Category.class);
        final ObjectTableModel<Category> tableModel = new ObjectTableModel<Category>(
                resolver, "categoryId,categoryName,parentCategory");

        tableModel.setData(getData());
        categoryTable.setModel(tableModel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        createCategoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(InventoryConstants.resourceDirectory.
                concat("list-add.png"))));
        createCategoryButton.setFocusable(false);
        createCategoryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createCategoryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(createCategoryButton);

        categoryScrollPane.setViewportView(categoryTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
            .addComponent(categoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CategoryList().setVisible(true);
            }
        });
    }

    private List<Category> getData() {
        List<Category> list = new ArrayList<Category>();
        return list;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createCategoryButton;
    private javax.swing.JScrollPane categoryScrollPane;
    private javax.swing.JTable categoryTable;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
