package com.napier.world.reports;

import com.napier.sem.App;
import com.napier.world.models.*;
import com.napier.world.models.Population;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing all integration tests for our application
 */
public class ReportIntegrationTests
{
    static App app;
    @BeforeAll
    static void init()
    {
        // Create new Application
        app = new App();

        // Connect to database
        String port = System.getProperty("portNumber");

        if (port == null)
        {
            port = "localhost:33060";
        }
        app.connectToDatabase(port);
    }
    // Tests GetPopulationofCity report returns more than zero elements
    @Test
    void testGetPopulationOfCity()
    {
        PopulationInfo result = new com.napier.world.reports.PopulationInfos(app.Conn).getPopulationOfACity("Edinburgh");
        assertNotNull(result);
    }
    // Tests GetCountriesByDescendingPopulation returns more than zero elements
    @Test
    void testGetCountriesByDescendingPopulation()
    {
        List<Country> result = new Countries(app.Conn).getAllCountriesInWorldByDescPopulation();
        assertTrue(result.size() > 0);
    }
    // Tests GetNPopulatedCountries report returns more than zero elements
    @Test
    void testGetNPopulatedCountries()
    {
        List<Country> result = new Countries(app.Conn).getNPopulatedCountriesInWorldByDescPopulation(5);
        assertTrue(result.size() == 5);
    }
    // Tests GGetCountriesInRegion report returns more than zero elements
    @Test
    void testGetCountriesInRegion()
    {
        List<Country> result = new Countries(app.Conn).getAllCountriesInRegionByDescPopulation("Central Africa");
        assertTrue(result.size() > 0);
    }
    // Tests GetAllCountriesInContinentByDescPopulation report returns more than zero elements
    @Test
    void testGetAllCountriesInContinentByDescPopulation()
    {
        List<Country> result = new Countries(app.Conn).getAllCountriesInContinentByDescPopulation("Africa");
        assertTrue(result.size() > 0);
    }
    // Tests NPopulatedCountriesWithinRegion report returns more than zero elements
    @Test
    void testNPopulatedCountriesWithinRegion()
    {
        List<Country> result = new Countries(app.Conn).getNPopulatedCountriesInRegionByDescPopulation(5, "Central Africa");
        assertTrue(result.size() > 0);
    }
    // Tests NPopulatedCitiesWithinRegion report returns more than zero elements
    @Test
    void testNPopulatedCitiesWithinRegion()
    {
        List<City> result = new Cities(app.Conn).getNPopulatedCitiesWithinRegionByDescPopulation(5, "Central Africa");
        assertTrue(result.size() > 0);
    }
    // Tests GetPopulationByRegion report returns more than zero elements
    @Test
    void testGetPopulationByRegion()
    {
        List<Population> result = new com.napier.world.reports.Population(app.Conn).getPopulationByRegion();
        assertTrue(result.size() > 1);
    }
    // Tests GetPopulationByCountry report returns more than zero elements
    @Test
    void testGetPopulationByCountry()
    {
        List<Population> result = new com.napier.world.reports.Population(app.Conn).getPopulationByCountries();
        assertTrue(result.size() > 1);
    }
    // Tests GetPopulationByContinent report returns more than zero elements
    @Test
    void testGetPopulationByContinent()
    {
        List<Population> result = new com.napier.world.reports.Population(app.Conn).getPopulationByContinent();
        assertTrue(result.size() > 1);
    }
    // Tests GetPopulationOfWorld report returns more than zero elements
    @Test
    void testGetPopulationOfWorld()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfWorld();
        assertNotNull(pop);
    }
    // Tests GetPopulationOfAContinent report returns more than zero elements
    @Test
    void testGetPopulationOfAContinent()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfAContinent("Africa");
        assertNotNull(pop);
    }
    // Tests GetPopulationOfARegion report returns more than zero elements
    @Test
    void testGetPopulationOfARegion()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfARegion("South America");
        assertNotNull(pop);
    }
    // Tests GetPopulationOfACountry report returns more than zero elements
    @Test
    void testGetPopulationOfACountry()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfACountry("Hungary");
        assertNotNull(pop);
    }
    // Tests GetPopulationOfADistrict report returns more than zero elements
    @Test
    void testGetPopulationOfADistrict()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfADistrict("Herat");
        assertNotNull(pop);
    }
    // Tests GetPopulationOfACity report returns more than zero elements
    @Test
    void testGetPopulationOfACity()
    {
        PopulationInfo pop = new PopulationInfos(app.Conn).getPopulationOfACity("London");
        assertNotNull(pop);
    }
    // Tests GetAllOrNCapitalCitiesReturnsFive report returns more than zero elements
    @Test
    void testGetAllOrNCapitalCitiesReturnsFive()
    {
        List<CapitalCity> capitals = new Cities(app.Conn).getAllOrNCapitalCitiesInWorldByDescPopulation(5);
        assertTrue(capitals.size() == 5);
    }
    // Tests GGetAllOrNCapitalCitiesReturnsAll report returns more than zero elements
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
    // Tests GetCitiesByDescPopulation report returns more than zero elements
    @Test
    void testGetCitiesByDescPopulation()
    {
        List<City> cities = new Cities(app.Conn).getAllCitiesInWorldByDescPopulation();
        assertTrue(cities.size() > 0);
    }
    // Tests GetNPopulatedCities report returns more than zero elements
    @Test
    void testGetNPopulatedCities()
    {
        List<City> cities = new Cities(app.Conn).getNPopulatedCitiesInWorldByDescPopulation(5);
        assertTrue(cities.size() == 5);
    }
    // Tests GetAllCitiesInRegionByDescPopulation report returns more than zero elements
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

    // Tests GetNPopulatedCitiesInContinent report returns more than zero elements
    @Test
    void testGetNPopulatedCitiesInContinent()
    {
        List<City> cities = new Cities(app.Conn).getNPopulatedCitiesInContinentByDescPopulation(5, "Asia");
        assertTrue(cities.size() == 5);
    }
    // Tests GetNPopulatedCapitalCitiesInRegion report returns more than zero elements
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
    // Tests GetAllCitiesInContinentByDescPopulation report returns more than zero elements
    @Test
    void testGetAllCitiesInContinentByDescPopulation()
    {
        List<City> cities = new Cities(app.Conn).getAllCitiesInContinentByDescPopulation("Africa");
        assertTrue(cities.size() > 0);
    }
    // Tests GetAllCitiesInCountryByDescPopulation report returns more than zero elements
    @Test
    void testGetAllCitiesInCountryByDescPopulation()
    {
        List<City> cities = new Cities(app.Conn).getAllCitiesInCountryByDescPopulation("Hungary");
        assertTrue(cities.size() > 0);
    }
    // Tests GetNPopulatedCitiesInDistrict report returns more than zero elements
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
    // Tests GetNPopulatedCitiesInCountry report returns more than zero elements
    @Test
    void testGetNPopulatedCitiesInCountry()
    {
        List<City> cities = new Cities(app.Conn).getNPopulatedCitiesInCountryByDescPopulation(10, "Hungary");
        assertTrue(cities.size() > 0);
    }
}
