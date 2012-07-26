package com.jabait.security

class SecurityFilters {

    def securityService;
    def grailsApplication;
    def grailsAttributes;

    def filters = {
        saveFilter(controller: "*", action: "save*", actionExclude: "*Profile", controllerExclude: "auth"){
            before = {
                if(!securityService.checkAuthorization(session,params.controller,params.action)){
                    redirect(controller: "security", action: "unauthorized");
                    return false;
                }
            }
        }

        createFilter(controller: "*", action: "create*", controllerExclude: "auth"){
            before = {
                if(!securityService.checkAuthorization(session,params.controller,params.action)){
                    redirect(controller: "security", action: "unauthorized");
                    return false;
                }
            }
        }

        readFilter(controller: "*", action: "*List", controllerExclude: "auth"){
            before = {
                System.out.println("read  " + params.action);
                if(!securityService.checkAuthorization(session,params.controller,params.action)){
                    redirect(controller: "security", action: "unauthorized");
                    return false;
                }
            }
        }

        updateFilter(controller: "*", action: "update*", controllerExclude: "auth"){
            before = {
                if(!securityService.checkAuthorization(session,params.controller,params.action)){
                    redirect(controller: "security", action: "unauthorized");
                    return false;
                }
            }
        }

        editFilter(controller: "*", action: "edit*", actionExclude:"*Profile", controllerExclude: "auth"){
            before = {
                if(!securityService.checkAuthorization(session,params.controller,params.action)){
                    redirect(controller: "security", action: "unauthorized");
                    return false;
                }
            }
        }

        deleteFilter(controller: "*", action: "delete*", controllerExclude: "auth"){
            before = {
                if(!securityService.checkAuthorization(session,params.controller,params.action)){
                    redirect(controller: "security", action: "unauthorized");
                    return false;
                }
            }
        }
    }
}
