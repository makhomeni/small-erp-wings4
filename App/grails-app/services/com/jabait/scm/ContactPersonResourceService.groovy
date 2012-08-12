package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class ContactPersonResourceService {

    def create(ContactPerson dto) {
        dto.save()
    }

    def read(def id) {
        def obj = ContactPerson.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(ContactPerson.class, id)
        }
        obj
    }

    def readAll() {
        ContactPerson.findAll()
    }

    def update(ContactPerson dto) {
        def obj = ContactPerson.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(ContactPerson.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = ContactPerson.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

