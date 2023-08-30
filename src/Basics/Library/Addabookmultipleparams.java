package Library;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.naming.Name;

import static io.restassured.RestAssured.given;

public class Addabookmultipleparams
{
    @Test(dataProvider ="Addboo" )
    public void bookMultiParam(String isbn1,String aisle1)
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String res1=given().header("Content-Type","application/json").
                body(Inputs.addBookUsingParams(isbn1,aisle1)).
                when().post("Library/Addbook.php")
                .then().log().all().extract().response().asString();
        System.out.println(res1);



    }
@DataProvider(name = "Addboo")
    public Object[][] getData()
    {
       return new Object[][] {{"test2","123"},{"test3","876"},{"test3","8876"},{"test4","8654"}};
    }


}
