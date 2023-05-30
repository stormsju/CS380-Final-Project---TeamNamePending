package admin.http;

import admin.entity.Friend;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FriendHttp {
    public static List<Friend> getAllContactIDs() throws Exception{
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getAllContactIDs"))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Type personType = new TypeToken<ArrayList<Friend>>(){}.getType();

        return gson.fromJson(response.body(), personType);
    }
}
