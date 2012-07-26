package com.jabait.security

import com.jabait.coresecurity.AuthenticationProcessingFilter
import com.jabait.coresecurity.AuthenticationToken
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class SecurityService {

    def grailsApplication;

    def serviceMethod() {

    }

    public AuthenticationToken doAuthentication(params){

        AuthenticationProcessingFilter authFilter = new AuthenticationProcessingFilter();
        AuthenticationToken authToken = authFilter.attemptAuthentication(params);

        return authToken;
    }

    public boolean checkAuthorization(session,module,operation){
        boolean isAuthorized = false;

        Map<String,Collection<Set<Feature>>> featureCollectionMap = session?.grantedAuthority;
        Collection<Set<Feature>> featureCollection = featureCollectionMap?.get("features");

        featureCollection?.each{
            features->
            print "collection size " + features.size()
            features.each{
                feature->

                if(feature?.module?.equalsIgnoreCase(module) && feature?.operation?.equalsIgnoreCase(operation)){
                    isAuthorized = true;
                }
            }
        }

        return isAuthorized;
    }

    public List<String> loadModules(session){
        Map<String,List<Feature>> featureListMap = session?.grantedAuthority;
        List<Feature> featureList = featureListMap.get("features");
        //Map<String,String> moduleMap = new HashMap<String,String>();
        List<Object> moduleList = new ArrayList<String,String>();
        featureList?.each{
            feature->
            moduleList.add(feature?.module);

        }

        return moduleList;

        /*grailsApplication.controllerClasses.each {  controllerArtefact ->
            def controllerClass = controllerArtefact.getClazz();
            println "$controllerArtefact, $controllerClass"
        }*/

        /**
         * code for feature servicce
         *
         */
    }

    /**
     * code for feature service
     *
     */

    boolean saveFeature(params){
        System.out.print("enter ");
        def featureInstance = new Feature(params);
        System.out.print("enter ");
        if(!featureInstance){
            return false;
        }


        if(!featureInstance.save(flush: true)){

            return false;
        }else{
            return true;
        }

    }

    boolean editFeature(id, params){
        // def encryptionUtils = new EncryptionUtils("jabait");

        def featureInstance = Feature.get(id);
        System.out.println(featureInstance);
        if(!featureInstance){
            return false;
        } else {
            featureInstance.properties = params;
            if(!featureInstance.save(flush: true)){
                return false;
            }
            else{
                return true;
            }

        }
    }
    List loadControllers(){
        List<String> controllerList = new ArrayList<String>();
        /*grailsApplication?.controllerClasses.each {
         controllerArtefact -> def controllerClass = controllerArtefact.getClazz();
            println "$controllerArtefact, $controllerClass"
             println(controllerArtefact.getName());
            //println( controllerClass.getDeclaredMethods() );
            controllerList.add(controllerArtefact.getName());
        }     */



        return controllerList;
    }


    List loadFeature(featureInstance){
        return Feature.list(featureInstance);
    }

    def deleteFeature(params){
        Feature feature = Feature.get(params.id);
        if(!feature){
            return "Feature delete failed";
        }

        try{
            // deleteFeatureRelatedData(id);
            def temporaryAuthority = [];
            
            feature.authorities.each {
                temporaryAuthority << it;
            }

            temporaryAuthority.each { authority ->

                feature.removeFromAuthorities(authority);

            }
            feature.delete(flush: true);
            return "Feature deleted successfully";
        } catch (Exception ex){
            return "Feature delete failed";
        }
    }
    /* def deleteFeatureRelatedData(id){
        def featureRoles = FeatureRole.findAllWhere(feature: Feature.get(id));

        for(FeatureRole featureRole : featureRoles){
            featureRole.delete(flush: true);
        }
    }*/

    /**
     * services for authority or role
     */

    boolean saveAuthority(params){
        def authorityInstance = new Authority(params);
        if(!authorityInstance){
            return false;
        }
        if(!authorityInstance.save(flush: true)){

            return  false
        }
        else{
            return true;
        }
    }

    boolean editAuthority(id){
        // def encryptionUtils = new EncryptionUtils("jabait");
        def authorityInstance = Authority.get(id);
        if(!authorityInstance){
            return false;
        } else {
            authorityInstance.save(flush: true);
            return true;
        }
    }

    List loadAuthorities(authorityList){
        return Authority.list(authorityList);
    }

    boolean updateAuthority(authorityId, params){

        def authorityInstance = Authority.get(authorityId);
        authorityInstance.roleTitle = params.roleTitle;
        authorityInstance.description = params.description;
        if(!authorityInstance.save(flush: true)){
            return false;
        }else{
            return true;
        }


    }

    def deleteAuthority(params){

        Authority authority = Authority.get(params.id);

        if(!authority){
            return "Authority delete failed";
        }

        try{

            //delete associated user
            def temporaryUsers = [];

            authority.users.each {
                temporaryUsers << it ;
            }

            temporaryUsers.each { user->
                user.removeFromAuthorities(authority);
            }


            //delete associated features
            def temporaryFeatures = [];
            authority.features.each {
                temporaryFeatures << it
            }
            temporaryFeatures.each { feature ->

                feature.removeFromAuthorities(authority);

            }

            //delete associated groups
            def temporaryGroups = [];
            authority.userGroups.each {
                temporaryGroups << it;


            }

            temporaryGroups.each { userGroup ->
                userGroup.removeFromAuthorities(authority);
            }

            authority.delete(flush: true);
            return "Authority deleted successfully";
        } catch (Exception ex){
            return "Authority delete failed";
        }
    }

    boolean isAllowedForAcl(session){
        def secured = false;
        def userInstance =  User.get(session.user.id)
        userInstance.authorities.features.module.each {
            module->

            module.each {
                data->
                if (data == "security"){
                    secured = true;
                }

            }
        }
        return secured;
    }
    boolean isAllowedForUser(session){
        def secured = false;
        def userInstance =  User.get(session.user.id)
        // System.out.println(userInstance.authorities.features.size());
        userInstance.authorities.features.operation.each {
            operation->

            operation.each {
                data->
                if (data == "userList"){
                    secured = true;
                }

            }
        }
        return secured;
    }

    boolean isAllowedForUserGroup(session){
        def secured = false;
        def userInstance =  User.get(session.user.id)
        // System.out.println(userInstance.authorities.features.size());
        userInstance.authorities.features.operation.each {
            operation->

            operation.each {
                data->
                if (data == "userGroupList"){
                    secured = true;
                }

            }
        }
        return secured;
    }

    boolean isAllowedForConfiguration(session){
        return true;
    }

    boolean isAllowedForEmployee(session){


        return true;

    }

    boolean isAllowedForCalender(session){

        return true;

    }

    boolean isAllowedForLeaveManagement(session){

        return true;

    }
    //new added
    boolean isAllowedForAuthority(session){
        def securedForAuthority = false;
        def userInstance =  User.get(session.user.id)
        // System.out.println(userInstance.authorities.features.size());
        userInstance.authorities.features.operation.each {
            operation->

            operation.each {
                data->
                if (data == "authorityList"){
                    securedForAuthority = true;
                }

            }
        }
        return securedForAuthority;
    }
    boolean isAllowedForFeature(session){
        def securedForFeature = false;
        def userInstance =  User.get(session.user.id)
        // System.out.println(userInstance.authorities.features.size());
        userInstance.authorities.features.operation.each {
            operation->

            operation.each {
                data->
                if (data == "featureList"){
                    securedForFeature = true;
                }

            }
        }
        return securedForFeature;
    }
    /**
     * convert the input string(2007-09-23 or 2007-09-23 00:00:00) into a timestamp(2007-09-23 00:00:00.0) object
     * @param inputString
     * @return
     */
    public Timestamp getTimeStamp(String inputString){
        if(inputString.length() != 19){
            inputString = inputString + " " + "00:00:00";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = dateFormat.parse(inputString);
            long time = date.getTime();

            return new Timestamp(time);
        } catch (ParseException e) {
            //  Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
