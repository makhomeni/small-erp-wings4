package com.jabait.scm.inventory

import grails.converters.JSON

class InventoryController {

    def inventoryService;

    def index() { }

    def createProduct(){
        render();
    }

    def saveProduct(){
       redirect(action: "createProduct");
    }

    def productList(){
        render(view: "/scm/product_list");
    }

    def productJsonData(){
        List products = inventoryService.findAllProducts();
        int max = 10;
        int totalCount = Product.count();


        if (products.size() < 10) {
            max = products.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([products : products.asList().subList(start, start + limit > products.size() ?
            products.size() : start + limit), totalCount:totalCount] as JSON);
    }

}
