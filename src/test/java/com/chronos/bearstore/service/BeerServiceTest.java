package com.chronos.bearstore.service;

import com.chronos.bearstore.model.Beer;
import com.chronos.bearstore.model.BeerType;
import com.chronos.bearstore.repository.BeerRepository;
import com.chronos.bearstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class BeerServiceTest {

    private BeerService service;

    @Mock
    private BeerRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new BeerService(repository);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_update_of_an_existing_beer_that_already_exists() {

        Beer beerInDB = new Beer();
        beerInDB.setId(10l);
        beerInDB.setName("Heineken");
        beerInDB.setType(BeerType.IPA);
        beerInDB.setVolume(BigDecimal.valueOf(350));

        when(repository.findByNameAndType("Heineken", BeerType.IPA)).thenReturn(Optional.of(beerInDB));

        Beer beer = new Beer();
        beer.setName("Heineken");
        beer.setType(BeerType.IPA);
        beer.setVolume(BigDecimal.valueOf(350));
        service.save(beer);
    }

    @Test
    public void should_create_new_beer() {


        Beer beer = new Beer();
        beer.setName("Heineken");
        beer.setType(BeerType.IPA);
        beer.setVolume(BigDecimal.valueOf(350));

        Beer newBeer = new Beer();
        newBeer.setId(10l);
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.IPA);
        newBeer.setVolume(BigDecimal.valueOf(350));

        when(repository.save(beer)).thenReturn(newBeer);

        Beer beerSave = service.save(beer);

        assertThat(beerSave.getId(), equalTo(10l));
        assertThat(beerSave.getName(), equalTo("Heineken"));
    }
}
