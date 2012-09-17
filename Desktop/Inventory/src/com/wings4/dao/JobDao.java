package com.wings4.dao;

import com.wings4.model.SalesOrder;
import com.wings4.util.POSTResourceFeed;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 9/17/12
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class JobDao {

    public static boolean saveSalesOrder(SalesOrder salesOrder){
        try {
            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("categoryName", salesOrder.getCategoryName());
//            System.out.println("category.getParentCategory() = " + category.getParentCategory());
//            jsonObject.put("parentCategory", category.getParentCategory());
            POSTResourceFeed.post("salesOrder", jsonObject);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
