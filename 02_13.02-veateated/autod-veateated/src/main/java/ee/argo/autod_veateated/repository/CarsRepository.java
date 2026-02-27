package ee.argo.autod_veateated.repository;

import ee.argo.autod_veateated.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Car,Long>  {
}
