package com.wings4.toggle;

import com.nepxion.swing.button.ButtonManager;
import com.nepxion.swing.button.JBasicButton;
import com.nepxion.swing.button.JBasicToggleButton;
import com.nepxion.swing.container.JContainer;
import com.nepxion.swing.dimension.DimensionManager;
import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.memorybar.JMemoryProgressBar;
import com.nepxion.swing.scrollpane.JBasicScrollPane;
import com.nepxion.swing.textarea.JBasicTextArea;
import com.nepxion.util.io.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppToggleTemplate extends JContainer {

    private JComponent viewComponent;
    private JBasicScrollPane codeScrollPane;
    private JBasicTextArea codeTextArea;
    private ToggleButtonPanel toggleButtonPanel;

    private String codeContent;

    public AppToggleTemplate(JComponent viewComponent){
        this.viewComponent = viewComponent;

        codeTextArea = new JBasicTextArea();
        codeTextArea.setTabSize(2);

        codeScrollPane = new JBasicScrollPane();
        codeScrollPane.getViewport().add(codeTextArea);

        toggleButtonPanel = new ToggleButtonPanel();

        setLayout(new BorderLayout());
        add(viewComponent, BorderLayout.CENTER);
        //add(toggleButtonPanel, BorderLayout.SOUTH);
    }

    public class ToggleButtonPanel extends JPanel{
        public ToggleButtonPanel(){
            final JMemoryProgressBar memoryProgressBar = new JMemoryProgressBar();
            DimensionManager.setDimension(memoryProgressBar, new Dimension(120, 25));
            memoryProgressBar.start();

            JBasicButton memoryGCButton = new JBasicButton(IconFactory.getSwingIcon("gc.png"), "Garbage Collector");
            memoryGCButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    memoryProgressBar.gc();
                }
            }
            );

            JBasicToggleButton viewToggleButton = new JBasicToggleButton("View",
                    IconFactory.getSwingIcon("component/view.png"), "View", true);
            viewToggleButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    addComponent(viewComponent, codeScrollPane, BorderLayout.CENTER);
                }
            });
            JBasicToggleButton codeToggleButton = new JBasicToggleButton("Code",
                    IconFactory.getSwingIcon("component/java.png"), "Code");
            codeToggleButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    addComponent(codeScrollPane, viewComponent, BorderLayout.CENTER);

                    if (codeContent == null){
                        String projectFolderName = "Inventory";
                        String codePath = FileUtil.getResourcePath(viewComponent.getClass(), projectFolderName);

                        try{
                            codeContent = FileUtil.readString(codePath, "GBK", false);
                        } catch (FileNotFoundException ex){
                            ex.printStackTrace();
                        } catch (UnsupportedEncodingException ex){
                            ex.printStackTrace();
                        } catch (IOException ex){
                            ex.printStackTrace();
                        }
                        codeTextArea.setText(codeContent);
                        codeTextArea.moveCaretPosition(0);
                    }
                }
            });

            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(BorderFactory.createEmptyBorder(2, 0, 3, 3)); // 2, 0, 1, 1
            add(memoryProgressBar);
            add(memoryGCButton);
            add(Box.createHorizontalGlue());
            add(viewToggleButton);
            add(Box.createHorizontalStrut(3));
            add(codeToggleButton);

            ButtonManager.updateUI(this, new Dimension(75, 25), new ButtonGroup());

            memoryGCButton.setDimension(new Dimension(25, 25));
        }
    }
}
