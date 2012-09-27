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

    public static initialize(){
        if(PaymentTerm.getCount() == 0){
            PaymentTerm paymentTerm = new PaymentTerm();
            paymentTerm.name = "late";
            paymentTerm.days = 90;
            paymentTerm.description = "Late pay for some reasons";
            paymentTerm.save();
        }
    }
}
