package com.jabait

class UploaderController {

    def index() {
        render(view: 'file_upload')
    }
    def jqueryFileUpload(){
        render(view: '../bootstrapFileUpload/mainupload')
    }
}
