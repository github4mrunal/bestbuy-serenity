package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductsPojo;
import com.bestbuy.steps.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {
static String name = "PS4" + TestUtils.getRandomValue();
static String type = "Gaming" + TestUtils.getRandomValue();
static int price = Integer.parseInt("499" + TestUtils.getRandomValue());
static int shipping = Integer.parseInt("5" + TestUtils.getRandomValue());
static String upc = "NextDay" + TestUtils.getRandomValue();
static String description = "Gaming Console" + TestUtils.getRandomValue();
static String manufacturer = "Sony" + TestUtils.getRandomValue();
static String model = "V4" + TestUtils.getRandomValue();
static String url = "sony@gmail.com" + TestUtils.getRandomValue();
static String image = "SONY PS4" + TestUtils.getRandomValue();
static int productId;

    @Steps
    ProductSteps productsSteps;

    @Title("This test will create a new products and verify its generated")
    @Test

    public void test001(){

        productId = productsSteps.createNewProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image).log().all().statusCode(201).extract().response()
                .body().jsonPath().getInt("id");
        System.out.println("product id is : " + productId);

    }

    @Title("This test will get the product information by ID")
    @Test

    public void test002(){
        productId = productsSteps.getAllProduct().extract().path("data[1].id");
        productsSteps.getProductById(productId).statusCode(200);

    }

    @Title("This test will update the product information and verify the updated information")
    @Test

    public void test003(){
        name = name+"_Changed";
        price = 89;
        upc = upc + "_added";
        productsSteps.updateProduct(productId,name,type,price,shipping,upc,description,manufacturer,model,url,image).statusCode(200);
       // productsSteps.getProductById(productId).body("name",equalTo(name));


    }
    @Title("This test will delete the product information and verify the product is deleted ")
    @Test

    public void test004(){
        productsSteps.deleteProductById(productId).statusCode(200);
        productsSteps.getProductById(productId).statusCode(404);
    }

}

