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
    String unitOfMeasure; //kg,meter,feet
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
        image(nullable: true);
    }

    public static initialize(){
        if(Product.getCount() == 0){
            Product product = new Product();

            product.stockKeepingUnit = "Test";
            product.universalProductCode = "001";
            product.productCategory = Category.get(1);
            product.productName = "Chemical";
            product.unitOfMeasure = "KG";
            product.productType = ProductType.get(1);
            product.licenseInfo = "12345";
            product.classification = ProductClassification.get(1);
            product.alertNotes = "no";
            product.productDetails = "product details";
            product.taxable = true;
            product.active = true;

            product.save();
        }

    }
}
