package com.jabait.security

import com.jabait.coresecurity.AuthenticationToken

class AuthController {

    def securityService;

    def index= { }

    def login={
        def targetUri = params.targetUri;
        try{
            if (params.targetUri != null){
                response.setHeader(params.targetUri, "")
            }
        }  catch (Exception ex){

        }


        render(view: "login",  model: [targetUri: targetUri]);
    }

    def authenticateUser={
        AuthenticationToken authToken = securityService.doAuthentication(params);
        def user = authToken.principal;


        if (user){
            session.user = user;
            if(!session.user){
                redirect(action: "login")
                return false;
            } else {
                session.credentials = authToken.credential;
                session.grantedAuthority = authToken.grantedAuthority;

                session.modules = securityService.loadControllers();
                System.out.println("grantedAuthority >>> " + session.grantedAuthority);

                if (session.requestedController != null && session.requestedAction != null){
                    redirect(controller:session.requestedController, action:session.requestedAction);
                }else{
                    redirect(controller:"application", action: "index");
                }

                return true;
            }

        } else {
            flash.message = "Bad Credentials!!!";
            redirect(action: "login");
        }
    }
}
