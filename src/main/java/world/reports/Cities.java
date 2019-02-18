package world.reports;

import world.connection.Connection;
import world.models.CapitalCity;
import world.models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cities {
    public static List<CapitalCity> getCapitalCities(int numberOfRows)
    {
        try
        {
            // Initializes a connection to the database
            Connection conFactory = new Connection();

            String strSelect = "SELECT ci.Name AS Capital, c.Name AS Country, ci.Population AS Population " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = conFactory.conn.prepareStatement(strSelect);
            stmt.setInt(1, numberOfRows);

            List<CapitalCity> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

            // Do until there's unprocessed records existing
            while (rSet.next())
            {
                String capital = rSet.getString("Capital");
                String country = rSet.getString("Country");
                int population = rSet.getInt("Population");

                CapitalCity city = new CapitalCity();
                city.Name = capital;
                city.Country = country;
                city.Population = population;

                result.add(city);
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