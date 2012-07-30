package com.jabait.scm.inventory

import com.jabait.scm.Warehouse


//The storage bin is the smallest available unit of space in a warehouse
class StorageBin {

    static belongsTo = [Warehouse];

    StorageBin storageBin;

    static constraints = {
    }
}
