package world.reports;

import world.connection.Connection;
import world.models.Country;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Countries {
    public static List<Country> getCountries()
    {
        try
        {
            // Initializes a connection to the database
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                                "FROM country c " +
                                "LEFT JOIN city ci on c.Capital = ci.ID " +
                                "ORDER BY c.Population DESC;";

            List<Country> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

            // Do until there's unprocessed records existing
            while (rSet.next())
            {
                String code = rSet.getString("Code");
                String name = rSet.getString("Name");
                String continent = rSet.getString("Continent");
                String region = rSet.getString("Region");
                int population = rSet.getInt("Population");
                String capital = rSet.getString("Capital");

                Country country = new Country();
                country.Code = code;
                country.Name = name;
                country.Continent = continent;
                country.Region = region;
                country.Population = population;
                country.Capital = capital;

                result.add(country);
            }

            System.out.println("Finished reading data");
            return result;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
