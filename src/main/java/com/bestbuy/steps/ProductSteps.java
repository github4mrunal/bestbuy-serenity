package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductsPojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {
    @Step("Creating Products with name : {0} , type : {1} , price : {2} , shipping : {3} , upc : {4} , description : {5}, manufacturer{6},model{7}, url {8},image {9}")

    public ValidatableResponse createNewProduct(String name, String type,int price,int shipping,String upc, String description, String manufacturer,String model,String url, String image) {

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);



        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(productsPojo)
                .post(EndPoints.POST_A_PRODUCT)
                .then();


    }

    @Step("Getting the Product information with productId : {0}")

    public ValidatableResponse getProductById(int productId) {
        return SerenityRest.rest()
                .given()
                .log().all()
                .pathParam("id", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }


    @Step("Updating Product information with productId :  name : {0} , type : {1} , price : {2} , shipping : {3} , upc : {4} , description : {5}, manufacturer{6},model{7}, url {8},image {9}")

    public ValidatableResponse updateProduct(int productId, String name, String type, int price,int shipping,String upc, String description,String manufacturer,String model,String url,String image) {

        ProductsPojo productsPojo = new ProductsPojo();
//        productsPojo.setName(name);
//        productsPojo.setType(type);
//        productsPojo.setPrice(price);
//        productsPojo.setShipping(shipping);
//        productsPojo.setUpc(upc);
//        productsPojo.setDescription(description);
//        productsPojo.setManufacturer(manufacturer);
//        productsPojo.setModel(model);
//        productsPojo.setUrl(url);
//        productsPojo.setImage(image);

        return SerenityRest.rest().given()
                .log().all()
                .pathParam("id", productId)
                .when()
                .body(productsPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting the Product information with productId : {0} ")

    public ValidatableResponse deleteProductById(int productId) {
        return SerenityRest.rest()
                .given()
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();


    }

    @Step("Getting all product Information ")

    public ValidatableResponse getAllProduct() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();

    }
}



