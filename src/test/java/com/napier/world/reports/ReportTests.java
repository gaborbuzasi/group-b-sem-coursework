package com.napier.world.reports;

//Contains all integration tests for reports

import org.junit.jupiter.api.Test;

public class ReportTests
{
    // Tests processCitiesResults successfully processes null value
    @Test
    void processCitiesResultsNull()
    {
        Cities.processResults(null);
    }
    // Tests processCountriesResults successfully processes null value
    @Test
    void processCountriesResultsNull()
    {
        Countries.processResults(null);
    }
    // Tests processPopulationResults successfully processes null value
    @Test
    void processPopulationsResultsNull()
    {
        Population.processResults(null);
    }
    // Tests processPopulationInfos successfully processes null value
    @Test
    void processPopulationInfosResultsNull()
    {
        PopulationInfos.processResults(null);
    }
}
