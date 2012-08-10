package com.jabait.accounting

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class PaymentTermResourceService {
    
    def create(PaymentTerm dto) {
        dto.save()
    }

    def read(def id) {
        def obj = PaymentTerm.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(PaymentTerm.class, id)
        }
        obj
    }
    
    def readAll() {
        PaymentTerm.findAll()
    }
    
    def update(PaymentTerm dto) {
        def obj = PaymentTerm.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(PaymentTerm.class, dto.id)
        }
        obj.properties = dto.properties 
        obj
    }
    
    void delete(def id) {
        def obj = PaymentTerm.get(id)
        if (obj) { 
            obj.delete()
        }
    }
    
}

