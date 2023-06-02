package http;

import com.google.gson.Gson;
import entity.Person;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Wyatt Stohr (original), Justin Storms (modified)
 * Code block borrowed from Http Application. Contains static method call which
 * allows a Person object to be requested from Http front-end.
 */
public class PersonHttp {
    /**
     * Class which returns a Gson data request from Http front-end. Pulls the data for a Person object.
     * @param id int Person ID key of Person table in database.
     * @return Gson of Person class data.
     * @throws Exception
     */
    public static Person getPersonWithId(int id) throws Exception{
        Person person = new Person();
        Gson gson = new Gson();
        String json = gson.toJson(person);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getPersonById/"+id))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        //use while loop to handle data response time
        while(true){
            response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            person = gson.fromJson(response.body(), Person.class);

            if(person.getStatus().equals("completed")){
                break;
            }

            if(person.getStatus().equals("error")){
                return null;
            }

        }

        return person;
    }
}
