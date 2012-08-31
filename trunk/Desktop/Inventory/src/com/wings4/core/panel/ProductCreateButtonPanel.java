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
 * Date: 8/31/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductCreateButtonPanel extends JPanel {

    public ProductCreateButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new ProductCreate());
    }

    public class ProductCreate extends JPanel {
        public ProductCreate() {
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
            tableModel.setData(getData());


            JTextField productNameText = new JTextField();
            TableComboBox productType = new TableComboBox();
            TableComboBox category = new TableComboBox(tableModel);
            TableComboBox productClassification = new TableComboBox();
            TableComboBox unitOfMeasure = new TableComboBox();
            JTextField licenseInfoText = new JTextField();
            JTextField stockKeepingUnitText = new JTextField();
            JTextField universalProductCodeText = new JTextField();
            JButton submitCategory = new JButton();
            JButton cancelCategory = new JButton();

            builder.append("Product Name:", productNameText);
            builder.nextLine();

            builder.append("Category:", category);
            builder.nextLine();

            builder.append("Product Type:", productType);
            builder.nextLine();

            builder.append("License Info:", licenseInfoText);
            builder.nextLine();

            builder.append("Classification:", productClassification);
            builder.nextLine();

            builder.append("Unit Of Measure:", unitOfMeasure);
            builder.nextLine();

            builder.append("SKU:", stockKeepingUnitText);
            builder.nextLine();

            builder.append("UPC:", universalProductCodeText);
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
