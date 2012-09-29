package com.jabait.scm

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.PUT
import javax.ws.rs.core.Response

import org.grails.jaxrs.provider.DomainObjectNotFoundException
import javax.ws.rs.Path
import javax.ws.rs.POST
import org.json.JSONObject
import com.jabait.hrm.Organization
import com.jabait.security.Address
import javax.ws.rs.PathParam

@Path("/api/warehouse")
class WarehouseResource {

    def warehouseResourceService
    def id
    @POST
    @Consumes(["application/json"])
    @Produces(["application/json"])
    Response createWareHouse(String wareHouse){
        println "wareHouse " + wareHouse
        JSONObject wareHouseObject = new JSONObject(wareHouse);

        Organization organization = Organization.get(Integer.parseInt(wareHouseObject.get("organization").toString()));
        String country = wareHouseObject.get("country").toString();
        String extendedAddress = wareHouseObject.get("extendedAddress").toString();//Street address 1 and Street address 2 two Text field
        String poBox = wareHouseObject.get("poBox").toString();
        String postalCode = wareHouseObject.get("postalCode").toString();
        String region = wareHouseObject.get("region").toString();
        String streetAddress = wareHouseObject.get("streetAddress").toString();

        Address address = new Address();
        address.country = country;
        address.extendedAddress = extendedAddress;
        address.poBox = poBox;
        address.postalCode = postalCode;
        address.region = region;
        address.streetAddress = streetAddress;

        Warehouse warehouse = new Warehouse();
        warehouse.organization = organization;
        warehouse.wareHouseName =  wareHouseObject.get("wareHouseName").toString();
        warehouse.address = address;

        created warehouse.save(flush: true);


    }

    @GET
    @Path("/{id}")
    Response read(@PathParam("id") Long id) {
        ok warehouseResourceService.read(id)
    }

    @GET
    Response readAll(){
        List<Warehouse> warehouses = warehouseResourceService.readAll();
        Map<String,Object> warehouseMap;
        List<Map<String,Object>> allWarehouses = new ArrayList<Map<String,Object>>();
        for (Warehouse warehouse: warehouses) {
            warehouseMap = new HashMap<String,Object>();
            warehouseMap.put("id", warehouse.id);
            warehouseMap.put("wareHouseName", warehouse.wareHouseName);
            warehouseMap.put("organization", warehouse.organization);
//            purchaseOrderMap.put("parentCategory", category?.parentCategory?.categoryName);

            allWarehouses.add(warehouseMap);
        }
        ok allWarehouses;

    }

    @PUT
    Response update(Warehouse dto) {
        dto.id = id
        ok warehouseResourceService.update(dto)
    }

    @DELETE
    void delete() {
        warehouseResourceService.delete(id)
    }

}

