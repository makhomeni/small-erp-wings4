package com.wings4.util;

import javax.swing.*;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/11/12
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */

public class BaseGridModel extends JPanel {
    public static final int ITEMS_PER_PAGE = 20;
    private static final Color evenColor = new Color(250, 150, 100);
    private static final LinkViewRadioButtonUI ui = new LinkViewRadioButtonUI();
    private static int LR_PAGE_SIZE = 10;
    private final Box box = Box.createHorizontalBox();
    public String from = "dablu";
    private String[] columnNames;
    private final DefaultTableModel model = new DefaultTableModel(null, columnNames) {
        @Override
        public Class<?> getColumnClass(int column) {
            return (column == 0) ? Integer.class : Object.class;
        }
    };
    public final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
    public final JTable table = new JTable(model) {
        @Override
        public Component prepareRenderer(TableCellRenderer tcr, int row, int column) {
            Component c = super.prepareRenderer(tcr, row, column);
            c.setForeground(getForeground());
            c.setBackground((model.getValueAt(row, 4).equals("absent")) ? Color.RED : (model.getValueAt(row, 4).equals("late")) ? Color.YELLOW : Color.WHITE);
            return c;
        }
    };




    public BaseGridModel() {
        super(new BorderLayout());
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension());
        table.setShowGrid(false);
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        table.setRowSorter(sorter);

        initLinkBox(ITEMS_PER_PAGE,1);

        add(box, BorderLayout.NORTH);
        add(new JScrollPane(table));
        setPreferredSize(new Dimension(500, 375));
    }

    private void initLinkBox(final int itemsPerPage, final int currentPageIndex) {
        //assert currentPageIndex>0;
        sorter.setRowFilter(makeRowFilter(itemsPerPage, currentPageIndex-1));
        System.out.println("currentPageIndex is :"+currentPageIndex);
        ArrayList<JRadioButton> paginationButtons = new ArrayList<JRadioButton>();

        int startPageIndex = currentPageIndex - LR_PAGE_SIZE;
        if (startPageIndex <= 0) startPageIndex = 1;

        int rowCount = model.getRowCount();
        System.out.println("rowCount is:"+rowCount);
        int maxPageIndex = (rowCount / itemsPerPage) + (rowCount % itemsPerPage == 0 ? 0 : 1);
        int endPageIndex = currentPageIndex + LR_PAGE_SIZE - 1;
        System.out.println("maxPageIndex is:"+maxPageIndex);
        if (endPageIndex > maxPageIndex) endPageIndex = maxPageIndex;

        if (currentPageIndex > 1) {
            paginationButtons.add(makePNRadioButton(itemsPerPage, currentPageIndex - 1, "Prev"));
        }
        if (startPageIndex < endPageIndex) {
            for (int i = startPageIndex; i <= endPageIndex; i++) {
                paginationButtons.add(makeRadioButton(itemsPerPage, currentPageIndex, i - 1));
            }
        }
        if (currentPageIndex < maxPageIndex) {
            paginationButtons.add(makePNRadioButton(itemsPerPage, currentPageIndex + 1, "Next"));
        }

        box.removeAll();
        ButtonGroup bg = new ButtonGroup();
        box.add(Box.createHorizontalGlue());
        for (JRadioButton r : paginationButtons) {
            box.add(r);
            bg.add(r);
        }
        box.add(Box.createHorizontalGlue());
        box.revalidate();
        box.repaint();
        paginationButtons.clear();
    }

    private JRadioButton makeRadioButton(final int itemsPerPage, final int current, final int target) {
        JRadioButton radio = new JRadioButton("" + (target + 1)) {
            @Override
            protected void fireStateChanged() {
                ButtonModel model = getModel();
                if (!model.isEnabled()) {
                    setForeground(Color.RED);
                } else if (model.isPressed() && model.isArmed()) {
                    setForeground(Color.RED);
                } else if (model.isSelected()) {
                    setForeground(Color.RED);
                }
                super.fireStateChanged();
            }
        };
        radio.setForeground(Color.BLUE);
        radio.setUI(ui);
        if (target + 1 == current) {
            radio.setSelected(true);
        }
        radio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initLinkBox(itemsPerPage, target + 1);
            }
        });
        return radio;
    }

    private JRadioButton makePNRadioButton(final int itemsPerPage, final int target, String title) {
        JRadioButton radio = new JRadioButton(title);
        radio.setForeground(Color.BLUE);
        radio.setUI(ui);
        radio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initLinkBox(itemsPerPage, target);
            }
        });
        return radio;
    }

    private RowFilter<TableModel, Integer> makeRowFilter(final int itemsPerPage, final int target) {
        return new RowFilter<TableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                int ei = entry.getIdentifier();
                return (target * itemsPerPage <= ei && ei < target * itemsPerPage + itemsPerPage);
            }
        };
    }

    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
        public boolean include(Entry entry) {
            return entry.getValueCount() > 3;
        }
    };
}

class LinkViewRadioButtonUI extends BasicRadioButtonUI {
    @Override
    public Icon getDefaultIcon() {
        return null;
    }

    private static Dimension size = new Dimension();
    private static Rectangle viewRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();

    @Override
    public synchronized void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        Font f = c.getFont();
        g.setFont(f);
        FontMetrics fm = c.getFontMetrics(f);

        Insets i = c.getInsets();
        size = b.getSize(size);
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = size.width - (i.right + viewRect.x);
        viewRect.height = size.height - (i.bottom + viewRect.y);
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;
        textRect.x = textRect.y = textRect.width = textRect.height = 0;

        String text = SwingUtilities.layoutCompoundLabel(
                c, fm, b.getText(), null, //altIcon != null ? altIcon : getDefaultIcon(),
                b.getVerticalAlignment(), b.getHorizontalAlignment(),
                b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
                viewRect, iconRect, textRect,
                0); //b.getText() == null ? 0 : b.getIconTextGap());

        if (c.isOpaque()) {
            g.setColor(b.getBackground());
            g.fillRect(0, 0, size.width, size.height);
        }
        if (text == null) return;
        g.setColor(b.getForeground());
        if (!model.isSelected() && !model.isPressed() && !model.isArmed()
                && b.isRolloverEnabled() && model.isRollover()) {
            g.drawLine(viewRect.x, viewRect.y + viewRect.height,
                    viewRect.x + viewRect.width, viewRect.y + viewRect.height);
        }
        View v = (View) c.getClientProperty(BasicHTML.propertyKey);
        if (v != null) {
            v.paint(g, textRect);
        } else {
            paintText(g, b, textRect, text);
        }
    }
}