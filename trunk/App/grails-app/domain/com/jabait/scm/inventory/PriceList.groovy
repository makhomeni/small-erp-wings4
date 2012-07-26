package com.jabait.scm.inventory

import com.jabait.hrm.Organization

class PriceList {

    Product product;
    Organization organization;
    String priceListVersion; //The most recent price list will be effective
    String currency;
    Double listPrice;
    Double standardPrice;
    Double limitPrice;
    Date createdOn;
    Date effectiveFrom;
    boolean active;

    static constraints = {
    }
}
