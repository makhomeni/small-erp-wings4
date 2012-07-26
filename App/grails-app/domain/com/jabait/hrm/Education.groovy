package com.jabait.hrm

class Education {
    
    String instituteName;
    String certificationName;
    Date dateOfCertification;
    String result;

    static hasMany = [educatedEmployee : Employee]

    static belongsTo = [Employee]

    static constraints = {
    }
}
