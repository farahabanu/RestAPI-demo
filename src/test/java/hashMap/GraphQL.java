package hashMap;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class GraphQL
{
    public static void main(String[] args)
    {
        String queryResponse=given().log().all().header("Content-Type","application/json").
                body("{\"query\":\"query($locationId :Int!)\\n{\\n  location(locationId:$locationId){\\n    id\\n    name\\n    dimension\\n    type\\n  }\\n  \\n}\",\"variables\":{\"locationId\":240}}")
                .when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
        System.out.println(queryResponse);
        JsonPath js=new JsonPath(queryResponse);
       String id= js.getString("data.location.id");
        System.out.println("ID is "+id);


//  In the graphql the body will not be in the json format, so it is good to copy the payload from graphql schema running and in network tab and copy the payload
        String mutationResponse=given().log().all().header("Content-Type","application/json").
                body("{\"query\":\"mutation\\n{\\n  createLocation(location:{name:\\\"bng\\\",type:\\\"south\\\",dimension:\\\"235\\\"})\\n  {\\n    id\\n    \\n  }\\n}\",\"variables\":null}")
                .when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
        System.out.println(mutationResponse);



    }
}
