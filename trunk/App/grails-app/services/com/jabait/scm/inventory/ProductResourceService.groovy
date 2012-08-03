package com.jabait.scm.inventory

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class ProductResourceService {

    def create(Product dto) {
        dto.save()
    }

    def read(def id) {
        def obj = Product.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Product.class, id);
        }
        obj
    }

    def readAll() {
        if(Product.count() == 0){
            throw new DomainObjectNotFoundException(Product.class, 1);
        } else {
            Product.findAll()
        }

    }

    def update(Product dto) {
        def obj = Product.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Product.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = Product.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

