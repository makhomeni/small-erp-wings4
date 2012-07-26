package com.jabait.hrm

class Training {

    String description;
    
    static hasMany = [employee:Employee]

    static belongsTo = [Employee]


    static constraints = {
    }
}
