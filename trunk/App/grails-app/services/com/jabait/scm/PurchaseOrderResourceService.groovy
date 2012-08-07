package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class PurchaseOrderResourceService {

    def create(PurchaseOrder dto) {
        dto.save()
    }

    def read(def id) {
        def obj = PurchaseOrder.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(PurchaseOrder.class, id)
        }
        obj
    }

    def readAll() {
        PurchaseOrder.findAll()
    }

    def update(PurchaseOrder dto) {
        def obj = PurchaseOrder.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(PurchaseOrder.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = PurchaseOrder.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

