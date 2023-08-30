package E2ETesting;

import com.google.gson.JsonParser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import javax.swing.text.StringContent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class Ecom {
    public static void main(String[] args)
    {
//        Login Api
        LoginRequest Lr=new LoginRequest();
        Lr.setUserEmail("farahabanu5@gmail.com");
        Lr.setUserPassword("Farru@123");

        RequestSpecification R = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200).build();
        RequestSpecification reqLogin = given().spec(R).body(Lr);
     LoginResponse lr=reqLogin.when().post("/api/ecom/auth/login").then().assertThat().spec(response).extract().response().as(LoginResponse.class);
        System.out.println(lr.getToken());

// Add a product
        RequestSpecification addRequest=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", lr.getToken()).build();
        RequestSpecification AddR=given().spec(addRequest)
                .param("productName","qwerty")
                .param("productAddedBy",lr.getUserId())
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","1500")
                .param("productDescription","Addias Originals")
                .param("productFor","Women")
               .multiPart("productImage",new File("//Users//Faraha179271//Downloads//tshirt.png"));
        /*AddProductResponse AR1=AddR.when().post("/api/ecom/product/add-product").then().log().all().extract().response().as(AddProductResponse.class);
        System.out.println(AR1.getProductId());

         */
        String r2=AddR.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
        System.out.println(r2);
        JsonPath js=new JsonPath(r2);
        String pid=js.getString("productId");
        System.out.println(pid);


//        Create Order
        Orders od=new Orders();
        od.setCountry("India");
        od.setProductOrderedId(pid);
        List<Orders> odl=new ArrayList<Orders>();
        odl.add(od);
        CreateOrderRequestBody cor=new CreateOrderRequestBody();
        cor.setOrders(odl);



        RequestSpecification createOrderRequest=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", lr.getToken()).setContentType(ContentType.JSON).build();
        RequestSpecification createOrder1= given().spec(createOrderRequest).body(cor);
        String COResponse=createOrder1.when().post("/api/ecom/order/create-order").then().extract().response().asString();
        System.out.println(COResponse);
        JsonPath js1=new JsonPath(COResponse);
        String productOrderId=js1.getString("productId");

//        View order
        RequestSpecification viewOrder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", lr.getToken()).setContentType(ContentType.JSON).build();
        String viewOrderResponse=given().spec(viewOrder).queryParam("id",productOrderId).when().
                get("https://rahulshettyacademy.com/api/ecom/order/get-orders-details").then().extract().response().toString();
        System.out.println(viewOrderResponse);


//      Delete a product
        RequestSpecification deleteRequest=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", lr.getToken()).build();
        String deleteRes=given().spec(deleteRequest).pathParam("productId",pid).when().delete("api/ecom/product/delete-product/{productId}").then().extract().response().asString();
        System.out.println(deleteRes);

    }
}
