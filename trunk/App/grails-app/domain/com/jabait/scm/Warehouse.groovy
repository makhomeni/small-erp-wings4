package com.jabait.scm

import com.jabait.scm.inventory.StorageBin
import com.jabait.hrm.Organization
import com.jabait.security.Address


class Warehouse {
    
    Organization organization;
    Address address;

    static hasMany = [storageBins : StorageBin]

    static constraints = {
    }
}
