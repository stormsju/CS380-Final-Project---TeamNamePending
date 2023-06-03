package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Post;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wyatt Stohr (original), Justin Storms (modified)
 * Code block borrowed from Http Application. Contains a single static method call which
 * allows a Post object to be requested from Http front-end.
 */
public class PostHttp {

    private final String apiUrl = "http://localhost:1911/api/v1/post";

    /**
     * Method to retrieve specific post from database.
     * @param postId The id of post to query for.
     * @return An object of type Post.
     * @throws Exception handles generic exception.
     */
    public Post getById(int postId) throws Exception{

        //initialize gson object
        Gson gson = new Gson();

        //create http get request to api
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(this.apiUrl+'/'+postId))
                .GET()
                .build();

        //make HttpClient and send request to api, get response body.
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        //parse JSON string into Post object using gson

        return gson.fromJson(response.body(), Post.class);
    }

    /**
     * Method to retrieve all posts from the database.
     * @return A list of every post in the database.
     * @throws Exception Throws generic exception.
     */
    public List<Post> getAll() throws Exception{

        //initialize gson object
        Gson gson = new Gson();

        //create http get request to api
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(this.apiUrl))
                .GET()
                .build();

        //make HttpClient and send request to api, get response body.
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        //parse JSON string into List<Post> object using gson
        Type postType = new TypeToken<ArrayList<Post>>(){}.getType();

        return gson.fromJson(response.body(), postType);
    }

    /**
     * Method to retrieve all posts made by a person.
     * @param personId The id of the Person to query posts for.
     * @return A list of all Posts that contain the personId.
     * @throws Exception Throws generic exception.
     */
    public List<Post> getByPersonId(int personId) throws Exception{

        //initialize gson object
        Gson gson = new Gson();

        //create http get request to api
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(this.apiUrl+"/person/"+personId))
                .GET()
                .build();

        //make HttpClient and send request to api, get response body.
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        //creating new type for List<Post>
        Type postType = new TypeToken<ArrayList<Post>>(){}.getType();
        //parse JSON string into List<Post> object using gson
        return gson.fromJson(response.body(), postType);
    }
}
