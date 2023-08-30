package hashMap;

import ExcelIntegration.DataDriven;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HashMapConversion {
    public static void main(String[] args) throws IOException {
        DataDriven d=new DataDriven();
        ArrayList list=d.getData("testdata","testcase","RestAddBook");
        Map<String,Object> maps=new HashMap<>();
        maps.put("name",list.get(1));
        maps.put("isbn",list.get(2));
        maps.put("aisle",list.get(3));
        maps.put("author",list.get(4));

/* using hashmap key and value pair


        Map<String,Object> maps=new HashMap<>();
        maps.put("name","RestApi");
        maps.put("isbn","2543far");
        maps.put("aisle","87");
        maps.put("author","Faraha");



     Convert the hastmap for the nested Array
        Map<String,Object> maps2=new HashMap<>();
        maps2.put("lat","34.98");
        maps2.put("lng","44.98");
        maps.put("location",maps2);

 */



        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String res = given().header("Content-Type", "application/json").body(maps).
                when().post("Library/Addbook.php")
                .then().statusCode(200).extract().response().asString();
        System.out.println(res);
        JsonPath js = new JsonPath(res);
        String id = js.get
                ("ID");

        System.out.println(id);

    }
}
