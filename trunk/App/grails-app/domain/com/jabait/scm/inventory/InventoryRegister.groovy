package com.jabait.scm.inventory

class InventoryRegister {
    
    Product product;
    Double onHand = 0.0; //when purchased then quantity will be added if sold subtracted to the onHand
    Double onHandFromLocalSupplier = 0.0;
    Double onHandImportByLC = 0.0;
    Double onSalesReturn = 0.0;
    Double onLoanReturn = 0.0;


    Double onPurchaseOrder = 0.0;//how many product is ordered will be added
    Double onSalesOrder = 0.0;
    Double allocated = 0.0;
    //needed from avobe
    Double committed = 0.0;

    Double unavailable = 0.0;
    Double unavailableFromSales = 0.0;
    Double unavailableFromSample = 0.0;
    Double unavailableFromLoan = 0.0;

    Double backOrdered = 0.0;
    Double dropShip = 0.0;

    Double availableForSale = 0.0;
    Double availableToPick = 0.0;
    
    Date modifiedDate = new Date();

    def beforeInsert = {
        modifiedDate = new Date();
    }

    def beforeUpdate = {
        modifiedDate = new Date();
    }

    static mapping = {
        modifiedDate(sqlType: 'date');
    }

    static constraints = {
        product(nullable: false)
    }
}
