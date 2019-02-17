package world.reports;

import world.connection.Connection;
import world.models.City;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NPopulatedCountries {
    public static List<Country> getCountries()
    {
        try
        {
            // Initializes a connection to the database
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT c.Name, c.Population" +
                    "FROM country c " +
                    "ORDER BY c.Population DESC;";

            List<country> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

            //Do N times
            final List<country> result1 = result;
            while (rSet.next())
            {
                String name = rSet.getString("Name");
                int population = rSet.getInt("Population");

                Country country = new Country();
                country.Name = name;
                country.population = population;

                result.add(country);
            }

            System.out.println("Finished reading data");
            return result1;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details");
            return null;
        }


    }
}