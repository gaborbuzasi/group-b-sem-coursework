package world.reports;

import world.connection.Connection;
import world.models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Population {
    public static List<world.models.Population> getPopulationOfContinent(String continent)
    {
        try
        {
            //TODO: implement this

            return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }


    public static List<world.models.Population>
    {
        try
        {
            // Initializes a connection to the database
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT DISTINCT ((SUM(c.Population)-(" +
                    "SELECT DISTINCT SUM(ci.Population) " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "GROUP BY c.Continent ) AS PopulationNotInCities)" +
            ("SELECT DISTINCT SUM(ci.Population) AS Population, c.Continent " +
                    "FROM country c " +
                "LEFT JOIN city ci on c.Capital = ci.ID " +
                "GROUP BY c.Continent )" +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "GROUP BY c.Continent ");


            PreparedStatement stmt = conFactory.conn.prepareStatement(strSelect);
            stmt.setInt(1, numberOfRows);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}




