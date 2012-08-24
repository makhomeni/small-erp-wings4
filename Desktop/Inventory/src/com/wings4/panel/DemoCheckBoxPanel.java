package com.wings4.panel;

import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.checkbox.JBasicCheckBox;
import com.nepxion.swing.checkbox.JLiteCheckBox;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.nepxion.swing.radiobutton.JBasicRadioButton;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoCheckBoxPanel extends JPanel {
    public DemoCheckBoxPanel()
    {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new StyleCheckBoxPanel());
    }

    public class StyleCheckBoxPanel
            extends JPanel
    {
        public StyleCheckBoxPanel()
        {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderManager.createComplexTitledBorder("Flat ButtonUI"));

            JLiteCheckBox styleCheckBox = new JLiteCheckBox("Style CheckBox", true);
            add(styleCheckBox);

            JBasicCheckBox c = new JBasicCheckBox("CheckBox");
            add(c);

            JBasicRadioButton r = new JBasicRadioButton("RadioButton");
            add(r);
        }
    }
}
