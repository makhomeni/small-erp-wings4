package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class WarehouseResourceService {

    def create(Warehouse dto) {
        dto.save()
    }

    def read(def id) {
        def obj = Warehouse.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Warehouse.class, id)
        }
        obj
    }

    def readAll() {
        Warehouse.findAll()
    }

    def update(Warehouse dto) {
        def obj = Warehouse.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Warehouse.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = Warehouse.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

