package com.jabait.scm.inventory

class Category {

    String categoryName;
    Category parentCategory;

    static constraints = {
        categoryName(unique:  true);
        parentCategory(nullable: true);
    }
}
