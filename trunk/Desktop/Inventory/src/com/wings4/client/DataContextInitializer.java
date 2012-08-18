package com.wings4.client;

import com.nepxion.swing.context.DataContextConstants;
import com.nepxion.swing.context.DataContextRegister;
import com.nepxion.util.context.AbstractContextInitializer;
import com.nepxion.util.context.IContextRegister;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataContextInitializer extends AbstractContextInitializer implements DataContextConstants{
    /**
     * Gets the context register.
     * @return the instance of IContextRegister
     */
    public IContextRegister getContextRegister(){
        return new DataContextRegister();
    }

    /**
     * Gets the default context file path.
     * @return the default context file path string
     */
    public String getDefaultContextFilePath(){
        return CONTEXT_FILE_PATH;
    }
}
