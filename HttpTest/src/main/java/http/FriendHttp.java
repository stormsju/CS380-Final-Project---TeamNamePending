package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Friend;
import entity.Person;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FriendHttp {

    public static void main(String[] args)throws Exception{
        List<Friend> friends = getAllFriends();

        for (Friend f: friends) {
            System.out.println(f.toString());
        }
    }

    public static List<Friend> getAllFriends() throws Exception{
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getFriends"))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        Type friendListType = new TypeToken<ArrayList<Friend>>(){}.getType();

        return gson.fromJson(response.body(), friendListType);
    }
}
