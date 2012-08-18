package com.wings4.client;

import com.nepxion.swing.context.UIContextConstants;
import com.nepxion.swing.context.UIContextRegister;
import com.nepxion.util.context.AbstractContextInitializer;
import com.nepxion.util.context.IContextRegister;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class UIContextInitializer extends AbstractContextInitializer implements UIContextConstants{
    /**
     * Gets the context register.
     * @return the instance of IContextRegister
     */
    public IContextRegister getContextRegister()
    {
        return new UIContextRegister();
    }

    /**
     * Gets the default context file path.
     * @return the default context file path string
     */
    public String getDefaultContextFilePath()
    {
        return CONTEXT_FILE_PATH;
    }
}
