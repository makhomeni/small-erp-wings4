package com.wings4.util;

import com.nepxion.swing.icon.IconContext;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class IconFactory {
    /**
     * The map of the icon cache pool.
     */
    private static Map iconPool = new HashMap();
    private static String SWING_ICON_FOLDER = InventoryConstants.RESOURCE_PATH;
    private static String SWING_BLANK_ICON = "";

    /**
     * Gets the icon in swing icon folder by an icon name.
     * The icon name must not be included the folder string.
     * For example : property.png, config.gif
     * @param iconName the icon name string
     * @return the instance of ImageIcon
     */
    public static ImageIcon getSwingIcon(String iconName){
        return getIcon(SWING_ICON_FOLDER + iconName);
    }

    public static Image getSwingImage(String imageName){
        return getImage(SWING_ICON_FOLDER + imageName);
    }



    /**
     * Gets the icon by an icon name in context icon folder which is registered in IconContext.
     * The icon name must not be included the folder string.
     * For example : property.png, config.gif
     * @return the instance of ImageIcon
     */
    public static ImageIcon getContextIcon(String iconName)
    {
        return getIcon(IconContext.getIconFolder() + iconName);
    }

    /**
     * Gets the blank icon.
     * @return the instance of ImageIcon
     */
    public static ImageIcon getBlankIcon()
    {
        return getIcon(SWING_BLANK_ICON);
    }

    /**
     * Gets the icon by an icon full path.
     * If the icon is not existed, the blank icon will be returned.
     * @param iconFullPath the icon full path string
     * @return the instance of ImageIcon
     */
    public static ImageIcon getIcon(String iconFullPath){
        ImageIcon imageIcon = createIcon(iconFullPath);
        return imageIcon;
    }

    public static Image getImage(String imageFullPath){
        Image image = createImage(imageFullPath);
        return image;
    }

    /**
     * Creates the icon by an icon full path.
     * @param iconFullPath  the icon full path string
     * @return the instance of ImageIcon
     */
    private static ImageIcon createIcon(String iconFullPath){
        URL imgURL = ClassLoader.getSystemResource(iconFullPath);
        if (imgURL != null) {
            return new ImageIcon(imgURL, "");
        } else {
            return null;
        }
    }

    private static Image createImage(String imageFullPath){
        Toolkit kit = Toolkit.getDefaultToolkit();
        URL imageUrl = ClassLoader.getSystemResource("com/wings4/resource/logo.png");
        Image favIcon = kit.createImage(imageUrl);

        return favIcon;
    }
}
