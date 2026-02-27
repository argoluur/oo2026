package ee.argo.decathlon.repositry;

import ee.argo.decathlon.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepositry extends JpaRepository<Person,Long> {

}
