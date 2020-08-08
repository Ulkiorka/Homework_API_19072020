import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;

public class Steps {
    @Step("Verifying that users have the same avatar file names")
    public static void usersHasSamePicturesNames(){
        Response response = given()
                .when()
                .get("api/users?page=2");
        List<String> avatars = response.jsonPath().getList("data.avatar");
        List<String> fileNames = new ArrayList<>();

        for (String parts: avatars){
            int count = parts.split("/").length;
            fileNames.add(parts.split("/")[count-1]);
        }

        for (int i = 1; i < fileNames.size(); i++)
            Assert.assertEquals(fileNames.get(0), fileNames.get(i), "names of avatars are different.");
    }

    @Step("Checking the successful registration")
    public static void successfulRegistrationTest(Map<String, String> data){
        Response response = given()
                .body(data)
                .when()
                .post("api/register")
                .then()
                .extract()
                .response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("id").toString(), "4");
        Assert.assertEquals(jsonResponse.get("token").toString(), "QpwL5tke4Pnpja7X4");
    }

    @Step("Checking successful login")
    public static void successfulLoginTest(Map<String, String> data){
        Response response = given()
                .body(data)
                .when()
                .post("api/login")
                .then()
                .extract()
                .response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("token").toString(), "QpwL5tke4Pnpja7X4", "token is not valid");
    }

    @Step("Checking login without password")
    public static void loginWithoutPasswordTest(Map<String, String> data) {
        Response response = given()
                .body(data)
                .when()
                .post("api/login")
                .then()
                .log().all()
                .statusCode(400)
                .extract()
                .response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("error").toString(), "Missing password", "Wrong error message");
    }

    @Step("Checking the sorting of response data by year")
    public static void sortedDataTest(){
        Response response = given()
                .when()
                .get("api/unknown")
                .then()
                .extract()
                .response();
        List<Integer> dataYears = response.jsonPath().getList("data.year");
        List<Integer> sortedList = new ArrayList<>();
        sortedList.addAll(dataYears);
        Collections.sort(sortedList);
        for (int i = 0; i < dataYears.size(); i++)
            Assert.assertEquals(dataYears.get(i), (sortedList.get(i)));
    }
}
