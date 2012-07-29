package com.jabait.accounting

class PaymentTerm {

    String name;
    Integer days;
    String description;

    static constraints = {
        name(nullable: false)
        days(nullable: false)
        description(nullable: true)
    }
}
