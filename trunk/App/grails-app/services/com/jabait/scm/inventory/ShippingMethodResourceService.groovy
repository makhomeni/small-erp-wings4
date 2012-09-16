package com.jabait.scm.inventory

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class ShippingMethodResourceService {

    def create(ShippingMethod dto) {
        dto.save()
    }

    def read(def id) {
        def obj = ShippingMethod.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(ShippingMethod.class, id)
        }
        obj
    }

    def readAll() {
        ShippingMethod.findAll()
    }

    def update(ShippingMethod dto) {
        def obj = ShippingMethod.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(ShippingMethod.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = ShippingMethod.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

