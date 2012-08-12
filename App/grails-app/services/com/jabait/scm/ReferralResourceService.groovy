package com.jabait.scm

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class ReferralResourceService {

    def create(Referral dto) {
        dto.save()
    }

    def read(def id) {
        def obj = Referral.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Referral.class, id)
        }
        obj
    }

    def readAll() {
        Referral.findAll()
    }

    def update(Referral dto) {
        def obj = Referral.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Referral.class, dto.id)
        }
        obj.properties = dto.properties
        obj
    }

    void delete(def id) {
        def obj = Referral.get(id)
        if (obj) {
            obj.delete()
        }
    }

}

