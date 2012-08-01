package com.jabait.scm.inventory

class ProductClassification {
    
    String classification;// Bill of Material, Purchased, Manufactured, Planned Item, Discontinued
    String description;

    static constraints = {
    }

    public static void initialize(){
        new ProductClassification(classification: 'Bill of Material', description: 'Raw Material').save();
        new ProductClassification(classification: 'Purchased', description: 'Purchased Item').save();
        new ProductClassification(classification: 'Manufactured', description: 'Manufactured Item').save();
        new ProductClassification(classification: 'Planned Item', description: 'Planned Item for MRP').save();
        new ProductClassification(classification: 'Discontinued', description: 'Discontinued Item').save();
    }
}
