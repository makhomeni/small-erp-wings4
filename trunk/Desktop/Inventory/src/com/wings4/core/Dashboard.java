package com.wings4.core;

import com.jidesoft.swing.JideButton;
import com.nepxion.swing.framework.JFrameWorkStatusBar;
import com.nepxion.swing.framework.dockable.JDockable;
import com.nepxion.swing.framework.dockable.JDockableHierarchy;
import com.nepxion.swing.framework.dockable.JDockableView;
import com.wings4.controller.MaterialsController;
import com.wings4.core.toggle.AppToggleContentPanel;
import com.wings4.util.IconFactory;
import com.nepxion.swing.menubar.JBasicMenuBar;
import com.nepxion.swing.menuitem.JBasicMenuItem;
import com.nepxion.swing.style.texture.ITextureStyle;
import com.nepxion.swing.style.texture.basic.JBlueTextureStyle;
import com.nepxion.swing.toolbar.JBasicToolBar;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class Dashboard extends JDockableHierarchy {

    public static final String EXPLORER_TITLE = "Explorer";
    public static final String CONTENT_PANE_TITLE = "ContentPane";


    public Dashboard() {
        initContentPane();
        initMenuBar();
        initToolBar();
        initStatusBar();
    }

    private void initContentPane(){
        JDockableView explorerView = new JDockableView(EXPLORER_TITLE, IconFactory.getIcon("s_logo.png"),
                EXPLORER_TITLE,ExplorerBar.getInstance());
        JDockableView contentPaneView = new JDockableView(CONTENT_PANE_TITLE, IconFactory.getIcon("s_logo.png"),
                CONTENT_PANE_TITLE, AppToggleContentPanel.getInstance());

        JDockable dockable = new JDockable();
        dockable.setOrientation(JDockable.HORIZONTAL_SPLIT);
        dockable.add(explorerView, JDockable.FLEXIBLE);
        dockable.add(contentPaneView, JDockable.VARY);

        dockable.setDividerLocation(0, 170);

        getDockableContainer().setContentPane(dockable);
    }

    private void initMenuBar(){

        JBasicMenuBar menuBar = getMenuBar();
        System.out.println("menuBar.getSize() = " + menuBar.getSize());

        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        JBasicMenuItem loginMenuItem = new JBasicMenuItem();
        JBasicMenuItem logoutMenuItem = new JBasicMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu generalMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem dashboardMenu = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator2 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem reportsMenu = new javax.swing.JMenuItem();
        javax.swing.JMenu materialsMenu = new javax.swing.JMenu();
        //javax.swing.JMenuItem categoryMenuItem = new javax.swing.JMenuItem();
        JBasicMenuItem categoryMenuItem = new JBasicMenuItem(MaterialsController.getCategoryAction());
        javax.swing.JPopupMenu.Separator jSeparator3 = new javax.swing.JPopupMenu.Separator();
        JBasicMenuItem productMenuItem = new JBasicMenuItem(MaterialsController.getProductAction());
        javax.swing.JPopupMenu.Separator jSeparator4 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem inventoryMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator5 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem transferOrderMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator6 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem consignmentMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu salesMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem salesOrderMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator7 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem pickingMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator8 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem shippingMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator9 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem customerMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator10 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem rmaMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator11 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem pricingMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator12 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem discountMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu purchasingMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem purchasingOrderMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator13 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem receivingMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator14 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem vendorMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu manufacturingMenuItem = new javax.swing.JMenu();
        javax.swing.JMenuItem billOfMaterialsMenuItem = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator15 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem manufactureOrderMenuItem = new javax.swing.JMenuItem();


        fileMenu.setText("File");

        loginMenuItem.setText("Login");
        loginMenuItem.setEnabled(false);
        fileMenu.add(loginMenuItem);

        logoutMenuItem.setText("Logout");
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //logoutMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(logoutMenuItem);
        fileMenu.add(jSeparator1);

        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        generalMenu.setText("General");

        dashboardMenu.setText("Dashboard");
        generalMenu.add(dashboardMenu);

        jMenuItem1.setText("Warehouse");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jMenuItem1ActionPerformed(evt);
            }
        });
        generalMenu.add(jMenuItem1);
        generalMenu.add(jSeparator2);

        reportsMenu.setText("Reports");
        generalMenu.add(reportsMenu);

        menuBar.add(generalMenu);

        materialsMenu.setText("Materials");

        categoryMenuItem.setText("Category");
        categoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //categoryMenuItemActionPerformed(evt);
            }
        });
        materialsMenu.add(categoryMenuItem);
        materialsMenu.add(jSeparator3);

        productMenuItem.setText("Product");
        materialsMenu.add(productMenuItem);
        materialsMenu.add(jSeparator4);

        inventoryMenuItem.setText("Inventory");
        inventoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //inventoryMenuItemActionPerformed(evt);
            }
        });
        materialsMenu.add(inventoryMenuItem);
        materialsMenu.add(jSeparator5);

        transferOrderMenuItem.setText("Transfer Order");
        materialsMenu.add(transferOrderMenuItem);
        materialsMenu.add(jSeparator6);

        consignmentMenuItem.setText("Consignment");
        materialsMenu.add(consignmentMenuItem);

        menuBar.add(materialsMenu);

        salesMenu.setText("Sales");

        salesOrderMenuItem.setText("Sales Order");
        salesOrderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //salesOrderMenuItemActionPerformed(evt);
            }
        });
        salesMenu.add(salesOrderMenuItem);
        salesMenu.add(jSeparator7);

        pickingMenuItem.setText("Picking");
        salesMenu.add(pickingMenuItem);
        salesMenu.add(jSeparator8);

        shippingMenuItem.setText("Shipping");
        salesMenu.add(shippingMenuItem);
        salesMenu.add(jSeparator9);

        customerMenuItem.setText("Customer");
        salesMenu.add(customerMenuItem);
        salesMenu.add(jSeparator10);

        rmaMenuItem.setText("RMA");
        salesMenu.add(rmaMenuItem);
        salesMenu.add(jSeparator11);

        pricingMenuItem.setText("Pricing");
        salesMenu.add(pricingMenuItem);
        salesMenu.add(jSeparator12);

        discountMenuItem.setText("Discount");
        salesMenu.add(discountMenuItem);

        menuBar.add(salesMenu);

        purchasingMenu.setText("Purchasing");

        purchasingOrderMenuItem.setText("Purchasing Order");
        purchasingOrderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //purchasingOrderMenuItemActionPerformed(evt);
            }
        });
        purchasingMenu.add(purchasingOrderMenuItem);
        purchasingMenu.add(jSeparator13);

        receivingMenuItem.setText("Receiving");
        purchasingMenu.add(receivingMenuItem);
        purchasingMenu.add(jSeparator14);

        vendorMenuItem.setText("LocalVendor");
        purchasingMenu.add(vendorMenuItem);

        menuBar.add(purchasingMenu);

        manufacturingMenuItem.setText("Manufacturing");

        billOfMaterialsMenuItem.setText("Bill of Materials");
        manufacturingMenuItem.add(billOfMaterialsMenuItem);
        manufacturingMenuItem.add(jSeparator15);

        manufactureOrderMenuItem.setText("Manufacture Order");
        manufacturingMenuItem.add(manufactureOrderMenuItem);

        menuBar.add(manufacturingMenuItem);

        //setJMenuBar(jMenuBar1);



