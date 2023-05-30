package admin.http;
import com.google.gson.Gson;
import admin.entity.Comment;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CommentHttp {
    public static Comment getCommentById(int id) throws Exception{
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getCommentById/"+id))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Comment.class);
    }
}
