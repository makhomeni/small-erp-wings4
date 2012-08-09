package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class SalesOrderResourceService {

    def create(SalesOrder dto) {
        dto.save()
    }

    def read(def id) {
        def obj = SalesOrder.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(SalesOrder.class, id)
        }
        obj
    }

    def readAll() {
        SalesOrder.findAll()
    }

    def update(SalesOrder dto) {
        def obj = SalesOrder.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(SalesOrder.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = SalesOrder.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

