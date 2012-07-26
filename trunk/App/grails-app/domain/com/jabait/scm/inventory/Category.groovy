package com.jabait.scm.inventory

class Category {

    String categoryName;
    Category parentCategory;

    static constraints = {
        parentCategory(nullable: true);
    }
}
