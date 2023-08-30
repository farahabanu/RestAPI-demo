package Library;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;



public class AddABook
{

//    Add a book
    public void book()
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String res=given().header("Content-Type","application/json").body(Inputs.addABook1()).
                when().post("Library/Addbook.php")
                .then().statusCode(200).extract().response().asString();
        System.out.println(res);
        JsonPath js=new JsonPath(res);
        String id=js.get
        ("ID");

        System.out.println(id);
    }

//    Add a book using parameters
    @Test
public void bookparam()
{
    RestAssured.baseURI="https://rahulshettyacademy.com";;
    String res1=given().header("Content-Type","application/json").
            body(Inputs.addBookUsingParams("test1","12442")).
            when().post("Library/Addbook.php")
            .then().log().all().extract().response().asString();
    System.out.println(res1);
    JsonPath js=new JsonPath(res1);
    String id=js.get("ID");

    System.out.println(id);


}

//public void getCode()
//{
//    WebDriverManager.chromedriver().setup();
//
//    WebDriver driver=new ChromeDriver();
//driver.get("");
//
//}


}




