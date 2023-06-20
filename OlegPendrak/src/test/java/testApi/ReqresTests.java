package testApi;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTests {

    private static final String URL ="https://reqres.in";

    @Test
    public void checkAvatarAndGetId(){
        List<UserData> userList = given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL+"/api/users?page=2")
                .then()
                .extract().jsonPath().getList("data",UserData.class)
                .stream().collect(Collectors.toList());
        userList.forEach(el-> Assert.assertTrue(el.getAvatar().contains(el.getId().toString())));
        Assert.assertTrue(userList.stream().allMatch(el->el.getEmail().endsWith("reqres.in")));
        int i=0;
    }
    @Test
    public void checkSussesRegister(){
        int id=4;
        String token = "QpwL5tke4Pnpja7X4";
        RequestSusses request = new RequestSusses("eve.holt@reqres.in","pistol");
         ResponceSusses response=given().contentType(ContentType.JSON)
                .when()
                .body(request)
                .post(URL+"/api/register")
                .then()
                .extract().body().as(ResponceSusses.class);
         Assert.assertEquals(id,response.getId());
         Assert.assertEquals(token,response.getToken());
         int i=0;
    }
    @Test
    public void checkResurseDataYearIsSorted(){
        List<ResurseData> dataList = given().contentType(ContentType.JSON)
                .when()
                .get(URL+"/api/unknown")
                .then()
                .extract().jsonPath().getList("data",ResurseData.class);
        List<Integer> year=dataList.stream().map(el->el.getYear()).collect(Collectors.toList());
        List<Integer> sortedYear = year.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(year,sortedYear);
        int i=0;
    }
}
