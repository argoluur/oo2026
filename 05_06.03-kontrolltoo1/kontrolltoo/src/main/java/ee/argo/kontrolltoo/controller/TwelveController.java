package ee.argo.kontrolltoo.controller;

import ee.argo.kontrolltoo.entity.Twelve;
import ee.argo.kontrolltoo.repository.TwelveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TwelveController {
    @Autowired
    private TwelveRepository twelveRepository;

    @PostMapping("twelves")
    public List<Twelve> addMovie(@RequestBody Twelve twelve){
        if (twelve.getId() != null){
            throw new RuntimeException("Can not add Entity with ID");
        }
        if (twelve.getText() == null) {
            throw new RuntimeException("Can not add Entity without text");
        }
        twelveRepository.save(twelve);
        return twelveRepository.findAll();
    }
    @GetMapping("twelves")
    public List<Twelve> getEntities() {return twelveRepository.findAll();}
}
