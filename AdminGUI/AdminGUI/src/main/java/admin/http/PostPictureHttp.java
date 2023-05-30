package admin.http;
import com.google.gson.Gson;
import admin.entity.PostPicture;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PostPictureHttp {
    public static PostPicture getPostPictureWithId(int id) throws Exception{
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getPostPictureById/"+id))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), PostPicture.class);
    }
}
