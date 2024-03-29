package com.wings4.util;

import java.lang.reflect.Method;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 *
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 8/11/12
 * Time: 2:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class ItemCreationCombo {

    /**
     *
     * @param className = your dao class for accessing webservice i.e =  CommonDao
     * @param methodName = method name for accessing webservice i.e =  findAllDeliveryTerms
     * @param displayName = which property you wanted to show in combo i.e. = name
     * @return Vector<Item> for combo box
     */
    public static Vector<Item> comboInitialization(String className, String methodName, String displayName){
        try{
            Class<?> c = Class.forName("com.wings4.dao.".concat(className));
            Method method = c.getMethod(methodName);
            Object ret = method.invoke(c);
            System.out.println("rate."+ ret.toString());
            JSONArray jsonArray = new JSONArray(ret.toString());
            return getVectorForCombo(jsonArray, displayName);
        }catch(Exception ex){
            System.out.println("ex is here = " + ex);
            return null;
        }
    }

    public static Vector<Item> getVectorForCombo(JSONArray jsonArray, String valueToShowInCombo){
        List<Map<String,Object>> instances = new ArrayList<Map<String,Object>>();
        Map<String,Object> instance;
        try{
            for(int i = 0 ; i < jsonArray.length(); i++){
                instance = new HashMap<String,Object>();
                JSONObject deliveryTermObject = (JSONObject)jsonArray.get(i);
                instance.put("id", deliveryTermObject.get("id"));
                System.out.println("deliveryTermObject.get(valueToShowInCombo) = " 
                        + deliveryTermObject.get(valueToShowInCombo));
                instance.put(valueToShowInCombo, deliveryTermObject.get(valueToShowInCombo));
                instances.add(instance);
            }

            Vector<Item> instanceVector = new Vector<Item>();
            for(Map<String,Object> instanceMap : instances) {
                instanceVector.addElement(new Item(Integer.parseInt(instanceMap.get("id").toString()),
                        instanceMap.get(valueToShowInCombo).toString()));
            }
            
            return instanceVector;
        }catch(JSONException jE){
            jE.printStackTrace();
            return null;
        }

    }

}
