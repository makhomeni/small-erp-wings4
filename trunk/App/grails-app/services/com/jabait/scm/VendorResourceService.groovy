package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class VendorResourceService {

    def create(Vendor dto) {
        dto.save()
    }

    def read(def id) {
        def obj = Vendor.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Vendor.class, id)
        }
        obj
    }

    def readAll() {
        Vendor.findAll()
    }

    def update(Vendor dto) {
        def obj = Vendor.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Vendor.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = Vendor.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

