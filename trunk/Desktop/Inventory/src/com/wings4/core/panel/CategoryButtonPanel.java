package com.wings4.core.panel;

import com.jidesoft.navigation.BreadcrumbBar;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.dao.MaterialDao;
import com.wings4.model.Category;
import com.wings4.core.toggle.CategoryCreateTogglePanel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.util.InventoryConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    private JScrollPane categoryScrollPane;
    private JTable categoryTable;
    private JToolBar categoryToolBar;
    private JButton createCategoryButton;
    private BreadcrumbBar breadcrumbBar;

    public CategoryButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new CategoryListPanel());
    }


    public class CategoryListPanel extends JPanel {
        public CategoryListPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder("Category List"));

            categoryToolBar = new JToolBar();
            createCategoryButton = new JButton();
            breadcrumbBar = new BreadcrumbBar();


            createCategoryButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton categoryButton = new GeneralToggleActionButton(new
                            CategoryCreateTogglePanel());
                     categoryButton.doClick();
                }
            });

            createCategoryButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                    concat("list-add.png"))));
            createCategoryButton.setFocusable(false);
            createCategoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createCategoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            categoryToolBar.add(createCategoryButton);

            categoryScrollPane = new JScrollPane();
            categoryTable = new JTable();

            AnnotationResolver resolver = new AnnotationResolver(Category.class);
            final ObjectTableModel<Category> tableModel = new ObjectTableModel<Category>(
                    resolver, "categoryId,categoryName,parentCategory");

            tableModel.setData(MaterialDao.findAllCategories());
            categoryTable.setModel(tableModel);
            categoryScrollPane.setViewportView(categoryTable);
            add(breadcrumbBar, BorderLayout.PAGE_START);
            add(categoryToolBar, BorderLayout.PAGE_START);
            add(categoryScrollPane, BorderLayout.CENTER);
        }
    }

    private List<Category> getData() {
        List<Category> list = new ArrayList<Category>();
        return list;
    }
}
