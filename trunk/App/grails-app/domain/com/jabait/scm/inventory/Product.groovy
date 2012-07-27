package com.jabait.scm.inventory

class Product {

    Category productCategory;
    String productName;
    ProductType productType;
    String licenseInfo;
    ProductClassification classification;
    ProductImage image;
    boolean active;

    static hasMany = [materials : Material, materialSpecifications : MaterialSpecification]


    static constraints = {
    }
}
