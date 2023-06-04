package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Person;
import entity.Picture;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wyatt Stohr (original), Justin Storms (modified)
 * Code block borrowed from Http Application. Contains static method call which
 * allows a Picture object to be requested from Http front-end.
 */
public class PictureHttp {

    private final String pictureUrl = "http://localhost:1911/api/v1/picture";
    /**
     * Class which returns a Gson data request from Http front-end. Pulls the data for a Picture object.
     * @param id int Picture ID key of Picture table in database.
     * @return Gson of Picture class data.
     * @throws Exception
     */
    public Picture getById(int id) throws Exception{

        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(pictureUrl +'/'+ id))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Picture.class);
    }

    public List<Picture> getByPerson(int personId) throws Exception{

        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(pictureUrl+"/person/"+personId))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Type pictureListClass = new TypeToken<List<Picture>>(){}.getType();
        return gson.fromJson(response.body(), pictureListClass);
    }
}
