package com.jabait.scm

import com.jabait.hrm.Employee


class WorkOrder extends JobOrder {

    static hasMany = [employees : Employee];

    static constraints = {
    }
}
