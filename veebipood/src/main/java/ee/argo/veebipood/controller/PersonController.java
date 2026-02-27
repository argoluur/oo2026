package ee.argo.veebipood.controller;


import ee.argo.veebipood.dto.PersonLoginRecordDto;
import ee.argo.veebipood.entity.Person;
import ee.argo.veebipood.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository; //dependency injection, ei võta ressurssi


    @GetMapping("persons")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);  //kustutab
        return personRepository.findAll();  //uuendatud seis
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        if (person.getId() != null){
            throw new RuntimeException("Cannot sign up with ID");
        }
        return personRepository.save(person);  //salvestab

    }
    @PostMapping("login")
    public Person login(@RequestBody PersonLoginRecordDto personDto){
        Person dbPerson = personRepository.findByEmail(personDto.email());
        if (dbPerson == null) {
            throw new RuntimeException("Invalid email");
        }
        if(!dbPerson.getPassword().equals(personDto.password())){
            throw new RuntimeException("Invalid password");
        }
        return dbPerson;
    }
}
