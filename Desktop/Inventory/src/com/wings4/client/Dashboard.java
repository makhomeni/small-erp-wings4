package com.wings4.client;

import com.nepxion.swing.framework.JFrameWorkStatusBar;
import com.nepxion.swing.framework.dockable.DockableManager;
import com.nepxion.swing.framework.dockable.JDockable;
import com.nepxion.swing.framework.dockable.JDockableHierarchy;
import com.nepxion.swing.framework.dockable.JDockableView;
import com.nepxion.swing.gradient.JBackgroundPainter;
import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.keystroke.KeyStrokeManager;
import com.nepxion.swing.menu.JBasicMenu;
import com.nepxion.swing.menubar.JBasicMenuBar;
import com.nepxion.swing.menuitem.JBasicMenuItem;
import com.nepxion.swing.style.texture.ITextureStyle;
import com.nepxion.swing.style.texture.basic.JBlueTextureStyle;
import com.nepxion.swing.toolbar.JBasicToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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
        JDockableView explorerView = new JDockableView(EXPLORER_TITLE, IconFactory.getIcon("explorer.png"),
                EXPLORER_TITLE,ExplorerBar.getInstance());
        JDockableView contentPaneView = new JDockableView(CONTENT_PANE_TITLE, IconFactory.getIcon("content_pane.png"),
                CONTENT_PANE_TITLE, DemoToggleContentPanel.getInstance());

        JDockable dockable = new JDockable();
        dockable.setOrientation(JDockable.HORIZONTAL_SPLIT);
        dockable.add(explorerView, JDockable.FLEXIBLE);
        dockable.add(contentPaneView, JDockable.VARY);

        dockable.setDividerLocation(0, 170);

        getDockableContainer().setContentPane(dockable);
    }

    private void initMenuBar(){
        JBasicMenuBar menuBar = getMenuBar();

        JBasicMenu menu = DockableManager.getToggleMenu(this);
        ((JMenuItem) menu.getMenuComponent(1)).doClick();
        menuBar.add(menu);

        JBasicMenu helpMenu = new JBasicMenu("About", "About");
        helpMenu.setMnemonic('H');
        menuBar.add(helpMenu);

        JBasicMenuItem aboutMenuItem = new JBasicMenuItem(DemoController.getAboutAction());
        KeyStrokeManager.registerButton(aboutMenuItem, KeyEvent.VK_F1, 'U');
        helpMenu.add(aboutMenuItem);
    }

    private void initToolBar(){
        JBasicToolBar toolBar = getToolBar();
    }

    private void initStatusBar(){
        JFrameWorkStatusBar statusBar = getStatusBar();
    }

    protected void paintMenuBarBackground(Component component, Graphics g){
        ITextureStyle textureStyle = new JBlueTextureStyle();

        JBackgroundPainter.paintBackground(component, g, textureStyle);
    }

    protected int getMenuBarBackgroundHeight(){
        ITextureStyle textureStyle = new JBlueTextureStyle();

        return textureStyle.getHeight();
    }

}
