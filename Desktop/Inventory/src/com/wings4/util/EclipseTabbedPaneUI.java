package com.wings4.util;

import com.jidesoft.plaf.eclipse.Eclipse3xJideTabbedPaneUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/17/12
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class EclipseTabbedPaneUI extends Eclipse3xJideTabbedPaneUI {

    private final Color SELECTED_TAB_COLOR = new Color(10, 36, 106);

    /**
     * FIXME: selected border has rounded top corners
     *
     * @see javax.swing.plaf.basic.BasicTabbedPaneUI#paintTabBorder(java.awt.Graphics, int, int, int, int, int, int, boolean)
     */
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                                  int x, int y, int w, int h, boolean isSelected) {

        g.setColor(Color.GRAY);

        if (tabPlacement == BOTTOM) {
            g.drawLine(x, y + h, x + w, y + h);
        }

        // right
        g.drawLine(x + w - 1, y, x + w - 1, y + h);

        if (tabPlacement == TOP) {
            // And a white line to the left and top
            g.setColor(Color.WHITE);
            g.drawLine(x, y, x, y + h);

            g.drawLine(x, y, x + w - 2, y);
        }

        if (tabPlacement == BOTTOM && isSelected) {
            g.setColor(Color.WHITE);

            // Top
            g.drawLine(x + 1, y + 1, x + 1, y + h);
            // Right
            g.drawLine(x + w - 2, y, x + w - 2, y + h);
            // Left
            g.drawLine(x + 1, y + 1, x + w - 2, y + 1);
            // Bottom
            g.drawLine(x + 1, y + h - 1, x + w - 2, y + h - 1);
        }
    }

    /**
     * Give selected tab blue color with a gradient!!.
     *
     * FIXME: with Plastic L&F the unselected background is too dark
     *
     * @see javax.swing.plaf.basic.BasicTabbedPaneUI#paintTabBackground(java.awt.Graphics, int, int, int, int, int, int, boolean)
     */
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                                      int x, int y, int w, int h, boolean isSelected) {

        Color color = UIManager.getColor("control");

        if (isSelected) {
            if (tabPlacement == TOP) {
                Graphics2D g2 = (Graphics2D)g;
                Paint storedPaint = g2.getPaint();
                g2.setPaint(new GradientPaint(x, y, SELECTED_TAB_COLOR, x + w, y + h, color));
                g2.fillRect(x, y, w, h);
                g2.setPaint(storedPaint);
            }
        } else {
            g.setColor(color);
            g.fillRect(x, y, w - 1, h);
        }
    }

    /**
     * Do not paint a focus indicator.
     *
     * @see javax.swing.plaf.basic.BasicTabbedPaneUI#paintFocusIndicator(java.awt.Graphics, int, java.awt.Rectangle[], int, java.awt.Rectangle, java.awt.Rectangle, boolean)
     */
    @Override
    protected void paintFocusIndicator(Graphics arg0, int arg1, Rectangle[] arg2, int arg3, Rectangle arg4, Rectangle arg5, boolean arg6) {
        // Leave it
    }

    /**
     * We do not want the tab to "lift up" when it is selected.
     *
     * @see javax.swing.plaf.basic.BasicTabbedPaneUI#installDefaults()
     */
    @Override
    protected void installDefaults() {
        super.installDefaults();

        //tabAreaInsets = new Insets(0, 0, 0, 0);
        //selectedTabPadInsets = new Insets(0, 0, 0, 0);
        //contentBorderInsets = new Insets(1, 0, 0, 0);
    }

    /**
     * Nor do we want the label to move.
     */
    protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected) {

        return 0;
    }

    /**
     * Increase the tab height a bit
     */
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {

        return fontHeight + 10;
    }

    @Override
    protected void layoutLabel(int arg0, FontMetrics arg1, int arg2, String arg3, Icon arg4, Rectangle arg5, Rectangle arg6, Rectangle arg7, boolean arg8) {

        super.layoutLabel(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    /**
     * Selected labels have a white color.
     *
     * @see javax.swing.plaf.basic.BasicTabbedPaneUI#paintText(java.awt.Graphics, int, java.awt.Font, java.awt.FontMetrics, int, String, java.awt.Rectangle, boolean)
     */
    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font,
                             FontMetrics metrics, int tabIndex, String title, Rectangle textRect,
                             boolean isSelected) {

        if (isSelected && tabPlacement == TOP) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }

        // HACK: Force painting of Tahoma - Plastic L&F renders a big Arial
        Font tabFont = new Font("Tahoma", Font.PLAIN, 11);

        g.setFont(tabFont);
        g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
    }

    @Override
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
                                             int selectedIndex, int x, int y, int w, int h) {

        if (selectedIndex != -1 && tabPlacement == TOP) {
            g.setColor(Color.GRAY);
            g.drawLine(x, y, x + w, y);
        }
    }

    @Override
    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
                                                int selectedIndex, int x, int y, int w, int h) {

        g.setColor(Color.GRAY);
        g.drawLine(x, y + h, x + w, y + h);
    }

    @Override
    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
                                              int selectedIndex, int x, int y, int w, int h) {
        // do nothingx, y, x, y + h);
    }

    @Override
    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
                                               int selectedIndex, int x, int y, int w, int h) {
        // do nothing
    }

}
