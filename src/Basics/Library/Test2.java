package Library;


import PojoClasses.GetResponse;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class Test2 {


    public static void main(String[] args) throws InterruptedException
    {
//
//
//        WebDriverManager.chromedriver().setup();
//        WebDriver dr = new ChromeDriver();
//        dr.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//        dr.findElement(By.id("identifierId")).sendKeys("farahabanu5@gmail.com");
//        dr.findElement(By.xpath("//span[text()='Next']")).click();
//        dr.findElement(By.name("password")).sendKeys("Farru@123");
//        dr.findElement(By.xpath("//span[text()='Next']")).click();
//        Thread.sleep(2000);
//





            String res = given().urlEncodingEnabled(false).
                    queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                    .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                    .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                    .queryParams("grant_type", "authorization_code")
                    .queryParams("code",
                            "4%2F0Adeu5BUndcTm6I17IiqQBHGLTyqqUN4cVGxO4AIMn07VhChSqZuvPARvUzcrgg17Hb3hfA")
                    .when().log().all()
                    .post("https://www.googleapis.com/oauth2/v4/token").asString();
        System.out.println(res);
        JsonPath js=new JsonPath(res);
       String aToken= js.getString("access_token");
        System.out.println(aToken);

        GetResponse r2=given().queryParam("access_token",aToken).expect().defaultParser(Parser.JSON).when().
                get("https://rahulshettyacademy.com/getCourse.php").as(GetResponse.class);
        System.out.println(r2.getUrl());



    }
}
