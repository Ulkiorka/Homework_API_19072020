import org.testng.annotations.Test;

import java.util.*;

public class ApiTests {

    @Test
    public void usersHasSameAvatarsNames(){
        Specifications.installSpec(Specifications.reqSpec(),Specifications.responseSpecification());
        Steps.usersHasSamePicturesNames();
        Specifications.clearSpecifications();
    }

    @Test
    public void successfulRegistrationTest(){
        Specifications.installSpec(Specifications.reqSpec());
        Map<String, String> data = new HashMap<>();
        data.put("email","eve.holt@reqres.in");
        data.put("password","pistol");
        Steps.successfulRegistrationTest(data);
        Specifications.clearSpecifications();
    }

    @Test
    public void successfulLoginTest(){
        Specifications.installSpec(Specifications.reqSpec());
        Map<String, String> data = new HashMap<>();
        data.put("email","eve.holt@reqres.in");
        data.put("password","cityslicka");
        Steps.successfulLoginTest(data);
        Specifications.clearSpecifications();
    }

    @Test
    public void unsuccessfulLoginTest(){
        Specifications.installSpec(Specifications.reqSpec());
        Map<String, String> data = new HashMap<>();
        data.put("email","eve.holt@reqres.in");
        Steps.loginWithoutPasswordTest(data);

        Specifications.clearSpecifications();
    }

    @Test
    public void sortedDataTest(){
        Specifications.installSpec(Specifications.reqSpec(), Specifications.responseSpecification());
        Steps.sortedDataTest();
        Specifications.clearSpecifications();
    }
}
