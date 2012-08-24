package com.wings4.toggle;

import com.wings4.util.IconFactory;
import com.nepxion.swing.toggle.JToggleContentPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppToggleContentPanel extends JToggleContentPanel {

    private static AppToggleContentPanel toggleContentPanel;

    public static AppToggleContentPanel getInstance(){
        if (toggleContentPanel == null){
            toggleContentPanel = new AppToggleContentPanel();
        }

        return toggleContentPanel;
    }

    private AppToggleContentPanel(){
        super();

        JLabel logoLabel = new JLabel(IconFactory.getSwingIcon("s_logo.png"));
        getHeader().add(logoLabel, BorderLayout.EAST);
    }
}
