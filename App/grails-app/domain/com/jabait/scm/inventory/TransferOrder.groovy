package com.jabait.scm.inventory

import com.jabait.scm.JobOrder
import com.jabait.scm.Warehouse
import com.jabait.util.Carrier
import com.jabait.util.MovementType

class TransferOrder extends JobOrder {

    Warehouse warehouse;
    Integer productQuantity;
    Carrier carrier;
    MovementType movementType;

    static constraints = {
    }
}
