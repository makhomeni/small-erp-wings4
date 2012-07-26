package com.jabait.security

import com.jabait.coresecurity.util.EncryptionUtils

class UserDetailsService {
    def grailsApplication

    EncryptionUtils enc = new EncryptionUtils("jabait");
    def serviceMethod() {

    }

    def getListOfUser(params){
        def json

        def users = User.withCriteria{
            and{
                or{
                    eq("userCode", params.query)
                }
                ne("id", params.author.id)
            }
        }

        json = [
                total:users.size(),
                results:users
        ]
        return json
    }

    //code for user domain
    def saveUser(params){
        def userInstance = params;
        if(!userInstance){
            return false;
        }
        EncryptionUtils encUtil = new EncryptionUtils("jabait");
        userInstance.properties = params;
        def encryptedPassword = encUtil.encrypt(params.password);

        def user = new User()
        user.password =  encryptedPassword;
        user.userCode = params.userCode;
        user.active = true;
        if(!user.save(flush: true)){
            return false;
            println("not saved")
        }
        else{
            return true;
        }
    }
    def userDistinctType(){
        def userCriteria = User.createCriteria();
        def userResults = userCriteria.list {
            projections {
                countDistinct("class")
            }
        }

        return userResults.get(0);
    }

    def editUser(id,params){
//        def userInstance = User.get(id);
//       // def passw
//        if(!userInstance){
//            return false;
//        } else {
//            userInstance.properties = params
//            def password = enc.encrypt(params.password)
//            userInstance.setPassword(password)
//            userInstance.save(flush: true);
//            return true;
//        }
        
        def user = User.get(params.id);
        if(user){
            if(params.active.equals("True")){
                user.active = true;
            }

            user.password =  enc.encrypt(params.password);

            if(user.save(flush: true)){
                return true;
            }else{
                return false;
            }
        } else{
            return false
        }

    }

    // for checking svn
    List loadUsers(userInstance){
        return User.list(userInstance);
    }

    boolean updateUser(id){
        def user = User.get(id);
        user.save(flush: true);

        return true;
    }

    def deleteUser(params){
        User user = User.get(params.id);
        if(!user){
            return "User delete failed";
        }
        try{
            def temporaryAuthority = [];
            user.authorities.each {
                temporaryAuthority << it;
            }
            temporaryAuthority.each {authority->
                user.removeFromAuthorities(authority);
            }
            
            def temporaryUserGroup = [];
            user.userGroups.each {
                temporaryUserGroup << it;
            }

            temporaryUserGroup.each {userGroup->
                user.removeFromUserGroups(userGroup);
            }

            user.delete(flush: true);

            return "User deleted successfully";
        } catch (Exception ex){
            return "User group delete failed";
        }
    }
    
    UserProfile loadUserProfile(user){
        UserProfile userProfile;
        if(!user){
            return null;
        } else {
            try{
                userProfile = UserProfile.findByOwner(user);
                return userProfile;
            } catch (Exception ex){
                return null;
            }

        }
    }

    Email loadEmailAddress(userProfileInstance){
        try{
            return Email.findByUserProfile(userProfileInstance);
        } catch (Exception ex){
            return null;
        }
    }
    
    Name loadName(userProfileInstance){
        try{
            return Name.findByUserProfile(userProfileInstance);
        } catch (Exception ex){
            return null;
        }
    }

    Address loadAddress(userProfileInstance){
        try{
            return Address.findByUserProfile(userProfileInstance);
        } catch (Exception ex){
            return null;
        }
    }

    def saveUserProfile(params){
        def userProfileInstance = params;
        def emailInstance = new Email( params.emailAddress);
        def addressInstance = new Address(params.country)
        userProfileInstance.properties = params;
        new UserProfile(userProfileInstance)
                        .addToUserProfile(emailInstance)
                        .addToUserProfile(addressInstance)
                        .save();

    }

    List loadUserGroup(userInstance){
        return User.list(userInstance);
    }

    boolean saveUserGroup(userGroupInstance){
        try{
            def userGroup = new UserGroup(userGroupInstance);
            userGroup.save(flush: true);

            return true;
        } catch (Exception ex){
            return false;
        }
    }

    def editUserGroup(id, params){
        def userGroupInstance = UserGroup.get(id);
        // def passw
        if(!userGroupInstance){
            return false;
        } else {
            userGroupInstance.properties = params
            if(!userGroupInstance.save(flush: true)){
                return false;
            }
            else{
                return true;
            }

        }

    }
    //delete user group
    boolean deleteUserGroup(id){
        def userGroupInstance = UserGroup.get(id);
        if(!userGroupInstance){
            return false;
        }

        try{

            //remove authority from this user group
            def temporaryAuthority =  [];
            userGroupInstance.authorities.each {
//                userGroup.removeFromAuthorities(authority);
                temporaryAuthority << it;

            }

            temporaryAuthority.each {authority->
                userGroupInstance.removeFromAuthorities(authority)
            }

            //remove users from this user group

            def temporaryUser = [];

            userGroupInstance.users.each {
                temporaryUser << it;

            }

            temporaryUser.each {user->
                userGroupInstance.removeFromUsers(user)
            }

            userGroupInstance.delete();
            return true;
        } catch (Exception ex){
            return false;
        }
    }


}
