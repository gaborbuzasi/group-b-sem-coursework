package com.napier.world.reports;

import org.junit.jupiter.api.Test;

class ReportTests
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
}
