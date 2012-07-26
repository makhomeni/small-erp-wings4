package com.jabait.coresecurity

import com.jabait.security.User

import com.jabait.security.Feature
import com.jabait.coresecurity.util.EncryptionUtils
import com.jabait.security.Authority


/**
 * User: hossaindoula
 * Date: 2/25/12
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
class AuthenticationToken {

    public Set<Authority> credential = null; //role
    public User principal; //user
    public Map<String,Collection<Set<Feature>>> grantedAuthority = null; //set of features

    private String username;
    private String password;

    public AuthenticationToken(username,password){
        authenticationToken(username,password);
    }
    
    def authenticationToken(userCode,password){
        principal = User.findByUserCodeAndPassword(userCode,new EncryptionUtils("jabait").encrypt(password));
        if(principal != null){
            credential = principal.authorities;
            grantedAuthority = new HashMap<String,Collection<Set<Feature>>>();
            println "the credential features " + credential.features.size();
            grantedAuthority.put("features", credential.features);
        }
    }
}
