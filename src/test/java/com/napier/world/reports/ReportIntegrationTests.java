package com.napier.world.reports;

import com.napier.sem.App;
import com.napier.world.models.City;
import com.napier.world.models.Country;
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
        List<City> result = new Cities(app.Conn).getPopulationOfCity("Edinburgh");
        assertTrue(result.size() > 0);
    }

    @Test
    void testGetCountriesByDescendingPopulation()
    {
        List<Country> result = new Countries(app.Conn).getCountriesByDescPopulation();
        assertTrue(result.size() > 0);
    }

    @Test
    void testGetCountriesInRegion()
    {
        List<Country> result = new Countries(app.Conn).getCountriesInRegion("Central Africa");
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

}
