package com.wings4.core.toggle;

import com.nepxion.swing.framework.ribbon.IRibbonComponent;
import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.listener.DisplayAbilityListener;
import com.nepxion.swing.tabbedpane.JEclipseTabbedPane;
import com.nepxion.swing.toggle.JThreadTogglePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:21 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AppTogglePanel extends JThreadTogglePanel implements IRibbonComponent {

    private JEclipseTabbedPane toggleTabbedPane;

    public AppTogglePanel(){
        super("Initializing, please wait...");

        setToggleBannerIcon(IconFactory.getSwingIcon("banner/edit.png"));
        setInformationText("Initialize the component");
        setInformationIcon(IconFactory.getSwingIcon("banner/query_128.png"));
        setErrorText("Initialize the component failure");
        setErrorIcon(IconFactory.getSwingIcon("banner/error_128.png"));
        setThreadPanelWidth(300);

        showInformation();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        addHierarchyListener(new DisplayAbilityListener(){
            public void displayAbilityChanged(HierarchyEvent e){
                execute();

                removeHierarchyListener(this);
            }
        });
    }

    public JEclipseTabbedPane getToggleTabbedPane(){
        return toggleTabbedPane;
    }

    public Component getContentPane(){
        return toggleTabbedPane;
    }

    public boolean isLoadCache(){
        return toggleTabbedPane != null;
    }

    protected void loadForeground(Object data)
            throws Exception{
        if (toggleTabbedPane == null){
            toggleTabbedPane = new JEclipseTabbedPane();
            add(toggleTabbedPane, BorderLayout.CENTER);

            initialize();
        }
    }

    protected Object loadBackground()
            throws Exception{
        return null;
    }

    public Object getUserObject(){
        return null;
    }

    public void setUserObject(Object userObject){

    }

    public abstract void initialize();

}
