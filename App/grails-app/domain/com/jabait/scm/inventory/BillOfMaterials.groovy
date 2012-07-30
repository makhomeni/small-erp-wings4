package com.jabait.scm.inventory

//Bill of Materials is a list of the
// raw materials,
// sub-assemblies,
// intermediate assemblies,
// sub-components,
// components,
// parts and the quantities of each needed to manufacture an end product.
// No physical dimension is described in a BOM
class BillOfMaterials {

    Product billOfMaterialProduct;
    Double quantity;
    String description;
    Boolean active;

    static constraints = {
    }
}
