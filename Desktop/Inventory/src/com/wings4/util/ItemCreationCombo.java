package com.wings4.util;

import com.wings4.dao.CommonDao;
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

    public Vector<Item> deliveryTermItem(){
        try{
            CommonDao commonDao = new CommonDao();
            JSONArray jsonArray = new JSONArray(commonDao.findAllDeliveryTerm());
            return getVectorForCombo(jsonArray, "terms");
        }catch(Exception ex){
            System.out.println("ex is here = " + ex);
            return null;
        }
    }

    public Vector<Item> paymentTermItem(){
        try{
            CommonDao commonDao = new CommonDao();
            JSONArray jsonArray = new JSONArray(commonDao.findAllPaymentMethods());
            return getVectorForCombo(jsonArray, "paymentMethod");
        }catch(Exception ex){
            System.out.println("ex is here = " + ex);
            return null;
        }
    }

    public Vector<Item> creatorItem(){
        try{
            CommonDao commonDao = new CommonDao();
            JSONArray jsonArray = new JSONArray(commonDao.findAllUsers());

            return getVectorForCombo(jsonArray, "userCode");
        }catch(Exception ex){
            System.out.println("ex is here = " + ex);
            return null;
        }
    }

    public Vector<Item> getVectorForCombo(JSONArray jsonArray, String valueToShowInCombo){
        List<Map<String,Object>> instances = new ArrayList<Map<String,Object>>();
        Map<String,Object> instance;
        try{
            for(int i = 0 ; i < jsonArray.length(); i++){
                instance = new HashMap<String,Object>();
                System.out.println("jsonArray.get(i) = " + jsonArray.get(i));
                JSONObject deliveryTermObject = (JSONObject)jsonArray.get(i);
                instance.put("id", deliveryTermObject.get("id"));
                instance.put(valueToShowInCombo, deliveryTermObject.get(valueToShowInCombo));
                instances.add(instance);
            }

            Vector<Item> instanceVector = new Vector<Item>();
            for(Map<String,Object> instanceMap : instances) {
                System.out.println("instanceMap = " + instanceMap);
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
