package com.jabait.scm.inventory

//this class contains different type of products like item, resource, service and so on...
class ProductType {
    
    String name;
    String description;

    static constraints = {
    }

    public static void initialize(){
        new ProductType(name: 'Expense Item', description: 'Expense Oriented Items').save();
        new ProductType(name: 'Item', description: 'Generic Items').save();
        new ProductType(name: 'Resource', description: 'Resource Related Items').save();
        new ProductType(name: 'Service', description: 'Service Related Items').save();
    }
}
