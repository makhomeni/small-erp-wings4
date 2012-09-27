package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class SalesResourceService {

    def create(Sales dto) {
        dto.save()
    }

    def read(def id) {
        def obj = Sales.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Sales.class, id)
        }
        obj
    }

    def readAll() {
        Sales.findAll()
    }

    def update(Sales dto) {
        def obj = Sales.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Sales.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = Sales.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

