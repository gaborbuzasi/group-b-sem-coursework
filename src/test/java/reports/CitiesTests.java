package reports;

import com.napier.world.reports.Cities;
import org.junit.jupiter.api.Test;

class CitiesTests
{
    @Test
    void processCitiesResultsNull()
    {
        Cities.processResults(null);
    }
}
