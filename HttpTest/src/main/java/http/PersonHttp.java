package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Person;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PersonHttp {

    public static void main(String[] args) throws Exception {
        List<Person> people = getAllPeople();


    }

    public static List<Person> getAllPeople() throws Exception{
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getPeople"))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Type personType = new TypeToken<ArrayList<Person>>(){}.getType();

        return gson.fromJson(response.body(), personType);
    }

    public static Person getPersonWithId(int id) throws Exception{
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getPersonById/"+id))
                .GET()
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return gson.fromJson(response.body(), Person.class);
    }

    public static void addPerson(Person person)throws Exception{
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(person);

        System.out.println(jsonRequest);
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/addPerson"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .header("Content-Type", "application/json")
                .build();

        System.out.println(postRequest.headers());
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
    }


}
