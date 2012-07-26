package com.jabait.security

class Authority {

    Integer id
    String roleTitle
    String description
    //Authority parentKey

    static hasMany = [features : Feature, users : User, userGroups : UserGroup]

    static belongsTo = [User, UserGroup];


    static constraints = {
        roleTitle(blank:false,size:1..30,unique:true)
    }

    String toString(){
        return roleTitle
    }

}
