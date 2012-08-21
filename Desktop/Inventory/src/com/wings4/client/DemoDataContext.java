package com.wings4.client;

import com.nepxion.swing.activex.ActiveXContext;
import com.nepxion.util.locale.LocaleContext;
import com.nepxion.util.log.LoggerContext;
import com.nepxion.util.scheduler.quartz.QuartzContext;
import com.nepxion.util.searcher.ip.local.IPContext;
//import com.nepxion.util.searcher.zone.local.ZoneContext;

import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/20/12
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoDataContext {

    public DemoDataContext()
    {
    }

    public static void initialize()
    {
        //EncodeContext.registerIOCharset("GBK");
        //EncodeContext.registerHttpCharset("UTF-8");
        LocaleContext.registerLocale(LocaleContext.LOCALE_EN_US);
        ActiveXContext.registerStrategy(0);
        try
        {
            LoggerContext.register();
            QuartzContext.register();
            IPContext.register();
            //ZoneContext.register();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void initialize(URL codeBase)
    {
        //EncodeContext.registerIOCharset("GBK");
        //EncodeContext.registerHttpCharset("UTF-8");
        ActiveXContext.registerStrategy(1);
        try
        {
            LoggerContext.register(codeBase);
            QuartzContext.register(codeBase);
            IPContext.register(codeBase);
            //ZoneContext.register(codeBase);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        registerURL(codeBase);
    }

    public static void registerURL(URL codeBase)
    {
        url = codeBase;
    }

    public static URL getURL()
    {
        return url;
    }

    private static URL url;
}
