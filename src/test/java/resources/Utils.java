package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    public static RequestSpecification request;
    ResponseSpecification res;

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);


    }

    public RequestSpecification resquestSpecifiaction() throws IOException {
        if (request == null) {

            PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
            request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri")).
                    addQueryParam("key", "qaclick123").
                    setContentType(ContentType.JSON).
                    addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();


            return request;
        }
        return request;
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();

    }


}
