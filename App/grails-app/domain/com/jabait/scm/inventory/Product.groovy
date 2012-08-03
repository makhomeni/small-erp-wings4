package com.jabait.scm.inventory

import com.jabait.util.UnitOfMeasure

class Product {

    String stockKeepingUnit; //usually mention as SKU; it would be totally unique
    String universalProductCode; //especially UPC; for UPC-A 12 digits are allocated;
    // for UPC-E 6 digits are allocated
    Category productCategory;
    String productName;
    ProductType productType;
    String licenseInfo;
    ProductClassification classification;
    ProductImage image;
    UnitOfMeasure unitOfMeasure; //kg,meter,feet
    String alertNotes;
    String productDetails;
    Boolean taxable = false;
    Boolean active = true;

    static hasMany = [materials : Material, billOfMaterials : BillOfMaterials, substituteProducts : Product]


    static constraints = {
        stockKeepingUnit(unique: true);
        productCategory(nullable: false);
        productName(nullable: false);
        productType(nullable: false);
        classification(nullable: false);
        unitOfMeasure(nullable: true);
    }
}
