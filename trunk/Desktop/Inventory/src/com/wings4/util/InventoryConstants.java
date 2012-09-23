/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.util;

/**
 *
 * @author ronnie
 */
public interface InventoryConstants {
    
    public static final String MEDIA_JSON = "application/json";
    public static final String MEDIA_XML = "application/xml";
    public static final String MEDIA_PLAIN = "plain/text";
    
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    
    public static final String CONTEXT_PATH = "App";
    public static final String EXTRA_PATH = "/api/";
    
    public static final String serverAddressPrefix = "http://";

    public static final String resourceDirectory = "/com/wings4/resource/";

    public static final String RESOURCE_PATH = "com/wings4/resource/";
    
    public static final String LOGIN_ERROR = "Login Error";
    public static final String INVALID_URL_MESSAGE = "The URL Provided is incorrect";
    public static final String BAD_CREDENTIAL_MESSAGE = "Bad Username/Password combination provided";
    
    public static final String ADD = " Add ";
    
    public static final String STOCK = "Stock";
    public static final String STOCK_ADD = STOCK.concat(ADD);
    public static final String CATEGORY = "Category";
    public static final String CATEGORY_ADD = CATEGORY.concat(ADD);
    public static final String CUSTOMER = "Customer";
    public static final String CUSTOMER_ADD = CUSTOMER.concat(ADD);
    public static final String PRODUCT = "Product";
    public static final String PRODUCT_ADD = PRODUCT.concat(ADD);
    public static final String PURCHASE = "Purchase";
    public static final String PURCHASE_ADD = PURCHASE.concat(ADD);
    public static final String LOCAL_VENDOR = "Local Vendor";
    public static final String LOCAL_VENDOR_ADD = LOCAL_VENDOR.concat(ADD);
    public static final String EXTERNAL_VENDOR = "External Vendor";
    public static final String EXTERNAL_VENDOR_ADD = EXTERNAL_VENDOR.concat(ADD);

    public static final String CURRENT_USER_ID = "currentUserId";
    public static final String DEFAULT_CSR_GROUP_ID = "defaultcsrgroupid";
    public static final String DEFAULT_TECH_GROUP_ID = "defaulttechgroupid";
    public static final String CURRENT_USERNAME = "currentUserName";
    public static final String DATE_AND_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
    public static final String DATE_AND_TIME_FORMAT_AMPM = "MM-dd-yyyy HH:mm:ss a";
    public static final String DATE_AND_TIME_FORMAT_SPECIAL_AMPM = "MM-dd-yyyy HH:mm a";
    public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String TIME_FORMAT_WITH_AM_PM = "hh:mm a";
    public static final String TIME_FORMAT_WITHOUT_AM_PM = "hh:mm";
    public static final String TIME_FORMAT_WITH_ONLY_AM_PM = "a";
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    
    public static final String DEVELOPER_ORGANIZATION = "Wings4";
    public static final String DEVELOPER_SITE = serverAddressPrefix.concat("www.wings4bd.com");
    public static final String APP_NAME = "Stock Management";
    public static final Double APP_VERSION = 1.0;

}
