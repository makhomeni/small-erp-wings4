package com.jabait.app

class ApplicationController {

    def securityService;

    def index() {
        println("in index");
        render(view: "index", model: [title : "ERP System - Ocean"]);
    }
    def save(){

    }
}
