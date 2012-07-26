package com.jabait.scm.inventory

class InventoryService {

    def serviceMethod() {

    }

    List findAllProducts(){
        List<Product> products = Product.list();
        List returnedProducts = new ArrayList();
        Map<String,Object> productStorage = new HashMap<String,Object>();
        for (Product product : products) {
            productStorage.put("productName", product.productName);
        }
        return null;
    }
}
