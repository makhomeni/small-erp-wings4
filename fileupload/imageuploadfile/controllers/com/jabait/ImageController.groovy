package com.jabait

import org.springframework.http.HttpStatus
import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import org.imgscalr.Scalr
import javax.imageio.ImageIO

class ImageController {
    def upload() {
        switch(request.method){
            case "GET":
                def results = []
                Image.findAll().each { Image picture ->
                    results << [
                            name: picture.originalFilename,
                            size: picture.fileSize,
                            url: createLink(controller:'image', action:'picture', id: picture.id),
                            thumbnail_url: createLink(controller:'image', action:'thumbnail', id: picture.id),
                            delete_url: createLink(controller:'image', action:'delete', id: picture.id),
                            delete_type: "DELETE"
                    ]
                }
                render results as JSON
                break;
            case "POST":
                def results = []
                if (request instanceof MultipartHttpServletRequest){
                    for(filename in request.getFileNames()){
                        MultipartFile file = request.getFile(filename)

                        String newFilenameBase = UUID.randomUUID().toString()
                        String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
                        String newFilename = newFilenameBase + originalFileExtension
                        println  ("file"+ grailsApplication.config.file.upload.directory);
                        String storageDirectory = "E:"


                        File newFile = new File("$storageDirectory/$newFilename")
                        file.transferTo(newFile)

                        BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
                        String thumbnailFilename = newFilenameBase + '-thumbnail.png'
                        File thumbnailFile = new File("$storageDirectory/$thumbnailFilename")
                        ImageIO.write(thumbnail, 'png', thumbnailFile)

                        Image picture = new Image(
                                originalFilename: file.originalFilename,
                                thumbnailFilename: thumbnailFilename,
                                newFilename: newFilename,
                                fileSize: file.size
                        ).save()

                        results << [
                                name: picture.originalFilename,
                                size: picture.fileSize,
                                url: createLink(controller:'image', action:'picture', id: picture.id),
                                thumbnail_url: createLink(controller:'image', action:'thumbnail', id: picture.id),
                                delete_url: createLink(controller:'image', action:'delete', id: picture.id),
                                delete_type: "DELETE"
                        ]
                    }
                }

                render results as JSON
                break;
            default: render status: HttpStatus.METHOD_NOT_ALLOWED.value()
        }
    }

    def picture(){
        def pic = Image.get(params.id)
        File picFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.newFilename}")
        response.contentType = 'image/jpeg'
        response.outputStream << new FileInputStream(picFile)
        response.outputStream.flush()
    }

    def thumbnail(){
        def pic = Image.get(params.id)
        File picFile = new File("E:/${pic.thumbnailFilename}")
        response.contentType = 'image/png'
        response.outputStream << new FileInputStream(picFile)
        response.outputStream.flush()
    }

    def delete(){
        def pic = Image.get(params.id)
        File picFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.newFilename}")
        picFile.delete()
        File thumbnailFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.thumbnailFilename}")
        thumbnailFile.delete()
        pic.delete()

        def result = [success: true]
        render result as JSON
    }
}