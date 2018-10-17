package com.chronos.bearstore.repository;

import com.chronos.bearstore.model.Beer;
import com.chronos.bearstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);
}
