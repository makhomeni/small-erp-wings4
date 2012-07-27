package com.jabait.scm

class SalesOrder extends JobOrder {

    Customer customer;

    static mapping = {
        table("sales_order")
    }

    static constraints = {
    }
}
