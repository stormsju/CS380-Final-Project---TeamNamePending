package httpTests;

import com.google.gson.Gson;
import entity.Person;
import http.PersonHttp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonHttpTest {
    @Test
    public void save() throws Exception {

        //creating PersonHttp class object.
        PersonHttp personHttp = new PersonHttp();

        //building person object to insert into the database.
        Person person = Person.builder()
                .fname("Wyatt")
                .lname("Stohr")
                .username("WyattTheRiot")
                .email("superRealEmail@gmail.com")
                .password("password")
                .date("05/25/2001")
                .build();

        //running save method.
        personHttp.save(person);
    }
    @Test
    public void getById() throws Exception {
        //calls the personHttp to get user from database.
        //api must be running in background.
        PersonHttp personHttp = new PersonHttp();
        Person person = personHttp.getById(1000);
        System.out.println(person.toString());

    }

    @Test
    public void getAll() throws Exception{
        //make new PersonHttp object
        PersonHttp personHttp = new PersonHttp();

        //get list from getAllPeople method
        List<Person> people = personHttp.getAllPeople();

        //iterate through list and print off people
        people.forEach(person -> System.out.println(person.toString()));

    }

    @Test
    public void getPersonUsername() {
        try {
            //Person p = (Person) PersonHttp.getPersonWithId(0);
            //assertEquals(p.getUsername(), "WyattTheRiot");
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonFname() {
        try{
            //Person p = PersonHttp.getPersonWithId(0);
            //assertEquals(p.getFname(), "Wyatt");
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonLname() {
        try{
            /*Person p = PersonHttp.getPersonWithId(0);
            assertEquals(p.getLname(), "Stohr");*/
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonEmail() {
        try{
            /*Person p = PersonHttp.getPersonWithId(0);
            assertEquals(p.getEmail(), "SuperRealEmail@gmail.com");*/
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void getPersonPassword() {
        try{
            /*Person p = PersonHttp.getPersonWithId(0);
            assertEquals(p.getPassword(), "password");*/
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}