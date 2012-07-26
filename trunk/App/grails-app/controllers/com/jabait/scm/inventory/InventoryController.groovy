package com.jabait.scm.inventory

class InventoryController {

    def inventoryService;

    def index() { }

    def createProduct(){
        render();
    }

    def productList(){
        render(view: "/scm/product_list");
    }

    def productJsonData(){
        List products = inventoryService.findAllProducts();
    }

}
