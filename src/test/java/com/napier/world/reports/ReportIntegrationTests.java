package com.napier.world.reports;

import com.napier.sem.App;
import com.napier.world.models.Country;
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
        app.connectToDatabase("localhost");
    }

    @Test
    void testGetCountriesByDescendingPopulation()
    {
        List<Country> result = new Countries(app.Conn).getCountriesByDescPopulation();
        assertTrue(result.size() > 0);
    }

}
