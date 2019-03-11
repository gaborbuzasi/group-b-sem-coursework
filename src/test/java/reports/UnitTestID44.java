package reports;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import world.models.CapitalCity;
import world.reports.Cities;


import java.util.ArrayList;

class City
{

    @BeforeAll
    static void init()
    {
        Cities getAllOrNCapitalCities = new Cities();
    }

    @Test
    void getAllOrNCapitalCitiesNull()
    {
        Cities.getAllOrNCapitalCities(null);
    }

    @Test
    void getAllOrNCapitalCitiesTestEmpty()
    {
        ArrayList<CapitalCity> Cities = new ArrayList<CapitalCity>();
        City.getAllOrNCapitalCities(Cities);
    }

    private static void getAllOrNCapitalCities(ArrayList<CapitalCity> cities) {
    }

    @Test
    void getAllOrNCapitalCities()
    {
        ArrayList<CapitalCity> Cities = new ArrayList<CapitalCity>();
        CapitalCity cc = new CapitalCity();
        cc.Name = "Paris";
        cc.Name = "France";
        cc.Population = 2125246;
        CapitalCity.add(cc);

}

    private static void add(CapitalCity cc) {
    }
}
