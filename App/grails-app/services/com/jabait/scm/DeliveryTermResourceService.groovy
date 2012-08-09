package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class DeliveryTermResourceService {
    
    def create(DeliveryTerm dto) {
        dto.save()
    }

    def read(def id) {
        def obj = DeliveryTerm.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(DeliveryTerm.class, id)
        }
        obj
    }
    
    def readAll() {
        DeliveryTerm.findAll()
    }
    
    def update(DeliveryTerm dto) {
        def obj = DeliveryTerm.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(DeliveryTerm.class, dto.id)
        }
        obj.properties = dto.properties 
        obj
    }
    
    void delete(def id) {
        def obj = DeliveryTerm.get(id)
        if (obj) { 
            obj.delete()
        }
    }
    
}

