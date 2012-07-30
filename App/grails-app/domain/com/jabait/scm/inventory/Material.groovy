package com.jabait.scm.inventory

class Material {
    
    String materialName;
    String materialDescription;

    static hasMany = [materialSpecifications : MaterialSpecification]

    static constraints = {
    }
}
