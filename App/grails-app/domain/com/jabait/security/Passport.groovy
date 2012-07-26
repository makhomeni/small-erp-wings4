package com.jabait.security

class Passport {
    String passportNumber;
    Date issueDate;
    String issueFrom;
    Date expiredDate;


    static belongsTo = [Identification]



    static constraints = {
    }
}
