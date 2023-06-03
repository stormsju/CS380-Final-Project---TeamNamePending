package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Person;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wyatt Stohr (original), Justin Storms (modified)
 * Code block borrowed from Http Application. Contains static method call which
 * allows a Person object to be requested from Http front-end.
 */
public class PersonHttp {

    private final String apiUrl = "http://localhost:1911/api/v1/person";

    /**
     * Method to retrieve person object from database by id.
     * @param id int Person ID key of Person table in database.
     * @return Person object from database
     * @throws Exception An exception for the URI syntax.
     */
    public Person getById(int id) throws Exception {
        //initialize gson object
        Gson gson = new Gson();

        //create http get request to api
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(this.apiUrl+'/'+id))
                .GET()
                .build();

        //make HttpClient and send request to api, get response body.
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        //parse JSON string into Person object using gson
        return gson.fromJson(response.body(), Person.class);
    }

    /**
     * A method to retrieve every Person object from the database.
     * @return A list of people (List<Person>) from the database.
     * @throws Exception An exception for the URI syntax.
     */
    public List<Person> getAllPeople() throws Exception{
        //initialize gson object
        Gson gson = new Gson();

        //create http get request to api
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(this.apiUrl+"/all"))
                .GET()
                .build();

        //make HttpClient and send request to api, get response body.
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        //make new token type for List<Person>
        //parse JSON string into List<Person> object using gson
        Type personType = new TypeToken<ArrayList<Person>>(){}.getType();
        return gson.fromJson(response.body(), personType);
    }

    /**
     * Method to save a person object into the database.
     * @param person Person object to be saved.
     * @throws Exception Throws an exception for the URI syntax.
     */
    public void save(Person person)throws Exception{
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(person);

        System.out.println(jsonRequest);
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI(this.apiUrl+"/add"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .header("Content-Type", "application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
    }
}
