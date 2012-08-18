package com.wings4.client;

import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.splash.JCaptionSplashDialog;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DemoCaptionSplashDialog extends JCaptionSplashDialog {

    public DemoCaptionSplashDialog()
    {
        super(IconFactory.getSwingIcon("splash.png"), new int[] {15, 200}, new int[] {220, 90}, new String[]
                {
                        "Nepxion Awt & Swing ¿ªÔ´¿âÕûºÏ½«½ü",
                        "Ò»°ÙÖÖÑùÊ½ÐÂÓ±£¬·ç¸ñ¶ÀÌØ£¬ÒÔ¼°¹¦ÄÜ",
                        "·á¸»µÄ½çÃæ¿Ø¼þ£¬Í¬Ê±Ìá¹©Ò»Ì×ÇáÁ¿¼¶",
                        "µÄ½çÃæ¿ò¼Ü½â¾ö·½°¸£¬²¢ÊÊÅäÓÚ²»Í¬µÄ",
                        "Íâ¹Û·ç¸ñ£¬ÊµÏÖ¿É¶¨ÖÆ»¯£¬¿ÉÀ©Õ¹»¯¡£",
                        "Æä¾ß±¸ÈçÏÂ¼¸¸öÌØµã£º",
                        "1. ´¿Java¼¼Êõ£¬ÊµÏÖ¿çÆ½Ì¨",
                        "2. ×é¼þÔ­×Ó»¯ºÍÁ¼ºÃµÄÁ£¶ÈÊµÏÖ»¯",
                        "3. Ö§³ÖC/S£¬B/SÁ½ÖÖ·½Ê½µÄ²¿Êð",
                        "4. Ö§³ÖÈýÖÖ¿ò¼Ü×é¼þµÄÊµÏÖ",
                        "5. Ç¿´óµÄ·ç¸ñÔ¤ÖÆºÍ¶¨ÖÆ¹¦ÄÜ"
                }
        );
    }
}
