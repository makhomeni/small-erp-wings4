package com.jabait.scm.inventory

import com.jabait.scm.Warehouse


//The storage bin is the smallest available unit of space in a warehouse
class StorageBin {

    static belongsTo = [Warehouse];

    static hasMany = [products : Product];

    String storageBinName;
    Warehouse warehouse;
    Double maximumWeight;
    String shelvingSize;
    Long numberOfShelves;
    Long totalBins;
    String model;
    Double totalCapacity;
    Integer storageBinType; // 1 small, 2 large pallets   //http://www.actionwp.com/storage-bins (for more info.)
    //http://www.actionwp.com/spv-169.aspx (also)


    static constraints = {
        storageBinName(unique: true);
        warehouse(nullable: false);
        maximumWeight(nullable: false);
        totalCapacity(nullable: false);
        totalBins(nullable: false);
        numberOfShelves(nullable: false);
        shelvingSize(nullable: false);
    }
}
