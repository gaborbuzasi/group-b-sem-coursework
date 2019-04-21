package com.napier.world.reports;

import com.napier.world.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains reports for population type information requirements
 */
public class Population {
    Connection Conn;

    /**
     * Initializes class with a connection to the database passed as argument
     * @param conn Connection to database to run queries against
     */
    public Population(Connection conn)
    {
        Conn = conn;
    }

    /**
     * Retrieves the population for each region in the world
     * @return
     */
    public List<com.napier.world.models.Population> getPopulationByRegion()
    {
        try
        {
            String strSelect = "select c.Region AS Name, " +
                    " SUM(c.Population) AS TotalPopulation, " +
                    " (CONCAT(CAST(ROUND((SELECT SUM(ci.Population) " +
                    "FROM city ci " +
                    "LEFT JOIN country co on ci.CountryCode = co.Code " +
                    "WHERE co.Region = c.Region " +
                    "GROUP BY co.Region) / SUM(c.Population) * 100, 2) AS CHAR(10)), '%')) AS LivingInCities, " +
                    "(CONCAT(CAST(ROUND((SELECT SUM(c.Population) - SUM(ci.Population) " +
                    "FROM city ci " +
                    "LEFT JOIN country co on ci.CountryCode = co.Code " +
                    "WHERE co.Region = c.Region " +
                    "GROUP BY co.Region) / SUM(c.Population) * 100, 2) AS CHAR(10)), '%')) AS NotLivingInCities " +
                    "from country c " +
                    "group by c.Region";

            Statement stmt = Conn.conn.createStatement();

            ResultSet rSet = stmt.executeQuery(strSelect);

            return processResults(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region details");
            return null;
        }
    }

    /**
     * Retrieves the population for each country in the world
     * @return
     */
    public List<com.napier.world.models.Population> getPopulationByCountries()
    {
        try
        {
            String strSelect = "select c.Name, " +
                    "       c.Population AS TotalPopulation, " +
                    "       CONCAT(CAST(ROUND(SUM(ci.Population) / c.Population * 100, 2) AS CHAR(10)), '%') AS LivingInCities, " +
                    "       CONCAT(CAST(ROUND((c.Population - SUM(ci.Population)) / c.Population * 100, 2) AS CHAR(10)), '%') AS NotLivingInCities " +
                    "from country c " +
                    "left join city ci on c.Code = ci.CountryCode " +
                    "group by c.code";

            Statement stmt = Conn.conn.createStatement();

            ResultSet rSet = stmt.executeQuery(strSelect);

            return processResults(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Retrieves the population for each continent in the world
     * @return
     */
    public List<com.napier.world.models.Population> getPopulationByContinent()
    {
        try
        {
            String strSelect = "select c.Continent AS Name, " +
                    "SUM(c.Population) AS TotalPopulation, " +
                    "(CONCAT(CAST(ROUND((SELECT SUM(ci.Population) " +
                    "FROM city ci " +
                    "LEFT JOIN country co on ci.CountryCode = co.Code " +
                    "WHERE co.Continent = c.Continent " +
                    "GROUP BY co.Continent) / SUM(c.Population) * 100, 2) AS CHAR(10)), '%')) AS LivingInCities, " +
                    "CONCAT(CAST(ROUND((SELECT SUM(c.Population) - SUM(ci.Population) " +
                    "FROM city ci \n" +
                    "LEFT JOIN country co on ci.CountryCode = co.Code " +
                    "WHERE co.Continent = c.Continent " +
                    "GROUP BY co.Continent) / SUM(c.Population) * 100, 2) AS CHAR(10)), '%') AS NotLivingInCities " +
                    "from country c " +
                    "group by c.Continent";


            Statement stmt = Conn.conn.createStatement();

            ResultSet rSet = stmt.executeQuery(strSelect);

            return processResults(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Serializes dynamic result set returned from SQL to Population model objects
     * @param rSet Result set returned from the database
     * @return List of Population model objects
     */
    public static List<com.napier.world.models.Population> processResults(ResultSet rSet)
    {
        if (rSet == null)
        {
            System.out.println("No records to process");
            return null;
        }

        List<com.napier.world.models.Population> result = new ArrayList<>();

        try
        {
            // Do until there's unprocessed records existing
            while (rSet.next())
            {
                com.napier.world.models.Population pop = new com.napier.world.models.Population();

                pop.Name = rSet.getString("Name");
                pop.TotalPopulation = rSet.getFloat("TotalPopulation");
                pop.LivingInCities = rSet.getString("LivingInCities");
                pop.NotLivingInCities = rSet.getString("NotLivingInCities");

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




