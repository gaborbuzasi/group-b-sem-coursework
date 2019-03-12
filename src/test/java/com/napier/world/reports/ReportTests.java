package com.napier.world.reports;

import org.junit.jupiter.api.Test;

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
