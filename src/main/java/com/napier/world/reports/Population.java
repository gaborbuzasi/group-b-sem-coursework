package com.napier.world.reports;

import com.mysql.cj.protocol.Resultset;
import com.napier.world.connection.Connection;
import com.napier.world.models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Population {
    Connection Conn;

    public Population(Connection conn)
    {
        Conn = conn;
    }

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
                    "GROUP BY co.Continent) / SUM(c.Population) * 100, 2) AS CHAR(10)), '%')) AS NotLivingInCities " +
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
                pop.LivinginCities = rSet.getString("LivingInCities");
                pop.NotLivinginCities = rSet.getString("NotLivingInCities");

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




