package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

class PersonHttpTest {

    @Test
    public void getPeople() throws Exception {
        Gson gson = new Gson();
        Person person = new Person();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:1911/getPeople"))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Type personType = new TypeToken<ArrayList<Person>>(){}.getType();
        List<Person> peopleList = gson.fromJson(response.body(), personType);

        for (Person p: peopleList) {
            System.out.println(p.toString());
        }
    }

    @Test
    public void savePerson(){
        Person person = Person.builder()
                .username("WyattTheRiot")
                .fname("Wyatt")
                .lname("Stohr")
                .email("superRealEmail@gmail.com")
                .password("password")
                .build();

        Gson gson = new Gson();
        String requestBody = gson.toJson(person);

        System.out.println(requestBody);
    }
}