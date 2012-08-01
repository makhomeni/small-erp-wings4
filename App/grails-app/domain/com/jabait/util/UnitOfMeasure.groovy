package com.jabait.util

class UnitOfMeasure {
    
    String uom; //k.g,feet,meter
    String description;

    static constraints = {
    }

    public static void initialize(){
        if (UnitOfMeasure.count() == 0 ) {
            new UnitOfMeasure(uom: 'K.G', description: 'Killogram').save();
            new UnitOfMeasure(uom: 'Feet', description: 'Feet').save();
            new UnitOfMeasure(uom: 'Meter', description: 'Meter').save();
        }
    }
}
