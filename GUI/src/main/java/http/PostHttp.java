package http;

import com.google.gson.Gson;
import entity.Post;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Wyatt Stohr (original), Justin Storms (modified)
 * Code block borrowed from Http Application. Contains a single static method call which
 * allows a Post object to be requested from Http front-end.
 */
public class PostHttp {
    public static Post getPostWithId(int id) throws Exception{
        Post post = new Post();
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getPostById/"+id))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        //use while loop to handle data response time
        while(true){
            response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            post = gson.fromJson(response.body(), Post.class);

            if(post.getStatus().equals("completed")){
                break;
            }

            if(post.getStatus().equals("error")){
                return null;
            }

        }

        return post;
    }
}
