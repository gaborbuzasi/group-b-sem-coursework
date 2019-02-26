package world.reports;

import world.connection.Connection;
import world.models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Population {
    public static List<world.models.Population> getPopulationOfContinent(String continent)
    {
        try
        {
            Connection conFactory = new Connection();

            String sqlStatement = "SELECT continent AS Name, SUM(Population) AS TotalPopulation " +
                                  "FROM country " +
                                  "WHERE continent = ? " +
                                  "GROUP BY continent";

            PreparedStatement stmt = conFactory.conn.prepareStatement(sqlStatement);
            stmt.setString(1, continent);

            ResultSet rSet = stmt.executeQuery();




            return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    private static List<world.models.Population> processResults(ResultSet rSet)
    {
        List<world.models.Population> result = new ArrayList<>();

        try
        {
            // Do until there's unprocessed records existing
            while (rSet.next())
            {
                String name = rSet.getString("Name");
                int population = rSet.getInt("TotalPopulation");

                world.models.Population pop = new world.models.Population();
                pop.Name = name;
                pop.TotalPopulation = population;

                result.add(pop);
            }

            System.out.println("Finished reading data");
            return result;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
