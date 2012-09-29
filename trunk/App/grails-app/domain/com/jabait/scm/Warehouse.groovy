package com.jabait.scm

import com.jabait.scm.inventory.StorageBin
import com.jabait.hrm.Organization
import com.jabait.security.Address


class Warehouse {
    
    Organization organization;
    String wareHouseName;
    String address;

    static hasMany = [storageBins : StorageBin]

    static constraints = {
    }

    public static void initialize(){
        if(Warehouse.getCount() == 0){
            Warehouse warehouse = new Warehouse();
            warehouse.organization = Organization.get(1);
            warehouse.wareHouseName = "Mohakhali Warehouse";
            warehouse.address = "Mohakhali DOHS";
            warehouse.save();
        }
    }
}
