import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Requests {
    private static Requests requests = null;

    public static Requests getInstance(){
        if(requests == null){
            requests = new Requests();
        }
        return requests;
    }
    private Requests(){ }

    public ArrayList<HashMap<String,Object>> getManifestResponse(String apiKey,String uri) {
        return given().queryParam("api_key",apiKey)
                .when()
                    .log().uri()
                    .get(uri)
                .then()
                    .assertThat().statusCode(200)
                .and()
                    .extract().path("photo_manifest.photos");
    }

    public ArrayList<String> getPhotosResponseOnSol(String sol,String apiKey, String pages, String uri){
        return given().queryParam("sol", sol)
                        .queryParam("api_key",apiKey)
                        .queryParam("page",pages)
                .when()
                    .log().uri()
                    .get(uri)
                .then()
                    .assertThat().statusCode(200)
                .and()
                    .extract().path("photos.img_src");
    }

    public ArrayList<String> getPhotosResponseOnEarthDate(String date,String apiKey, String pages, String uri){
        return given().queryParam("earth_date", date)
                        .queryParam("api_key",apiKey)
                        .queryParam("page",pages)
                .when()
                    .log().uri()
                    .get(uri)
                .then()
                    .assertThat().statusCode(200)
                .and()
                    .extract().path("photos.img_src");
    }
}
