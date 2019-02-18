package world.reports;

import world.connection.Connection;
import world.models.CapitalCity;
import world.models.Country;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Capital_Cities_by_Population {
    private static Object CapitalCity;
    private static Object Country;

    public static <result> List<CapitalCity> getCapitalCities() {
        try {

            // Initializes a connection to the database
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT c.Capital ci.Population" +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "ORDER BY c.Population DESC;";

            List<CapitalCity> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

            {
                String capital = ((ResultSet) rSet).getString("Capital");
                int population = rSet.getInt("Population");

                Country country = new Country();
                country.Capital = capital;

                CapitalCity capitalCity = new CapitalCity();
                capitalCity.Population = population;


                final boolean add = result.add((world.models.CapitalCity) CapitalCity);
                result.add((world.models.CapitalCity) Country);
            }

            System.out.println("Finished reading data");
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details");
            return null;


        }

    }
}
