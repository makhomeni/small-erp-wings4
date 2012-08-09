/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author ronnie
 */
public class RESTFeed {
    
    private String restEndPoint;
    private JSONObject jsonObject;
    private String contentType;
    private String acceptType;
    private String requestMethod;
    private String resource;

    /**
     *
     * @param contentType
     * @param acceptType
     * @param requestMethod
     * @param restEndPoint
     * @param resource
     */
    public RESTFeed(String contentType, String acceptType, String requestMethod, String restEndPoint, String resource, JSONObject jsonObject) {
        System.out.println(contentType + " requestMethod = "+requestMethod+" restEndPoint = "+restEndPoint+" resource = "+resource );
        this.contentType = contentType;
        this.acceptType = acceptType;
        this.requestMethod = requestMethod;
        this.restEndPoint = restEndPoint;
        this.resource = resource;
        this.jsonObject = jsonObject;
    }
    
    public String restInitialization() throws MalformedURLException, IOException {
        URL url = new URL(restEndPoint.concat(resource));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Accept", acceptType);
        
        if(! requestMethod.equals("GET")){
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(getJsonObject().toString().getBytes());
            outputStream.flush();
            outputStream.close();
        }
        
        
        System.out.println("connection.getInputStream() = " + jsonObject + "    " + restEndPoint);

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));
        String output = br.readLine(); 
//        while ((output = br.readLine()) != null) {
//            System.out.println(output);
//        }

        connection.disconnect();
        
        return output;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getRestEndPoint() {
        return restEndPoint;
    }

    public void setRestEndPoint(String restEndPoint) {
        this.restEndPoint = restEndPoint;
    }

    public String getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(String acceptType) {
        this.acceptType = acceptType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
    
}
