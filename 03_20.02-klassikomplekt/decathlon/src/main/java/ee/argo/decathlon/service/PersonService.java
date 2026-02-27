package ee.argo.decathlon.service;


import ee.argo.decathlon.entity.Person;
import ee.argo.decathlon.repositry.PersonRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class PersonService {


    private final PersonRepositry personRepositry;

    public double calculatePointsTotal(Long id) {
        double total = 0;
        Person person = personRepositry.findById(id).orElseThrow();
        String field = person.getSportField();
        Integer score = person.getScore();

        if(Objects.equals(field, "100m")) {
            double a = 25.4347;
            double b = 18.0;
            double c = 1.81;

            if (score >= b) return 0;

            total = a * Math.pow((b - score), c);
        } else if (Objects.equals(field, "Long Jump")) {
            double a = 0.14354;
            double b = 220.0;
            double c = 1.40;

            if (score <= b) return 0;


            total = a * Math.pow((score -b), c);
        } else {
            System.out.println("Not good");
            System.out.println(field);

        }



        return total;
    }
}
