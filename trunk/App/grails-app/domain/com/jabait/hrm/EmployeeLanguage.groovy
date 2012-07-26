package com.jabait.hrm

class EmployeeLanguage {

    String competency;
    String comments;

    static hasMany = [employees : EmployeeProfile]

    static constraints = {
    }
}
