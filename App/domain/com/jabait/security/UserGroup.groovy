package com.jabait.security

class UserGroup {

    Integer id
    String groupName
    String description

    static mapping = {
        table("user_group")
    }

    static hasMany = [users : User, authorities: Authority]

    static belongsTo = [User];

    static constraints = {
        groupName(blank: false,unique: true,size: 2..50)
        description(nullable: true)
    }

    String toString(){
        return groupName
    }
}
