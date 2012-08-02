package com.jabait.scm.inventory


class InventoryService {

    def serviceMethod() {

    }

    boolean saveCategory(params){
        try{
            Category category = new Category();
            category.categoryName = params.categoryName;
            if(params.category != null) category.parentCategory = Category.get(params.category);
            if(category.hasErrors()){
                println(category.errors)

            }
            category.save(flush: true);

            return true;
        } catch (Exception ex){
            return false;
        }
    }
    
    List findAllCategories(){
        List<Category> categories = Category.list();
        List<Map<String,Object>> returnedCategories = new ArrayList<HashMap<String,Object>>();
        Map<String,Object> categoryStorage;
        for (Category category: categories) {
            categoryStorage = new HashMap<String,Object>();
            categoryStorage.put("categoryName", category.categoryName);
            categoryStorage.put("parentCategory", category?.parentCategory?.categoryName);
            returnedCategories.add(categoryStorage);
        }

        return returnedCategories;
    }

    boolean deleteCategory(params){
        try{
            Category.get(params.id).delete();
            return true;
        } catch(Exception ex){
            return false;
        }
    }

    boolean saveProduct(params){
        try{
            Product product = new Product();
            product.productCategory = Category.get(params.category);
            product.productName = params.productName;
            product.classification = ProductClassification.get(params.classification);
            product.licenseInfo = params.licenseInfo;
            product.productType = ProductType.get(params.productType);
            product.unitOfMeasure = ProductType.get(params.uom);
            if(product.hasErrors()){
                println(product.errors)

            }
            product.save(flush: true);

            return true;
        } catch (Exception ex){
            return false;
        }
    }

    List findAllProducts(){
        List<Product> products = Product.list();
        List<Map<String,Object>> returnedProducts = new ArrayList<HashMap<String,Object>>();
        Map<String,Object> productStorage;
        for (Product product : products) {
            productStorage = new HashMap<String,Object>();
            productStorage.put("id", product.id);
            productStorage.put("productName", product.productName);
            productStorage.put("categoryName", product.productCategory.categoryName);
            productStorage.put("classification", product.classification.classification);
            productStorage.put("licenseInfo", product.licenseInfo);
            productStorage.put("productType", product.productType.name);
            returnedProducts.add(productStorage);
        }

        return returnedProducts;
    }

    boolean deleteProduct(params){
        try{
            Product.get(params.id).delete();

            return true;
        } catch(Exception exception){
            return false;
        }

    }
    
    Product findProductById(id){
        return Product.get(id);
    }

    boolean updateProduct(params){
        try{
            Product product = Product.get(params.id);
            product.properties = params;
            product.save();

            return true;
        } catch(Exception exception){
            return false;
        }
    }
}
