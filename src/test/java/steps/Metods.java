package steps;

import com.jayway.restassured.http.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

public class Metods {
    public static String accessToken = "13OUOf641bALRDrwbIxZg8veGMi9LfgsZDCR";
    public static String link = "https://gorest.co.in/public-api/users";
    public static String url = link + "?_format=json&access-token=" + accessToken;

    public static String newID;
    public static int getStatuseCode;
    public static int postStatuseCode;
    public static int putStatuseCode;
    public static int deleteStatuseCode;
    public static String newDataString;

    public static String[] keys = {"id", "first_name", "last_name", "gender", "dob", "email", "phone", "website", "address", "status"};


    public static HashMap<String, String> newData = new HashMap();
    public static String DataString;

    public static void GETPOST(String id, String name) {


        given().contentType(ContentType.JSON).
                when().
                get(String.format("https://gorest.co.in/public-api/users?_format=json&access-token=13OUOf641bALRDrwbIxZg8veGMi9LfgsZDCR&id=%s", id)).
                then().body("result.first_name[0]", is(name)).statusCode(200);

    }

    public static void CheckGetPost(String postNumber) {

        given().contentType(ContentType.JSON).
                when().get(String.format("https://my-json-server.typicode.com/typicode/demo/posts/", postNumber)).
                then().body("title", containsInAnyOrder("Post 3"));
    }


    public static void Post(Map<String, String> person) {


        postStatuseCode = given().contentType(ContentType.JSON).with().body(person).
                when().
                post(url).then().extract().path("_meta.code");

    }


    public static void GetData(Map<String, String> person) {


        getStatuseCode = given().contentType(ContentType.JSON).
                when().
                get(String.format(url + "&email=%s", person.get("email"))).
                then().body("result.first_name[0]", is(person.get("first_name"))).
                body("result.last_name[0]", is(person.get("last_name"))).
                body("result.gender[0]", is(person.get("gender"))).extract().path("_meta.code");

        newID = given().when().
                get(String.format(url + "&email=%s", person.get("email"))).
                then().contentType(ContentType.JSON).extract().path("result[0].id");


    }


    public static void Delete(String id, String name) {

        given().contentType(ContentType.JSON).with().
                param("access-token", accessToken).
                when().
                delete(String.format(link + "/" + id)).getBody().print();

    }


    public static void Put(String id, Map<String, String> person) {
        given().contentType(ContentType.JSON).with().body(person).

                when().
                put("https://gorest.co.in/public-api/users/2051?access-token=13OUOf641bALRDrwbIxZg8veGMi9LfgsZDCR").getBody().print();


    }

    public static void PutNewId(Map<String, String> person) {

        putStatuseCode = given().contentType(ContentType.JSON).with().body(person).
                when().
                put(String.format("https://gorest.co.in/public-api/users/%s?access-token=13OUOf641bALRDrwbIxZg8veGMi9LfgsZDCR", newID)).then().extract().path("_meta.code");
    }

    public static void deleteWithNewId() {
        deleteStatuseCode = given().contentType(ContentType.JSON).with().
                param("access-token", accessToken).
                when().
                delete(String.format(link + "/" + newID)).then().extract().path("_meta.code");


    }

    public static void verifyStatus() {
        System.out.println();
        System.out.print("Get Status: \t" + getStatuseCode);
        if (getStatuseCode == 200) System.out.println(" OK");
        else System.out.println(" Error");

        System.out.print("Put Status: \t" + putStatuseCode);
        if (putStatuseCode == 200) System.out.println(" OK");
        else System.out.println(" Error");

        System.out.print("Post Status: \t" + postStatuseCode);
        if (postStatuseCode == 201) System.out.println(" OK");
        else System.out.println(" Error");

        System.out.print("Delete Status: \t" + deleteStatuseCode);
        if (deleteStatuseCode == 204) System.out.println(" OK");
        else System.out.println(" Error");

        System.out.println("/-----------------------------------------------/");
    }

    public static void gettingdat() {


        DataString = given().contentType(ContentType.JSON).
                when().
                get(String.format(url + "&id=%s", newID)).getBody().prettyPrint();
        System.out.println("/-----------------------------------------------/");

        JSONObject obj = new JSONObject(DataString);
        JSONArray data = obj.getJSONArray("result");
        int n = data.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < keys.length; j++) {


                JSONObject person = data.getJSONObject(i);
                System.out.println(keys[j] + ": " + person.get(keys[j]));

            }
        }
        System.out.println("/-----------------------------------------------/");


    }


    public static void getingNewData() {

        newDataString = given().contentType(ContentType.JSON).
                when().
                get(String.format(url + "&id=%s", newID)).getBody().prettyPrint();
        System.out.println("/-----------------------------------------------/");

        JSONObject obj = new JSONObject(newDataString);
        JSONArray data = obj.getJSONArray("result");
        int n = data.length();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < keys.length; j++) {


                JSONObject person = data.getJSONObject(i);
                System.out.println(keys[j] + ": " + person.get(keys[j]));

            }
        }
        System.out.println("/-----------------------------------------------/");

    }

    public static void deleteAllObjects() {
        newDataString = given().contentType(ContentType.JSON).
                when().
                get(String.format(url)).getBody().prettyPrint();
        System.out.println("/-----------------------------------------------/");

        JSONObject obj = new JSONObject(newDataString);
        JSONArray data = obj.getJSONArray("result");


        for (int i = 0; i < data.length(); i++) {


            JSONObject person = data.getJSONObject(i);

            System.out.print("Delete id: " + person.get("id"));

            deleteStatuseCode = given().
                    param("access-token", accessToken).
                    delete(String.format(link + "/" + person.get("id"))).then().extract().path("_meta.code");

            System.out.print(". Code: " + deleteStatuseCode + " Status:");

            if (deleteStatuseCode == 204) System.out.println(" OK");
            else System.out.println(" Error");
        }
    }


}
