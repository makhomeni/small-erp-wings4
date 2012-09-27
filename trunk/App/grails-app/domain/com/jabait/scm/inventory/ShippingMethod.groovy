package com.jabait.scm.inventory

class ShippingMethod {
    
    String shippingMethod;
    String description;

    static constraints = {
    }
    public static void initialize(){
        if(ShippingMethod.getCount() == 0){
            ShippingMethod shippingMethod1 = new ShippingMethod();
            shippingMethod1.shippingMethod = "On Ship";
            shippingMethod1.description = "Product will be transported using ship";
            shippingMethod1.save();
        }
    }
}
