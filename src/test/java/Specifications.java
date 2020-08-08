import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification reqSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType("application/json")
                .build();
    }

    public static ResponseSpecification responseSpecification(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static void installSpec(RequestSpecification reqSpec){
        RestAssured.requestSpecification = reqSpec;
    }

    public static void installSpec(RequestSpecification reqSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = reqSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void clearSpecifications(){
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
