package com.jabait.coresecurity

/**
 * Created by IntelliJ IDEA.
 * User: hossaindoula
 * Date: 2/25/12
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 */
class AuthenticationProcessingFilter {

    public AuthenticationToken attemptAuthentication(params){
        String username = obtainUsername(params);
        String password = obtainPassword(params);

        if(username == null){
            username = "";
        }

        if(password == null){
            password = "";
        }

        AuthenticationToken userAuthTokenObject = new AuthenticationToken(username,password);

        return userAuthTokenObject;
    }


    public String obtainUsername(params){
        return params.userCode;
    }

    public String obtainPassword(params){
        return params.password;
    }


}
