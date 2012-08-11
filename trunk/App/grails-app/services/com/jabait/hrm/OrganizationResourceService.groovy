package com.jabait.hrm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class OrganizationResourceService {

    def create(Organization dto) {
        dto.save()
    }

    def read(def id) {
        def obj = Organization.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Organization.class, id)
        }
        obj
    }

    def readAll() {
        Organization.findAll()
    }

    def update(Organization dto) {
        def obj = Organization.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Organization.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = Organization.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

