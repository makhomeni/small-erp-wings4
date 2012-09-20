package com.jabait.scm

import com.jabait.scm.inventory.InventoryService
import com.jabait.scm.inventory.Product

class Purchase {

    Vendor vendor;
    Product product;
    Date purchaseDate;
    Double price;
    Integer quantity;

    static constraints = {
    }
}
