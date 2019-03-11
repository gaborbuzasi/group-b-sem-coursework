package reports;

import com.napier.world.reports.Countries;
import org.junit.jupiter.api.Test;

public class CountriesTests
{
    @Test
    void processCountriesResultsNull()
    {
        Countries.processResults(null);
    }
}