//        JBasicMenuBar menuBar = getMenuBar();
//
//        JBasicMenu menu = DockableManager.getToggleMenu(this);
//        ((JMenuItem) menu.getMenuComponent(1)).doClick();
//        menuBar.add(menu);
//
//        JBasicMenu helpMenu = new JBasicMenu("About", "About");
//        helpMenu.setMnemonic('H');
//        menuBar.add(helpMenu);
//
//        JBasicMenuItem aboutMenuItem = new JBasicMenuItem(MaterialsController.getAboutAction());
//        KeyStrokeManager.registerButton(aboutMenuItem, KeyEvent.VK_F1, 'U');
//        helpMenu.add(aboutMenuItem);
    }

    private void initToolBar(){
        JBasicToolBar toolBar = getToolBar();
        JideButton categoryToolBarButton = new JideButton();

        categoryToolBarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wings4/resource/category.png"))); // NOI18N
        categoryToolBarButton.setFocusable(false);
        categoryToolBarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        categoryToolBarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        categoryToolBarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //categoryToolBarButtonActionPerformed(evt);
            }
        });
        toolBar.add(categoryToolBarButton);

        JideButton productToolbarButton = new JideButton();
        productToolbarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wings4/resource/product.png"))); // NOI18N
        productToolbarButton.setFocusable(false);
        productToolbarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productToolbarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        productToolbarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //productToolbarButtonActionPerformed(evt);
            }
        });
        toolBar.add(productToolbarButton);

        JideButton purchaseToolbarButton = new JideButton();
        purchaseToolbarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wings4/resource/purchase.png"))); // NOI18N
        purchaseToolbarButton.setFocusable(false);
        purchaseToolbarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        purchaseToolbarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(purchaseToolbarButton);
    }

    private void initStatusBar(){
        JFrameWorkStatusBar statusBar = getStatusBar();
    }

    protected void paintMenuBarBackground(Component component, Graphics g){
        //ITextureStyle textureStyle = new JBlueTextureStyle();

        //JBackgroundPainter.paintBackground(component, g, textureStyle);
    }

    protected int getMenuBarBackgroundHeight(){
        ITextureStyle textureStyle = new JBlueTextureStyle();

        return textureStyle.getHeight();
    }

}
