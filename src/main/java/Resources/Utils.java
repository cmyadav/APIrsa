package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {


        if (req == null){
        PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
         req = new RequestSpecBuilder().setBaseUri(globalVariable("baseURI"))
                 .addQueryParam("key", "qaclick123")
                 .addFilter(RequestLoggingFilter.logRequestTo(log))
                 .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();

         return req;
        }return req;

    }

    public static String globalVariable(String key) throws IOException {

        Properties property = new Properties();
        FileInputStream File = new FileInputStream("/Users/akkiyadav/Downloads/RestAssured_RSA/src/main/java/Resources/Global.properties");
        property.load(File);
        return property.getProperty(key);
    }

    public static String getJsonPath(Response response, String key){

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key);
    }

}
