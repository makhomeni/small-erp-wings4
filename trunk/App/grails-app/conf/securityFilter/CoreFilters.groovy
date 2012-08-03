package com.jabait.security

class CoreFilters {

    def filters = {

        forSecurity(controller:"*", action:"*", controllerExclude: "auth|jaxrs") {
            before = {

                session.requestedController = params.controller
                session.requestedAction = params.action

                if(!session.user) {
                    redirect(controller:  "auth", action: "login");
                    return false;
                }


            }
            after = { Map model ->
                try{
                    if(session?.user){
                        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
                        response.setHeader("Pragma", "no-cache");
                        response.setDateHeader("Expires", 0);
                    }
                } catch(Exception ex){

                }

                //redirect(controller:"auth", action:"login")
            }
            afterView = { Exception e ->

            }
        }

        forRoot(controller:"auth", action:"login") {
            before = {

                if(session.user) {
                    redirect(controller:  "application", action: "index");
                    return false;
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
