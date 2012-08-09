/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.util;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Administrator
 */
public class ItemRenderer extends BasicComboBoxRenderer {
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);

            if (value != null) {
                Item item = (Item) value;
                setText(item.getName().toUpperCase());
            }

            if (index == -1) {
                Item item = (Item) value;
//                setText( "" + item.getName() );
            }


            return this;
        }
    }
