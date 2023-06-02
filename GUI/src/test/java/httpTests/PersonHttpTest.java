package http;

import com.google.gson.Gson;
import entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonHttpTest {
    @Test
    public void savePerson() {
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
    @Test
    void getPersonWithId() {
        try {
            Person p = PersonHttp.getPersonWithId(0);
            assertEquals(p.getId(), 0);
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonUsername() {
        try {
            Person p = (Person) PersonHttp.getPersonWithId(0);
            assertEquals(p.getUsername(), "WyattTheRiot");
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonFname() {
        try{
            Person p = PersonHttp.getPersonWithId(0);
            assertEquals(p.getFname(), "Wyatt");
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonLname() {
        try{
            Person p = PersonHttp.getPersonWithId(0);
            assertEquals(p.getLname(), "Stohr");
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonEmail() {
        try{
            Person p = PersonHttp.getPersonWithId(0);
            assertEquals(p.getEmail(), "SuperRealEmail@gmail.com");
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonPassword() {
        try{
            Person p = PersonHttp.getPersonWithId(0);
            assertEquals(p.getPassword(), "password");
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}