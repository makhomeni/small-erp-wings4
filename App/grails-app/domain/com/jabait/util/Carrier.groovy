package com.jabait.util

class Carrier {

    String carrierName;
    String carrierDescription;

    static constraints = {
    }

    public static void initialize(){
        if(Carrier.count() == 0){
            new Carrier(carrierName: "Will Call", carrierDescription: "Will Call").save();
            new Carrier(carrierName: "USPS", carrierDescription: "USPS").save();
            new Carrier(carrierName: "FedEx", carrierDescription: "FedEx").save();
            new Carrier(carrierName: "DHL", carrierDescription: "DHL").save();
            new Carrier(carrierName: "Delivery", carrierDescription: "Delivery").save();
        }
    }
}
