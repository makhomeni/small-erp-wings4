package com.jabait.scm.inventory

import com.jabait.util.Files

class ProductImage extends Files {

    static mapping = {
        discriminator(value: "product_image")
    }

    static constraints = {
    }
}
