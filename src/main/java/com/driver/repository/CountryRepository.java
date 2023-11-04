package com.driver.repository;

import com.driver.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "select * from country where CountryName =: countryName", nativeQuery = true)
    List<Country> findCountryByName(String countryName);
}
