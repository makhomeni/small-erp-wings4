package com.jabait.security

class DrivingLicense {
    
    String drivingLicenseNumber;
    Date issueDate;
    String issueFrom;
    Date expiredDate;


    static belongsTo = [Identification]

    static constraints = {
    }
}
