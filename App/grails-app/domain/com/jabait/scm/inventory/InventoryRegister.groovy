package com.jabait.scm.inventory

class InventoryRegister {
    
    Product product;
    Integer onHand = 0;
    Integer onOrder = 0;
    Integer allocated = 0;
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
