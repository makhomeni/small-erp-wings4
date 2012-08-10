package com.jabait.hrm.payroll

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class PaymentMethodResourceService {
    
    def create(PaymentMethod dto) {
        dto.save()
    }

    def read(def id) {
        def obj = PaymentMethod.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(PaymentMethod.class, id)
        }
        obj
    }
    
    def readAll() {
        PaymentMethod.findAll()
    }
    
    def update(PaymentMethod dto) {
        def obj = PaymentMethod.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(PaymentMethod.class, dto.id)
        }
        obj.properties = dto.properties 
        obj
    }
    
    void delete(def id) {
        def obj = PaymentMethod.get(id)
        if (obj) { 
            obj.delete()
        }
    }
    
}

