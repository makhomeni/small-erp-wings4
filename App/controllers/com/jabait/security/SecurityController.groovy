package com.jabait.security

import com.jabait.coresecurity.AuthenticationToken
import grails.converters.JSON
import java.text.DateFormat
import java.text.SimpleDateFormat
import com.jabait.coresecurity.util.EncryptionUtils
import com.jabait.security.User

class SecurityController {

    static allowedMethods = [save: "POST", update: "POST", saveUser: "POST", saveFeature: "POST", authenticateUser: "POST", test2: "POST", saveUserProfile: "POST"]


    def userDetailsService
    def securityService
    def grailsApplication

    def index() {
        render(view: "index")
    }

    def userSummary(){
        render userDetailsService.userDistinctType();
    }

    def logout(){
        session.removeValue("user")
        session.invalidate();
        redirect(controller: "auth", action: "login")
    }
    
    def loggedout(){
        render(view: "loggedout");
    }

    def authCheck(){
        if (!session.user){
            redirect(action: "login");
            return false;
        }
    }

    def unauthorized(){
        render(view: "/unauthorized");
    }


    def authenticateUser(){
        AuthenticationToken authToken = securityService.doAuthentication(params);
        def user = authToken.principal;
        
        if (user){
            session.user = user;
            if(!session.user){
                redirect(action: "login")
                return false;
            } else {
                session.credentials = authToken.credential;
                session.grantedAuthority = authToken.grantedAuthority;
                redirect(controller:"application", action: "index");
                return true;
            }

        } else {
            flash.message = "Bad Credentials!!!";
            render(view: "/auth/login");
        }
    }

    def saveUser(){
       // def userInstance = params;
        def submittedUser = User.findByUserCode(params.userCode)
        if (submittedUser){
            flash.message = "User already exists";
            redirect(action: "createUser");
        }else{
            if (params.password==params.confirmPassword){
                //
                if (!userDetailsService.saveUser(params)){
                    flash.message="User creation failed";
                }

                else{
                    flash.message = "User created successfully";
                }
                redirect(action: "createUser");
            }
            else {

                flash.message="Password not matched";
                redirect(action: "createUser");
            }

        }

    }

    def editUser(){
        def userInstance = User.get(params.id);
        render(view: "user_update", model: [userInstance:userInstance, type: "Edit User"]);
    }
    
    def updateUser(){

            if(params.password == params.confirmPassword){
                if (!userDetailsService.editUser(params.id, params)){
                    flash.message="User update failed";
                }else{
                    flash.message="User updated successfully";
                }
                redirect(action: "editUser", id: params.id)
            } else{
                flash.message="Password and confirm password does not match";
                redirect(action: "editUser", id: params.id)
            }


    }
    
    def createUser(){
        render(view: "user_create", model: [type: "Create User"]);
    }

    def userList(){
        render(view: "user_list", model:[type: "User List"]);
    }

