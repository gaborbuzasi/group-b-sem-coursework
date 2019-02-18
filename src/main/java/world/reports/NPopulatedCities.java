package world.reports;

import world.connection.Connection;
import world.models.City;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NPopulatedCities {
    public static List<City> getCities()
    {
        try
        {
            // Initializes a connection to the database
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT ci.Name, ci.Population" +
                    "FROM city ci " +
                    "ORDER BY ci.Population DESC;" +
                    "LIMIT N";

            List<City> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

            //Do until there are no unprocessed records exisiting within the N limit
            while (rSet.next())
            {
                String name = rSet.getString("Name");
                int population = rSet.getInt("Population");

                City city = new City();
                city.Name = name;
                city.population = population;

                result.add(city);
            }

            System.out.println("Finished reading data");
            return result;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }
}