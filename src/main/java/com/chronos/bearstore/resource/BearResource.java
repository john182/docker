package com.chronos.bearstore.resource;

import com.chronos.bearstore.model.Beer;
import com.chronos.bearstore.repository.BeerRepository;
import com.chronos.bearstore.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BearResource {

    @Autowired
    private BeerRepository repository;
    @Autowired
    private BeerService service;

    @GetMapping()
    public List<Beer> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer) {
        return service.save(beer);
    }

}