    def userJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }

        List<User> userList;

        if(params.userCode != null){
            userList = User.findAllByUserCode(params.userCode.toString());
        }else{
            userList = User.list();
        }
        
        Map<String, String> userMap;
        List userListForJson = new ArrayList();
        
        for (User user: userList){
            userMap = new HashMap<String, String>();
//            def userFirstName;
//            def userLastName;
//            def userMiddleName;
//
//            if (user.){
//
//            }
            userMap.put("id", user.id.toString());
            userMap.put("userCode", user.userCode.toString());
            userMap.put("active", user.active.toString());

            userListForJson.add(userMap);

        }
        int max = 10;
        int totalCount = userListForJson.size();

        if (totalCount < 10){
            max = userListForJson.size();
        }
        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([users : userListForJson.asList().subList(start, start + limit > userListForJson.size() ? userListForJson.size() : start + limit),totalCount:totalCount] as JSON);

    }

    def deleteUser(){
        flash.message = userDetailsService.deleteUser(params);
        redirect(action: "userList");
    }
    
    def jsonSessionValue(){
        def features = User.get(params.id).authorities.features
        render([grantedAuthorityJson: features] as JSON);
    }
    /**
     * use this action for logged users authority group
     */
    def jsonValueForUserDetails(){
        def authorities = User.get(params.id).authorities;
        def groups = User.get(params.id).userGroups;
        render([authorities: authorities, groups: groups] as JSON);
    }


    def saveUserGroup(){
        def userGroupInstance = params
        if(userDetailsService.saveUserGroup(userGroupInstance)){
            flash.message = "UserGroup has been successfully created";
            redirect(action: 'createUserGroup');
        } else {
            flash.message = "UserGroup creation failed";
            redirect(action: 'createUserGroup');
        }
//        render(view: "user_group_create");

    }
    def editUserGroup(){
        def userGroupInstance = UserGroup.get(params.id);
        render(view: "user_group_update", model: [userGroupInstance:userGroupInstance, type: "Edit UserGroup"]);
    }

    def updateUserGroup(){
        if (userDetailsService.editUserGroup(params.id, params)){
             flash.message = "User group updated successfully";
        }
        else{
            flash.message = "User group update failed";
        }
        redirect(action: 'editUserGroup', id: params.id);
    }

    def createUserGroup(){
        render(view: "user_group_create", model: [type: "Create User Group"]);
    }

    def userGroupList(){
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        render(view: "user_group_list", model: [userGroupInstanceList: userDetailsService.loadUserGroup(params),
                userGroupInstanceTotal: UserGroup.count(), type: "User Group List"])
    }

    def userGroupJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ userGroups: UserGroup.list(offset: params.start, max: params.max), totalCount: UserGroup.count() ] as JSON );
    }


    def deleteUserGroup(){
        def userGroupInstance = UserGroup.get(params.id)
        if (!userDetailsService.deleteUserGroup(params.id)){

            flash.message = "userGroup.delete.failed.message"
            flash.args = [userGroupInstance.groupName]
            flash.default = "User group delete failed";
        }
        else{
            flash.message = "userGroup.delete.message"
            flash.args = [userGroupInstance.groupName]
            flash.default = "User group deleted";
        }
        redirect(action: "userGroupList");
        return true;
    }

    def createFeature(){

        render(view: "feature_create", model: [type: "Create Feature"]);
    }
    
    def saveFeature(){
        if(!securityService.saveFeature(params)){
            flash.message="Feature creatiion failed";
        }
        else{
            flash.message="Feature created successfully";
        }

//        render(view: "feature_create");
        redirect(action: 'createFeature');
    }
    def editFeature(){
        def featureInstance = Feature.get(params.id);
        render(view: "feature_update", model: [featureInstance:featureInstance, type:  "Edit Feature"]);
    }

    def updateFeature(){
        System.out.print("enter update feature "+params);
        if (!securityService.editFeature(params.id, params)){
            flash.message = "Feature update failed";
        }else{
            flash.message = "Feature updated successfully";
        }
        redirect(action: "editFeature", id: params.id);

    }

    def featureList(){
        params.max = Math.min(params.max ? params.int('max') : 5, 10)
        render(view: "feature_list", model: [featureInstanceList: securityService.loadFeature(params),
                featureInstanceTotal: Feature.count(), type: "Feature List"])
    }
    def deleteFeature(){

        flash.message = securityService.deleteFeature(params);
        redirect(action: "featureList");
        return true;
    }

    def featureModuleJsonData(){
        def featureModuleList = Feature.executeQuery("select distinct feature.module from Feature feature");
        def featureModuleCount = Feature.executeQuery("select count (distinct feature.module) from Feature feature").get(0);
        def modulesKeyValuePair;
        List moduleList = new ArrayList();

        int id = 0;
        featureModuleList.each {
            featureModule->
            System.out.println(featureModule)
            id++;
            modulesKeyValuePair = [id:id, module: featureModule]
            moduleList.add(modulesKeyValuePair);
        }
        render([modules: moduleList, totalCount: featureModuleCount] as JSON);
    }
    def featureOperationJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        def moduleName = params.moduleName;
        def featureOperationList = Feature.executeQuery("select feature.operation from Feature feature where feature.module = '"+ moduleName +"'");
        def featureOperationCount = featureOperationList?.size();
        render([operations: Feature.findAllByModule(moduleName, [offset: params.start, max: params.max]), totalCount: featureOperationCount] as JSON);
    }

    def featureOperationJsonDataUsingAuthority(){
        def authorityId = 1;
        Authority.get(authorityId).features
        render([operations: Authority.get(authorityId).features, totalCount: 25] as JSON);
    }
    def featureJsonDataForAuthority(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        println(params.authorityId)
        def authority = Authority.get(params.authorityId)
        def totalCount = authority?.features?.size()?:0;



        //for pagination
        int max = 10;


        if (totalCount < 10) {
            max = totalCount
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([features : authority.features.asList().subList(start, start + limit > totalCount ? totalCount : start + limit),totalCount:totalCount] as JSON);


    }
    def showFeatureForUser(){
        def feature = User.get(params.id).authorities.features;
        feature.operation.each {
            operation->
            operation.each {
                name->
            }

        }
        if(feature.size() < 1){
            flash.message = "Feature not available for this user"
        }
        render(view: "user_available_feature", model:[features: feature])
    }
    def showUserDetails(){
        def authorities = User.get(params.id).authorities
        def userGroup = User.get(params.id).userGroups
        System.out.println(authorities)
        authorities.each {
            authorityName ->
            System.out.println(authorityName)
        }
        render(view: "user_available_feature", model: [authorities: authorities, userGroups: userGroup])
    }
    
    def showAuthorityDetails(){
        def authority = Authority.get(params.id)
        def users = authority.users;
        def userGroup  = authority.userGroups;
        def features = Authority.get(params.id).features
        if(features.size() < 1){
            flash.message = "Feature not available for this authority"
        }

        render(view: "authority_available_features", model: [features: features, authorityId: params.id, userGroups: userGroup, users: users]);
    }

    def userGroupDetails(){
        def userGroup = UserGroup.get(params.id)
        def authority = userGroup.authorities
        def users = userGroup.users
        System.out.println(authority)
        authority.each {
            authorityName ->
            System.out.println(authorityName)
        }
      render(view: "user_group_details",model: [authorities:authority, users: users, userGroupInstance: userGroup])
    }
    def featureJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }
        render( [ features: Feature.list(offset: params.start, max: params.max), totalCount: Feature.count() ] as JSON );
    }
    def featureComboJsonData(){
        render( [ features: securityService?.loadFeature(params), totalCount: Feature.count() ] as JSON );
    }
    def featureEmptyData(){
        render( [ features: "", totalCount: Feature.count() ] as JSON );
    }

    def authority(){
        render(view: "authority_create");
    }
    def saveAuthority(){
        def authorityInstance = params
        if (!securityService.saveAuthority(authorityInstance)){
             flash.message = "Authority creation failed";
        }
        else{
            flash.message = "Authority created successfully";
        }
        redirect(action: "createAuthority");
    }


    def createAuthority(){
        render(view: "authority_create", model: [ type: "Create Authority"]);
    }

    def editAuthority(){
        def authorityInstance = Authority.get(params.id);
        render(view: "authority_update", model: [authorityInstance: authorityInstance, type: "Edit Authority"]);
    }

    def updateAuthority(){
        if(!securityService.updateAuthority(params.authorityId, params)){
            flash.message = "Authority update failed";
        }
        else{
            flash.message = "Authority updated successfully";
        }
        redirect(action: "editAuthority", id: params.authorityId);

    }

    def authorityList(){
        render(view: "authority_list", model:  [type: "Authority List"]);
    }

    def deleteAuthority(){
        flash.message = securityService.deleteAuthority(params);
        redirect(action: "authorityList");
    }

    def authorityJsonData(){
        if (!params.limit){
            params.max = 10;
        }else{
            params.max = params.limit;
        }

        render( [ authorityList: Authority.list(offset: params.start, max: params.max), totalCount: Authority.count() ] as JSON );
    }
    /**
     * Role CRUD  end
     */
    def test(){

        render ("authority features count  " + Feature.count);
    }


    def editProfile(){
        def user = User.findByUserCode(session?.user?.userCode);
        def userProfileInstance = UserProfile.findByOwner(user);
        println(userProfileInstance.id+" user id = "+user.id)
        def emailAddressInstance;
        def nameInstance;
        def addressInstance;

        if (userProfileInstance != null){
            emailAddressInstance = userDetailsService.loadEmailAddress(userProfileInstance);
            nameInstance = userDetailsService.loadName(userProfileInstance);
            addressInstance = userDetailsService.loadAddress(userProfileInstance);
        }

        render(view: "user_profile_create", model: [userProfileInstance: userProfileInstance,
                emailAddressInstance: emailAddressInstance,nameInstance:nameInstance, addressInstance:addressInstance]);
    }
    def profileView(){
        def userProfileInstance = userDetailsService.loadUserProfile(session?.user)
        render(view: "profile_view", model: [userProfileInstance: userProfileInstance])
//        render session?.user?.userCode
    }

    def saveUserProfile(){

        println("id = "+params.ownerId);
        def user = User.findByUserCode(session?.user?.userCode);
        def userProfileInstance = UserProfile.findByOwner(user);
        println("instance = "+userProfileInstance)

        def nameInstance = Name.get(userProfileInstance.name.id)
        nameInstance.firstName = params.firstName;
        nameInstance.middleName = params.middleName;
        nameInstance.surname =  params.surname;
        nameInstance.nickname = params.nickName;

        def emailInstance = Email.get(userProfileInstance.emailAddress.id)
        emailInstance.address = params.emailAddress;

        def addressInstance = Address.get(userProfileInstance.address.id)
        addressInstance.country = params.country;
        addressInstance.extendedAddress = params.extendedAddress;
        addressInstance.poBox = params.poBox;
        addressInstance.postalCode = params.postalCode;
        addressInstance.region = params.region;
        addressInstance.streetAddress = params.streetAddress;

        String theDate = params.dateOfBirth;
        def newDate = new Date().parse("d/M/yyyy H:m:s", theDate)
        System.out.println("formated date = "+newDate)

        userProfileInstance.dateOfBirth = newDate;

        userProfileInstance.gender = params.gender;
        userProfileInstance.title = params.title;

        userProfileInstance.name = nameInstance;
        userProfileInstance.address = addressInstance;
        userProfileInstance.emailAddress = emailInstance;

        if (!userProfileInstance.save(flush: true)){
             flash.message = "User profile update failed"
             redirect(action: "editProfile")
        }
        else{
            flash.message = "User profile updated successfully"
            redirect(action: "editProfile")
        }

    }

    def authorityAssignment(){
        System.out.println("enter auth assignment");

        User user = User.get(params.userId);
        if(!user){
            render( [ "success" : false ] as JSON );
        } else {
            params.authorities.each {
                authorityId ->
                println(authorityId)
                Authority authority = Authority.get(authorityId);
                user.addToAuthorities(authority);
                render(["success" : true] as JSON);
            }
        }
    }
    def userGroupAssignment(){
        User user = User.get(params.userId);
        System.out.println(user)

        if(!user){
            render(["success" : false] as JSON);
        } else {
            params.userGroups.each {
                groupId ->
                System.out.println(groupId)
                //get user group from id
               UserGroup userGroup = UserGroup.findById(groupId)
                user.addToUserGroups(userGroup);
                def authorities = userGroup.authorities
                authorities.each {
                    authority ->
                     user.addToAuthorities(authority)

                }

               render(["success" : true] as JSON);
            }
        }
    }


    def authorityAssignmentToGroup(){
        UserGroup userGroup = UserGroup.get(params.userGroupId);
        System.out.println(userGroup);
        if(!userGroup){
            render( [ "success" : false ] as JSON );
        } else {
            params.authorities.each {
                authorityId->
                System.out.println(authorityId)
                Authority authority = Authority.findById(authorityId);
                userGroup.addToAuthorities(authority);
                render(["success" : true] as JSON);
            }
        }
    }


    
    def featureAssignment(){
        Authority authority = Authority.get(params.authorityId);

        if (!authority){
            render( [ "success" : false ] as JSON );
        }
        else{
            if (params.featureOperations.toString().contains(",")){

                String s = params.featureOperations.toString().replaceAll("\\([^\\(]*?\\)", "");
                int indexOfOpenBracket = s.indexOf("[");
                int indexOfLastBracket = s.lastIndexOf("]");

                def an = s.substring(indexOfOpenBracket+1, indexOfLastBracket);

                def arg = an.split(",")
                for(int i = 0; i < arg.length; i++){
                    Feature feature = Feature.get(arg[i]);
                    authority.addToFeatures(feature);
                    render(["success" : true] as JSON);
                }
            } else {
                System.out.println("operation Id" + params.featureOperations)
                Feature feature = Feature.get(Integer.parseInt(params.featureOperations.toString()));
                authority.addToFeatures(feature);
                render(["success" : true] as JSON);
            }
            
        }

    }
    def test4(){
        EncryptionUtils encUtil = new EncryptionUtils("jabait");
        def user = new User()
        def encryptedPassword = encUtil.encrypt("123");
        user.password = encryptedPassword;
        user.userCode = "admin";
        user.active = true;
        def user2 = User.findByUserCode("admin")
        if (!user2){
           println("painai")
        } else{
            println("paise")
        }
    }

    def deleteFeatureFromAuthority(){
        println("Enter in delete ")
        System.out.println("params.authorityId = "+params.authorityId + " params.featureId = "+ params.featureId)
        Authority authority = Authority.get(params.authorityId);
        Feature feature = Feature.get(params.featureId);
        feature.removeFromAuthorities(authority)
        flash.message = "Feature removed";
        redirect(action: "showAuthorityDetails", id: params.authorityId);
    }

}
