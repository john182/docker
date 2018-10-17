package com.chronos.bearstore.service;

import com.chronos.bearstore.model.Beer;
import com.chronos.bearstore.repository.BeerRepository;
import com.chronos.bearstore.service.exception.BeerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {


    private BeerRepository repository;

    public BeerService(@Autowired BeerRepository repository) {
        this.repository = repository;
    }

    public Beer save(final Beer beer) {

        verifyIfBeerExists(beer);
        return repository.save(beer);
    }

    private void verifyIfBeerExists(final Beer beer) {
        Optional<Beer> beerByNameAndType = repository.findByNameAndType
                (beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent() && (beer.isNew() ||
                isUpdatingToADifferentBeer(beer, beerByNameAndType))) {
            throw new BeerAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer,
                                               Optional<Beer> beerByNameAndType) {
        return beer.alreadyExist() && !beerByNameAndType.get()
                .equals(beer);
    }
}
