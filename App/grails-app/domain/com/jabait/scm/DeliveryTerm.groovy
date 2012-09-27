package com.jabait.scm

class DeliveryTerm {
    
    String terms;
    String description;

    static constraints = {
    }

    public static initialize(){
        if(DeliveryTerm.getCount() == 0){
            DeliveryTerm deliveryTerm = new DeliveryTerm();
            deliveryTerm.terms = "test";
            deliveryTerm.description = "test delivery";
        }
    }
}
