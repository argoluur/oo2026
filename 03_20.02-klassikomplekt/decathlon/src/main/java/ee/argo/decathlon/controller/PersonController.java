package ee.argo.decathlon.controller;


import ee.argo.decathlon.entity.Person;
import ee.argo.decathlon.repositry.PersonRepositry;
import ee.argo.decathlon.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {
    private PersonRepositry personRepositry;
    private PersonService personService;

    @GetMapping("persons")
    public List<Person> getPerson() { return personRepositry.findAll(); }

    @GetMapping("persons/{id}")
    public double getPersonScore(@PathVariable("id") Long id) {

        return personService.calculatePointsTotal(id);
    }


    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id){
        personRepositry.deleteById(id);
        return personRepositry.findAll();
    }

    @PostMapping("persons")
    public Person addPerson(@RequestBody Person person){
        if (person.getId() != null){
            throw new RuntimeException("Cannot add person with ID");
        }
        if (person.getFirstName() == null){
            throw new RuntimeException("Cannot add person without name");
        }
        if (person.getLastName() == null){
            throw new RuntimeException("Cannot add person without name");
        }
        if (person.getSportField() == null){
            throw new RuntimeException("Cannot add person without sports field");
        }
        if (person.getScore() == null){
            throw new RuntimeException("Cannot add person without score");
        }
        if (person.getPoints() != null){
            throw new RuntimeException("Cannot add person with points");
        }
        return personRepositry.save(person);
    }

}
