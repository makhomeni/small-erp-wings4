package com.jabait.security

class UserProfile {

    Email emailAddress;
    Name name;
    String gender;
    Date dateOfBirth;
    Date updated;
    String title;
    String imageUrl;
    Address address;
    User owner;


    static constraints = {
        name(nullable:false)
        emailAddress(nullable: false)
        imageUrl(nullable: true)
        gender(nullable: false)
        dateOfBirth(nullable: true)
        title(nullable: false)
        updated(nullable: true)
        address(nullable: true)
    }
}
