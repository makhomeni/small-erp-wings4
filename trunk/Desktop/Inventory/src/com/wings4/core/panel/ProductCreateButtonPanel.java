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
import com.wings4.dao.JobDao;
import com.wings4.dao.MaterialDao;
import com.wings4.model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
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





            final JTextField productNameText = new JTextField();
            final TableComboBox productType = new TableComboBox(productTypeTableModel);
            final TableComboBox category = new TableComboBox(categoryTableModel);
            final TableComboBox productClassification = new TableComboBox(classificationTableModel);
            final TableComboBox unitOfMeasure = new TableComboBox(uomTableModel);
            final JTextField licenseInfoText = new JTextField();
            final JTextField stockKeepingUnitText = new JTextField();
            final JTextField universalProductCodeText = new JTextField();
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
            submitCategory.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {

                    Product product = new Product();
                    product.setProductName(productNameText.getText());
                    product.setProductCategory(category.getSelectedItem().toString());
                    product.setProductType(productType.getSelectedItem().toString());
                    product.setProductClassification(productClassification.getSelectedItem().toString());
                    product.setStockKeepingUnit(stockKeepingUnitText.getText());
                    product.setUniversalProductCode(universalProductCodeText.getText());
                    product.setUnitOfMeasure(unitOfMeasure.getSelectedItem().toString());
                    product.setLicenseInfo(licenseInfoText.getText());

                    if (CommonDao.saveProduct(product))
                        JideOptionPane.showMessageDialog(null, "Product Saved Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JideOptionPane.showMessageDialog(null, "Product Saved Failed", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

}
