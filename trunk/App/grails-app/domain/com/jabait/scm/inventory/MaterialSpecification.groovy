package com.jabait.scm.inventory

class MaterialSpecification {
    
    String specificationKey;
    String specificationValue;
    String specificationHelpText;

    static constraints = {
        specificationKey(nullable: false);
        specificationValue(nullable: false);
        specificationHelpText(nullable: true);
    }
}
