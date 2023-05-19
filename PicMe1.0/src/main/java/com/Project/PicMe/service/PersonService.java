package com.Project.PicMe.service;

import com.Project.PicMe.entity.Person;
import com.Project.PicMe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public Person savePerson(Person person){
        return repository.save(person);
    }

    public List<Person> savePersons(List<Person> personList){
        return repository.saveAll(personList);
    }

    public List<Person> getPersons(){
        return repository.findAll();
    }

    public Person findPersonById(int id){
        return repository.findById(id).orElse(null);
    }

    public boolean deletePerson(int id){
        if(findPersonById(id) != null){
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public List<Person> findPersonsByName(String name){
        return repository.findAll()
                .stream()
                .filter(s -> s.getFname().equalsIgnoreCase(name) || s.getLname().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public Person updatePerson(Person person){
        Person existingPerson = repository.findById(person.getId()).orElse(null);
        existingPerson.setFname(person.getFname());
        existingPerson.setLname(person.getLname());
        existingPerson.setEmail(person.getEmail());
        return repository.save(existingPerson);
    }
}
