package com.jabait.scm.inventory

class InventoryService {

    def serviceMethod() {

    }

    List findAllProducts(){
        List<Product> products = Product.list();
        List<Map<String,Object>> returnedProducts = new ArrayList<HashMap<String,Object>>();
        Map<String,Object> productStorage;
        for (Product product : products) {
            productStorage = new HashMap<String,Object>();
            productStorage.put("productName", product.productName);
            productStorage.put("categoryName", product.productCategory.categoryName);
            productStorage.put("classification", product.classification.classification);
            productStorage.put("licenseInfo", product.licenseInfo);
            productStorage.put("productType", product.productType.name);
            returnedProducts.add(productStorage);
        }

        return returnedProducts;
    }
}
