package com.jabait.scm.inventory

import com.jabait.hrm.Organization

class PriceList {

    Product product;
    Organization organization;
    String priceListVersion; //The most recent price list will be effective
    String currency;
    Double listPrice; //the maximum price
    Double standardPrice; //the average price
    Double limitPrice; //the minmum price
    Date createdOn;
    Date effectiveFrom;
    boolean active;

    static constraints = {
    }
}
