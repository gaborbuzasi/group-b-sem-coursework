package world.reports;

import world.connection.Connection;
import world.models.Country;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Population {
    public static List<world.models.Population> getPopulationOfContinent(String continent)
    {
        try
        {
            // Initializes a connection to the database
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "ORDER BY c.Population DESC";

            List<Country> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

            return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
