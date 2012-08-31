package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.combobox.TableComboBox;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.model.Category;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/31/12
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryCreateButtonPanel extends JPanel {

    public InventoryCreateButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new InventoryCreate());
    }


    public class InventoryCreate extends JPanel {
        public InventoryCreate() {
            DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
            builder.setBorder(BorderFactory.createEmptyBorder(2, 3, 2, 3));
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 10px)");
            builder.appendColumn("5dlu");
            builder.appendColumn("right:pref");
            builder.appendColumn("3dlu");
            builder.appendColumn("fill:max(pref; 10px)");

            AnnotationResolver resolver = new AnnotationResolver(Category.class);
            final ObjectTableModel<Category> tableModel = new ObjectTableModel<Category>(
                    resolver, "categoryId,categoryName,parentCategory");
            tableModel.setData(null);


            TableComboBox product = new TableComboBox();
            JTextField onHandText = new JTextField();
            JTextField onOrderText = new JTextField();
            JTextField allocatedText = new JTextField();
            JTextField committedText = new JTextField();
            JTextField unavailableText = new JTextField();
            JTextField backOrderedText = new JTextField();
            JTextField dropShipText = new JTextField();
            JTextField availableForSaleText = new JTextField();
            JTextField availableToPickText = new JTextField();
            JButton submitCategory = new JButton();
            JButton cancelCategory = new JButton();

            builder.append("Product:", product);
            builder.nextLine();

            builder.append("On Hand:", onHandText);
            builder.nextLine();

            builder.append("On Order:", onOrderText);
            builder.nextLine();

            builder.append("Allocated:", allocatedText);
            builder.nextLine();

            builder.append("Committed:", committedText);
            builder.nextLine();

            builder.append("Unavailable:", unavailableText);
            builder.nextLine();

            builder.append("Back Ordered:", backOrderedText);
            builder.nextLine();

            builder.append("Drop Ship:", dropShipText);
            builder.nextLine();

            builder.append("Available for Sale:", availableForSaleText);
            builder.nextLine();

            builder.append("Available to Pick:", availableToPickText);
            builder.nextLine();

            submitCategory.setText("Submit");
            cancelCategory.setText("Cancel");

            builder.append(submitCategory);
            builder.append(cancelCategory);

            add(builder.getPanel());
        }
    }
}
