package com.jabait.scm

class ScmController {

    def home(){
        render(view: "scm_home", model: [type: "Supply-Chain Management"]);
    }
}
