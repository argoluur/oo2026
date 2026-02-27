package ee.argo.veebipood.repository;


import ee.argo.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

//CrudRepository --> minimaalsed vajalikud (standard) funktsioonid
//PagingAndSortingRepository --> funktsioonid lehekülgede kaupa andmete väljastamiseks ja sorteerimiseks
//JpaRepository --> kõik võimalikud funktsioonid

public interface PersonRepository  extends JpaRepository<Person,Long> {
    // SELECT * FROM person WHERE email =
    Person findByEmail(String email);
}
