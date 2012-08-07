package com.jabait.scm.inventory

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class InventoryRegisterResourceService {

    def create(InventoryRegister dto) {
        dto.save()
    }

    def read(def id) {
        def obj = InventoryRegister.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(InventoryRegister.class, id)
        }
        obj
    }

    def readAll() {
        InventoryRegister.findAll()
    }

    def update(InventoryRegister dto) {
        def obj = InventoryRegister.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(InventoryRegister.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = InventoryRegister.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

