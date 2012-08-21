package com.wings4.client;

import com.nepxion.swing.chart.ChartContext;
import com.nepxion.swing.icon.IconContext;
import com.nepxion.swing.popupmenu.PopupMenuContext;
import com.nepxion.swing.style.framework.JPlasticStyle;
import com.nepxion.swing.style.framework.StyleContext;
import com.nepxion.swing.topic.TopicContext;
import com.wings4.util.LookAndFeelManager;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/20/12
 * Time: 6:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoUIContext {

    public DemoUIContext()
    {
    }

    public static void initialize()
    {
        StyleContext.registerStyle(JPlasticStyle.ID);
        StyleContext.registerFrameDecorated(true);
        StyleContext.registerDialogDecorated(true);
        ChartContext.registerChartPDFAuthor("Mohammed Hossain Doula");
        ChartContext.registerChartPDFSubject("Inventory");
        PopupMenuContext.registerTitle("Wings4 Inventory");
        IconContext.registerIconFolder("com/nepxion/demo/component/icon/image/");
        TopicContext.registerTopicComponent(new DemoCaptionSplashDialog());
        LookAndFeelManager.setAlloyLookAndFeel();
    }
}
