package ee.argo.autod_veateated.controller;


import ee.argo.autod_veateated.entity.Car;
import ee.argo.autod_veateated.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarsController {
    @Autowired
    private CarsRepository carsRepository;

    @GetMapping("cars")
    public List<Car> getCars(){return carsRepository.findAll();}

    @PostMapping("cars")
    public List<Car> addCar(@RequestBody Car car){
        if (car.getId() != null){
            throw new RuntimeException("Can't add without ID");
        }
        if (car.getBrand() == null){
            throw new RuntimeException("Can't add without car brand");
        }
        if (car.getRegNumber() == null){
            throw new RuntimeException("Can't add without registry number");
        }
        if (car.getModel() == null){
            throw new RuntimeException("Can't add without car model");
        }
        if (car.getYear() == null){
            throw new RuntimeException("Can't add without car year");
        }
        carsRepository.save(car);
        return carsRepository.findAll();
    }
}
