package http;

import com.google.gson.Gson;
import entity.Picture;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Wyatt Stohr (original), Justin Storms (modified)
 * Code block borrowed from Http Application. Contains static method call which
 * allows a Picture object to be requested from Http front-end.
 */
public class PictureHttp {
    /**
     * Class which returns a Gson data request from Http front-end. Pulls the data for a Picture object.
     * @param id int Picture ID key of Picture table in database.
     * @return Gson of Picture class data.
     * @throws Exception
     */
    public static Picture getPictureWithId(int id) throws Exception{
        Picture picture = new Picture();
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getPersonById/"+id))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        //use while loop to handle data response time
        while(true){
            response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            picture = gson.fromJson(response.body(), Picture.class);

            if(picture.getStatus().equals("completed")){
                break;
            }

            if(picture.getStatus().equals("error")){
                return null;
            }

        }

        return picture;
    }
}
