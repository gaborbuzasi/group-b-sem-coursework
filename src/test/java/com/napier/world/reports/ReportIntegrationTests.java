package com.napier.world.reports;

import com.napier.sem.App;
import com.napier.world.models.*;
import com.napier.world.models.Population;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReportIntegrationTests
{
    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();

        String port = System.getProperty("portNumber");

        if (port == null)
        {
            port = "localhost:33060";
        }
        app.connectToDatabase(port);
    }

    @Test
    void testGetPopulationOfCity()
    {
        PopulationInfo result = new com.napier.world.reports.PopulationInfos(app.Conn).getPopulationOfACity("Edinburgh");
        assertNotNull(result);
    }

    @Test
    void testGetCountriesByDescendingPopulation()
    {
        List<Country> result = new Countries(app.Conn).getAllCountriesInWorldByDescPopulation();
        assertTrue(result.size() > 0);
    }

    @Test
    void testGetNPopulatedCountries()
    {
        List<Country> result = new Countries(app.Conn).getNPopulatedCountriesInWorldByDescPopulation(5);
        assertTrue(result.size() == 5);
    }

    @Test
    void testGetCountriesInRegion()
    {
        List<Country> result = new Countries(app.Conn).getAllCountriesInRegionByDescPopulation("Central Africa");
        assertTrue(result.size() > 0);
    }

    @Test
    void testGetAllCountriesInContinentByDescPopulation()
    {
        List<Country> result = new Countries(app.Conn).getAllCountriesInContinentByDescPopulation("Africa");
        assertTrue(result.size() > 0);
    }

    @Test
    void testNPopulatedCountriesWithinRegion()
    {
        List<Country> result = new Countries(app.Conn).getNPopulatedCountriesInRegionByDescPopulation(5, "Central Africa");
        assertTrue(result.size() > 0);
    }

    @Test
    void testNPopulatedCitiesWithinRegion()
    {
        List<City> result = new Cities(app.Conn).getNPopulatedCitiesWithinRegionByDescPopulation(5, "Central Africa");
        assertTrue(result.size() > 0);
    }

    @Test
    void testGetPopulationByRegion()
    {
        List<Population> result = new com.napier.world.reports.Population(app.Conn).getPopulationByRegion();
        assertTrue(result.size() > 1);
    }

    @Test
    void testGetPopulationByCountry()
    {
        List<Population> result = new com.napier.world.reports.Population(app.Conn).getPopulationByCountries();
        assertTrue(result.size() > 1);
    }

    @Test
    void testGetPopulationByContinent()
    {
        List<Population> result = new com.napier.world.reports.Population(app.Conn).getPopulationByContinent();
        assertTrue(result.size() > 1);
    }

    @Test
    void testGetPopulationOfWorld()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfWorld();
        assertNotNull(pop);
    }

    @Test
    void testGetPopulationOfAContinent()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfAContinent("Africa");
        assertNotNull(pop);
    }

    @Test
    void testGetPopulationOfARegion()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfARegion("South America");
        assertNotNull(pop);
    }

    @Test
    void testGetPopulationOfACountry()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfACountry("Hungary");
        assertNotNull(pop);
    }

    @Test
    void testGetPopulationOfADistrict()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfADistrict("Herat");
        assertNotNull(pop);
    }

    @Test
    void testGetPopulationOfACity()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfACity("London");
        assertNotNull(pop);
    }

    @Test
    void testGetAllOrNCapitalCitiesReturnsFive()
    {
        List<CapitalCity> capitals = new Cities(app.Conn).getAllOrNCapitalCitiesInWorldByDescPopulation(5);
        assertTrue(capitals.size() == 5);
    }

    @Test
    void testGetAllOrNCapitalCitiesReturnsAll()
    {
        List<CapitalCity> capitals = new Cities(app.Conn).getAllOrNCapitalCitiesInWorldByDescPopulation(null);
        assertTrue(capitals.size() > 0);
    }

    @Test
    void testGetAllCapitalCitiesInRegion()
    {
        List<CapitalCity> capitals = new Cities(app.Conn).getAllCapitalCitiesInRegionByDescPopulation("Central Africa");
        assertTrue(capitals.size() > 0);
    }

    @Test
    void testGetCitiesByDescPopulation()
    {
        List<City> cities = new Cities(app.Conn).getAllCitiesInWorldByDescPopulation();
        assertTrue(cities.size() > 0);
    }

    @Test
    void testGetNPopulatedCities()
    {
        List<City> cities = new Cities(app.Conn).getNPopulatedCitiesInWorldByDescPopulation(5);
        assertTrue(cities.size() == 5);
    }

    @Test
    void testGetAllCitiesInRegionByDescPopulation()
    {
        List<City> cities = new Cities(app.Conn).getAllCitiesInRegionByDescPopulation("Central Africa");
        assertTrue(cities.size() > 0);
    }

    @Test
    void testgetAllCitiesInContinentByDescPopulation()
    {
        List<City> cities = new Cities (app.Conn).getAllCitiesInContinentByDescPopulation("Europe");
        assertTrue(cities.size() > 0);
    }

    @Test
    void testGetNPopulatedCitiesInContinent()
    {
        List<City> cities = new Cities(app.Conn).getNPopulatedCitiesInContinentByDescPopulation(5, "Asia");
        assertTrue(cities.size() == 5);
    }

    @Test
    void testGetNPopulatedCapitalCitiesInRegion()
    {
        List<CapitalCity> cities = new Cities(app.Conn).getNPopulatedCapitalCitiesInRegionByDescPopulation(5, "Central Africa");
        assertTrue(cities.size() == 5);
    }

    @Test
    void testGetNPopulatedCapitalCitiesInContinent()
    {
        List<CapitalCity> cities = new Cities(app.Conn).getNPopulatedCapitalCitiesInContinentByDescPopulation(5, "Asia");
        assertTrue(cities.size() == 5);
    }

    @Test
    void testGetAllCitiesInContinentByDescPopulation()
    {
        List<City> cities = new Cities(app.Conn).getAllCitiesInContinentByDescPopulation("Africa");
        assertTrue(cities.size() > 0);
    }
    
    @Test
    void testGetAllCitiesInCountryByDescPopulation()
    {
        List<City> cities = new Cities(app.Conn).getAllCitiesInCountryByDescPopulation("Hungary");
        assertTrue(cities.size() > 0);
    }

    @Test
    void testGetNPopulatedCitiesInDistrict()
    {
        List<City> cities = new Cities(app.Conn).getNPopulatedCitiesInDistrictByDescPopulation(10, "Herat");
        assertTrue(cities.size() > 0);
    }

    @Test
    void testGetAllCitiesInDistrict()
    {
        List<City> cities = new Cities(app.Conn).getAllCitiesInDistrictByDescPopulation("Herat");
        assertTrue(cities.size() > 0);
    }

    @Test
    void testGetNPopulatedCitiesInCountry()
    {
        List<City> cities = new Cities(app.Conn).getNPopulatedCitiesInCountryByDescPopulation(10, "Hungary");
        assertTrue(cities.size() > 0);
    }
}
