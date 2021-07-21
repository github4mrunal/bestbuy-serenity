package com.bestbuy.bestbuyinfo;

import com.bestbuy.model.CategoriesPojo;
import com.bestbuy.steps.CategoriesSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class CategoriesCRUDTest extends TestBase {

        static String name = "Glenfiddich" + TestUtils.getRandomValue();
        static String id  = "Whisky" + TestUtils.getRandomValue();
        static String categoriesId;

    @Steps
    CategoriesSteps categoriesSteps;
    @Title("This test will create a new categories and verify its generated")
    @Test

    public void test001() {

        categoriesId = categoriesSteps.createNewCategories(name, id).log().all().statusCode(201).extract().response()
                .body().jsonPath().getString("id");
        System.out.println("categories ID is" + categoriesId);
    }
    @Title("This test will get the categories information by ID")
    @Test

    public void test002() {
        categoriesSteps.getCategorieById(categoriesId).log().all().statusCode(200);

    }

    @Title("This test will update the categories information and verify the updated information")
    @Test

    public void test003(){

        name = name+"_Changed";

        categoriesSteps.updateCategories(categoriesId,name).statusCode(200);
        categoriesSteps.getCategorieById(categoriesId).body("name",equalTo(name));

    }
    @Title("This test will delete the categories information and verify the categories is deleted ")
    @Test

    public void test004(){
        categoriesSteps.deleteCategoriesById(categoriesId).statusCode(200);
        categoriesSteps.getCategorieById(categoriesId).statusCode(404);
    }


}
