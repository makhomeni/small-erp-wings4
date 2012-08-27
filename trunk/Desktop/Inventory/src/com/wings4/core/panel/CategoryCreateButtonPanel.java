package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.combobox.TableComboBox;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Category;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/24/12
 * Time: 2:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryCreateButtonPanel extends JPanel {

    public CategoryCreateButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new CategoryCreate());
    }

    public class CategoryCreate extends JPanel {
        public CategoryCreate() {
            DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
            builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 100px)");
            builder.appendColumn("5dlu");
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 10px)");

            AnnotationResolver resolver = new AnnotationResolver(Category.class);
            final ObjectTableModel<Category> tableModel = new ObjectTableModel<Category>(
                    resolver, "categoryId,categoryName,parentCategory");
            tableModel.setData(getData());


            JTextField categoryNameText = new JTextField();
            TableComboBox parentCategory = new TableComboBox(tableModel);
            JButton submitCategory = new JButton();
            JButton cancelCategory = new JButton();

            builder.append("Category Name:", categoryNameText);
            builder.nextLine();

            builder.append("Parent Category:", parentCategory);
            builder.nextLine();

            submitCategory.setText("Submit");
            cancelCategory.setText("Cancel");

            builder.append(submitCategory);
            builder.append(cancelCategory);

            add(builder.getPanel());
        }
    }

    private List<Category> getData() {
        List<Category> list = new ArrayList<Category>();
        return list;
    }
}
