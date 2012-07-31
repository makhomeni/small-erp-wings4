package com.jabait.scm.inventory

import grails.converters.JSON

import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import org.imgscalr.Scalr
import javax.imageio.ImageIO
import com.jabait.scm.Vendor

class InventoryController {

    def inventoryService;

    def home(){
        render(view: "/scm/inventory_home", model:[type: "Invetory Home"]);
    }

    def createCategory(){
        render(view: "/scm/category_create", model: [type: "Category Create"]);
    }

    def saveCategory(){
        flash.message = "Category Created " + inventoryService.saveCategory(params) ? "successfully" : "failed";
        redirect(action: "createCategory");
    }

    def categoryList(){
        render(view: "/scm/category_list", model: [type: "Category List"]);
    }

    def categoryJsonData(){
        List categories = inventoryService.findAllCategories();
        int max = 10;
        int totalCount = Category.count();


        if (categories.size() < 10) {
            max = categories.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([categories : categories.asList().subList(start, start + limit > categories.size() ?
            categories.size() : start + limit), totalCount:totalCount] as JSON);
    }

    def deleteCategory(){
        flash.message = "Category deleted " + inventoryService.deleteCategory(params) ? "successfully" : "failed";
        redirect(action: "categoryList");
    }

    def createProduct(){
        render(view: "/scm/product_create", model:[type: "Product Create"]);
    }

    def saveProduct(){
       redirect(action: "createProduct");
    }

    def productList(){
        render(view: "/scm/product_list", model: [type: "Product List"]);
    }

    def productJsonData(){
        List products = inventoryService.findAllProducts();
        int max = 10;
        int totalCount = Product.count();


        if (products.size() < 10) {
            max = products.size()
        }

        int start = (params.start != null) ? Integer.parseInt(params.start) : 0;
        int limit = (params.limit != null) ? Integer.parseInt(params.limit) : 10;

        render([products : products.asList().subList(start, start + limit > products.size() ?
            products.size() : start + limit), totalCount:totalCount] as JSON);
    }

    def deleteProduct(){
        flash.message = "Product Deleted " + inventoryService.deleteProduct(params) ? "successfully" : "failed";
        redirect(action: "productList");
    }

    def editProduct(){
        render(view: "/scm/", model: [product:inventoryService.findProductById(params.id), type: "Edit Product"]);
    }

    def updateProduct(){
        flash.message = "Product Updated " + inventoryService.updateProduct(params) ? "successfully" : "failed";
        redirect(action: "productList");
    }
    
    def materialList(){
        render(view: "");
    }

    def materialJsonData(){

    }
    
    def createMaterial(){
        render(view:  "");
    }

    def saveMaterial(){
        redirect(action: "createMaterial")
    }
    
    def deleteMaterial(){
        redirect(view: "materialList")
    }

    def upload() {
        println "request method " + request.method
        switch(request.method){
            case "GET":
                println "GET ..."
                def results = []
                ProductImage.findAll().each { ProductImage picture ->
                    results << [
                            name: picture.generatedName,
                            size: picture.extension,
                            url: createLink(controller:'image', action:'picture', id: picture.id),
                            thumbnail_url: createLink(controller:'image', action:'thumbnail', id: picture.id),
                            delete_url: createLink(controller:'image', action:'delete', id: picture.id),
                            delete_type: "DELETE"
                    ]
                }
                render results as JSON
                break;
            case "POST":
                println "POST ..."
                def results = []
                if (request instanceof MultipartHttpServletRequest){
                    for(filename in request.getFileNames()){
                        MultipartFile file = request.getFile(filename)

                        String newFilenameBase = UUID.randomUUID().toString()
                        String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
                        String newFilename = newFilenameBase + originalFileExtension
                        String storageDirectory = grailsApplication.config.file.upload.directory?:'/tmp'

                        File newFile = new File("$storageDirectory/$newFilename")
                        file.transferTo(newFile)

                        BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
                        String thumbnailFilename = newFilenameBase + '-thumbnail.png'
                        File thumbnailFile = new File("$storageDirectory/$thumbnailFilename")
                        ImageIO.write(thumbnail, 'png', thumbnailFile)

                        ProductImage picture = new ProductImage(
                                originalFilename: file.originalFilename,
                                thumbnailFilename: thumbnailFilename,
                                newFilename: newFilename,
                                fileSize: file.size
                        ).save()

                        results << [
                                name: picture.generatedName,
                                size: picture.addedDate,
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
        def pic = ProductImage.get(params.id)
        File picFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.newFilename}")
        response.contentType = 'image/jpeg'
        response.outputStream << new FileInputStream(picFile)
        response.outputStream.flush()
    }

    def thumbnail(){
        def pic = ProductImage.get(params.id)
        File picFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.thumbnailFilename}")
        response.contentType = 'image/png'
        response.outputStream << new FileInputStream(picFile)
        response.outputStream.flush()
    }

    def delete(){
        def pic = ProductImage.get(params.id)
        File picFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.newFilename}")
        picFile.delete()
        File thumbnailFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.thumbnailFilename}")
        thumbnailFile.delete()
        pic.delete()

        def result = [success: true]
        render result as JSON
    }
}
