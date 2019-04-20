package com.napier.world.reports;

import org.junit.jupiter.api.Test;

/**
 * Class containing all unit tests for methods that were possible to test with unit testing
 */
public class ReportTests
{
    @Test
    void processCitiesResultsNull()
    {
        Cities.processResults(null);
    }

    @Test
    void processCountriesResultsNull()
    {
        Countries.processResults(null);
    }

    @Test
    void processPopulationsResultsNull()
    {
        Population.processResults(null);
    }

    @Test
    void processPopulationInfosResultsNull()
    {
        PopulationInfos.processResults(null);
    }
}
