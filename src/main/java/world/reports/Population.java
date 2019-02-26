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
}
