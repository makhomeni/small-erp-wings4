package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class PurchaseResourceService {

    def create(Purchase dto) {
        dto.save()
    }

    def read(def id) {
        def obj = Purchase.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Purchase.class, id)
        }
        obj
    }

    def readAll() {
        Purchase.findAll()
    }

    def update(Purchase dto) {
        def obj = Purchase.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Purchase.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = Purchase.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

