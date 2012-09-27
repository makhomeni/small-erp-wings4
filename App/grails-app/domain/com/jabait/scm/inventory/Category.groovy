package com.jabait.scm.inventory

class Category {

    String categoryName;
    Category parentCategory;

    static constraints = {
        categoryName(unique:  true);
        parentCategory(nullable: true);
    }

    public static void initialize(){
        if(Category.getCount() == 0){
            Category category = new Category();
            category.categoryName = "Chemical product";
            category.save();

        }
    }
}
