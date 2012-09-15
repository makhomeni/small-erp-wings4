package com.wings4.util;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 9/16/12
 * Time: 1:31 AM
 * To change this template use File | Settings | File Templates.
 */
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JPopupMenu;

import com.jidesoft.plaf.JideTabbedPaneUI;
import com.jidesoft.swing.JideTabbedPane;
import com.nepxion.swing.tabbedpane.ITabbedPane;
import com.nepxion.swing.tabbedpane.TabbedPaneManager;


public class JEclipseTabbedPane
        extends JideTabbedPane implements ITabbedPane, MouseListener
{
    private JPopupMenu popupMenu;
    //private PopupMenuAdapter popupMenuAdapter;

    public JEclipseTabbedPane()
    {
        super();

        initComponents();
    }

    public JEclipseTabbedPane(int tabPlacement)
    {
        super(tabPlacement);

        initComponents();
    }

    public JEclipseTabbedPane(int tabPlacement, int tabLayoutPolicy)
    {
        super(tabPlacement, tabLayoutPolicy);

        initComponents();
    }

    private void initComponents()
    {
        TabbedPaneManager.setPreferenceStyle(this);

        setUseDefaultShowIconsOnTab(false);

        setShowCloseButton(true);
        setShowCloseButtonOnTab(true);
        setShowCloseButtonOnSelectedTab(true);

        setRightClickSelect(true);

        setContentBorderInsets(new Insets(3, 2, 2, 2));

        addMouseListener(this);
    }

    public void addTab(String title, Component component)
    {
        addTab(title, component, null);
    }

    public void addTab(String title, Component component, String toolTipText)
    {
        addTab(title, null, component, toolTipText);
    }

    public void addTab(String title, Icon icon, Component component)
    {
        addTab(title, icon, component, null);
    }

    public void addTab(String title, Component component, boolean isClosable)
    {
        addTab(title, null, component, isClosable);
    }

    public void addTab(String title, Component component, String toolTipText, boolean isClosable)
    {
        addTab(title, null, component, toolTipText, isClosable);
    }

    public void addTab(String title, Icon icon, Component component, String toolTipText)
    {
        addTab(title, icon, component, toolTipText, false);
    }

    public void addTab(String title, Icon icon, Component component, boolean isClosable)
    {
        addTab(title, icon, component, null, isClosable);
    }

    public void addTab(String title, Icon icon, Component component, String toolTipText, boolean isClosable)
    {
        super.addTab(title, icon, component, toolTipText);

        setTabClosableAt(indexOfComponent(component), isClosable);
    }

    @Override
    public boolean isSnapToolTip() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSnapToolTip(boolean b) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getTabIndex(MouseEvent e)
    {
        JideTabbedPaneUI tabbedPaneUI = (JideTabbedPaneUI) getUI();
        int tabIndex = tabbedPaneUI.tabForCoordinate(this, e.getX(), e.getY());

        return tabIndex;
    }

    public String getSelectedTitle()
    {
        int index = getSelectedIndex();
        if (index == -1)
        {
            return null;
        }

        return getTitleAt(index);
    }

    public Icon getIcon(int index)
    {
        return getIconAt(index);
    }

    public Component getTabAt(String title)
    {
        for (int i = 0; i < getTabCount(); i++)
        {
            if (getTitleAt(i).equals(title))
            {
                return getComponentAt(i);
            }
        }

        return null;
    }

    public List getClosableTabs()
    {
        List components = new ArrayList();
        for (int i = 0; i < getTabCount(); i++)
        {
            if (isTabClosableAt(i))
            {
                Component component = getComponentAt(i);
                components.add(component);
            }
        }

        return components;
    }

    public void removeTabAt(String title)
    {
        for (int i = 0; i < getTabCount(); i++)
        {
            if (getTitleAt(i).equals(title))
            {
                removeTabAt(i);
                break;
            }
        }
    }

    public void removeTabs(List components)
    {
        for (int i = 0; i < components.size(); i++)
        {
            Component closedComponent = (Component) components.get(i);
            removeTabAt(indexOfComponent(closedComponent));
        }
    }

    public void removeReverseTabsAt(int index)
    {
        List components = new ArrayList();
        for (int i = 0; i < getTabCount(); i++)
        {
            if (i != index && isTabClosableAt(i))
            {
                Component component = getComponentAt(i);
                components.add(component);
            }
        }
        removeTabs(components);
    }

    public void removeAllTabs()
    {
        List components = new ArrayList();
        for (int i = 0; i < getTabCount(); i++)
        {
            if (isTabClosableAt(i))
            {
                Component component = getComponentAt(i);
                components.add(component);
            }
        }
        removeTabs(components);
    }

    public JPopupMenu getPopupMenu()
    {
        return popupMenu;
    }

    public void setPopupMenu(JPopupMenu popupMenu, boolean isClosable)
    {
        this.popupMenu = popupMenu;

        if (isClosable)
        {
            //popupMenuAdapter = new PopupMenuAdapter(this, popupMenu);
        }
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        requestFocus();
    }

    public void mouseReleased(MouseEvent e)
    {
        if (popupMenu != null && getTabIndex(e) != -1)
        {
            if (!e.isPopupTrigger())
            {
                return;
            }

            int selectedIndex = getSelectedIndex();
            if (selectedIndex == -1)
            {
                return;
            }

//            if (popupMenuAdapter != null)
//            {
//                popupMenuAdapter.adaptClosableItem(selectedIndex);
//            }

            popupMenu.show(e.getComponent(), e.getX(), e.getY());

            /*if (indexAtLocation(e.getX(), e.getY()) != -1)
            {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }*/
        }
    }
}
