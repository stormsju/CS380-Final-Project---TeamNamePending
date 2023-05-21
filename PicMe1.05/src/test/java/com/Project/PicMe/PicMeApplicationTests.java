package com.Project.PicMe;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PicMeApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Test
	public void savePerson(){
		Person person = Person.builder()
				.fname("Person")
				.lname("Tester")
				.email("bogusemail@outlook.com")
				.build();

		personRepository.save(person);
	}

	@Test
	public void printPeople(){
		List<Person> people= personRepository.findAll();
		for (Person p: people) {
			System.out.println(p.toString());
		}
	}

}
