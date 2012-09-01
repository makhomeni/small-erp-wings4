package com.wings4.core.panel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.combobox.TableComboBox;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.dao.MaterialDao;
import com.wings4.model.Category;
import com.wings4.model.ProductClassification;
import com.wings4.model.ProductType;
import com.wings4.model.UnitOfMeasure;

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

            AnnotationResolver categoryResolver = new AnnotationResolver(Category.class);
            final ObjectTableModel<Category> categoryTableModel = new ObjectTableModel<Category>(
                    categoryResolver, "categoryId,categoryName,parentCategory");
            categoryTableModel.setData(MaterialDao.findAllCategories());


            AnnotationResolver productTypeResolver = new AnnotationResolver(ProductType.class);
            final ObjectTableModel<ProductType> productTypeTableModel = new ObjectTableModel<ProductType>(
                    productTypeResolver, "productTypeId,name,description");
            productTypeTableModel.setData(MaterialDao.findAllProductTypes());


            AnnotationResolver uomResolver = new AnnotationResolver(UnitOfMeasure.class);
            final ObjectTableModel<UnitOfMeasure> uomTableModel = new ObjectTableModel<UnitOfMeasure>(
                    uomResolver, "id,uom,description");
            uomTableModel.setData(MaterialDao.findAllUnitOfMeasures());


            AnnotationResolver classificationResolver = new AnnotationResolver(ProductClassification.class);
            final ObjectTableModel<ProductClassification> classificationTableModel
                    = new ObjectTableModel<ProductClassification>(classificationResolver, "classificationId," +
                    "classification,description");
            classificationTableModel.setData(MaterialDao.findAllProductClassifications());





            JTextField productNameText = new JTextField();
            TableComboBox productType = new TableComboBox(productTypeTableModel);
            TableComboBox category = new TableComboBox(categoryTableModel);
            TableComboBox productClassification = new TableComboBox(classificationTableModel);
            TableComboBox unitOfMeasure = new TableComboBox(uomTableModel);
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

}
