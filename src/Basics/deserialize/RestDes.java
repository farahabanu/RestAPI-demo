package deserialize;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RestDes {
    public static void main(String[] args) {


        PojoCla p1 = new PojoCla();
        p1.setAccuracy(50);
        p1.setName("my house");
        p1.setPhone_number("(+91) 983 893 3937");
        p1.setAddress("29, side layout, cohen 09");
        List<String> mylist=new ArrayList<String>();
        mylist.add("shop");
        mylist.add("shoe park");
        p1.setTypes(mylist);

        Location l1=new Location();
        l1.setLat(8.655);
        l1.setLongitude(80.5354);
        p1.setLocation(l1);

/*
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String res=given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
//        String res = given().header("Content-Type", "application/json").
                . body(p1).
                when().post("https://rahulshettyacademy.com/Library/Addbook.php")
                .then().statusCode(200).extract().response().asString();

        */

        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification res =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        RequestSpecification response=given().spec(req).body(p1);
        String re=response.when().post("/Library/Addbook.php")
                .then().spec(res).extract().response().asString();
        System.out.println(re);


    }
}
