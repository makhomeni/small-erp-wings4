package com.jabait.scm.inventory

import com.jabait.util.UnitOfMeasure

class Product {

    Category productCategory;
    String productName;
    ProductType productType;
    String licenseInfo;
    ProductClassification classification;
    ProductImage image;
    UnitOfMeasure unitOfMeasure; //kg,meter,feet
    boolean active;

    static hasMany = [materials : Material, materialSpecifications : MaterialSpecification]


    static constraints = {
        productCategory(nullable: false);
        productName(nullable: false);
        productType(nullable: false);
        classification(nullable: false);
        unitOfMeasure(nullable: true);
    }
}
