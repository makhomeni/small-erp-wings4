package com.wings4.client;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.button.JBasicButton;
import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.nepxion.swing.style.button.flat.FlatButtonUI;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Category;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/22/12
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryButtonPanel extends JPanel {

    private javax.swing.JScrollPane categoryScrollPane;
    private javax.swing.JTable categoryTable;

    public CategoryButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new CategoryListPanel());
    }

    public class CategoryListPanel extends JPanel {
        public CategoryListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Category List"));

            categoryScrollPane = new javax.swing.JScrollPane();
            categoryTable = new javax.swing.JTable();

            AnnotationResolver resolver = new AnnotationResolver(Category.class);
            final ObjectTableModel<Category> tableModel = new ObjectTableModel<Category>(
                    resolver, "categoryId,categoryName,parentCategory");

            tableModel.setData(getData());
            categoryTable.setModel(tableModel);

            categoryScrollPane.setViewportView(categoryTable);

            add(categoryScrollPane);
        }
    }

    private List<Category> getData() {
        List<Category> list = new ArrayList<Category>();
        return list;
    }
}
