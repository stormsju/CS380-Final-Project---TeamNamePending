package admin.http;
import com.google.gson.Gson;
import admin.entity.Admin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdminHttp {
    public static Admin getAdminWithId(int id) throws Exception{
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getAdminById/"+id))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(), Admin.class);
    }
}
