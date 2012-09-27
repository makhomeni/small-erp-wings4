package com.jabait.scm.inventory

class InventoryRegister {
    
    Product product;
    Integer onHand = 0; //when purchased then quantity will be added if sold subtracted to the onHand
    Integer onPurchaseOrder = 0;//how many product is ordered will be added
    Integer onSalesOrder = 0;
    Integer allocated = 0;
    //needed from avobe
    Integer committed = 0;
    Integer unavailable = 0;
    Integer backOrdered = 0;
    Integer dropShip = 0;
    Integer availableForSale = 0;
    Integer availableToPick = 0;


    static constraints = {
        product(nullable: false)
    }
}
