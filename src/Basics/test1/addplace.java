package test1;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class addplace
{
    public static void main(String[] args)
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String res=given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json").
                body("{\n"
                        + "  \"location\": {\n"
                        + "    \"lat\": -38.383494,\n"
                        + "    \"lng\": 33.427362\n"
                        + "  },\n"
                        + "  \"accuracy\": 50,\n"
                        + "  \"name\": \"fal\",\n"
                        + "  \"phone_number\": \"(+91) 983 893 3937\",\n"
                        + "  \"address\": \"29, side layout, cohen 09\",\n"
                        + "  \"types\": [\n"
                        + "    \"shoe park\",\n"
                        + "    \"shop\"\n"
                        + "  ],\n"
                        + "  \"website\": \"http://google.com\",\n"
                        + "  \"language\": \"French-IN\"\n"
                        + "}\n"
                        + "").

                when().post("/maps/api/place/add/json").
                then().
                assertThat().statusCode(200).
//                body("status",equalTo("OK")).
//                headers("Server",equalTo("Apache/2.4.41 (Ubuntu)")).
                extract().response().asString();
                System.out.println(res);
//                JsonPath js=new JsonPath(res);
//                String placeId=js.getString("place_id");
//        System.out.println(placeId);
/*
//        update a place
        String add="Tarikere,karnataka";

        String responseUpdate=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
                body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+add+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").when().put("maps/api/place/update/json").
                then().statusCode(200).body("msg",equalTo("Address successfully updated"))
                .extract().response().asString();

        System.out.println(responseUpdate);
//        get place

        String getRes=given().queryParam("key","qaclick123")
                .header("Content-Type","application/json").queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath jget=new JsonPath(getRes);
       String address = jget.getString("address");
       boolean b=address.equalsIgnoreCase(add);
        System.out.println(b);

*/






    }
}
