package com.napier.world.reports;

import com.napier.world.connection.Connection;
import com.napier.world.models.Population;
import com.napier.world.models.PopulationInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.napier.world.reports.Population.processResults;

/*
Contains reports for populationInfo type requirements
 */
public class PopulationInfos
{
    Connection Conn;
    public PopulationInfos(Connection conn)
    {
        Conn = conn;
    }

    public PopulationInfo getPopulationOfWorld()
    {
        try
        {
            /*
            Calculates the population of the world
            */
            String strSelect = "SELECT SUM(c.Population) AS Population " +
                               "FROM country c";

            Statement stmt = Conn.conn.createStatement();

            ResultSet rSet = stmt.executeQuery(strSelect);

            return processResults(rSet);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get continent details");
            return null;
        }
    }

    public PopulationInfo getPopulationOfARegion(String region)
    {
        try
        {
            /*
            Calculates the population of a region where the region is entered by the user
            */
            String strSelect = "SELECT SUM(c.Population) AS Population " +
                               "FROM country c " +
                               "WHERE c.Region = ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, region);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get region details");
            return null;
        }
    }

    public PopulationInfo getPopulationOfACountry(String country)
    {
        try
        {
            /*
            Retrieves the population of a country where the country is entered by the user
            */
            String strSelect = "SELECT SUM(c.Population) AS Population " +
                    "FROM country c " +
                    "WHERE c.Name = ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, country);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public PopulationInfo getPopulationOfADistrict(String district)
    {
        try
        {
            /*
            Retrieves the population of a district where the district is entered by the user
            */
            String strSelect = "SELECT SUM(c.Population) AS Population " +
                    "FROM city c " +
                    "WHERE c.District = ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, district);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get district details");
            return null;
        }
    }

    public PopulationInfo getPopulationOfACity(String city)
    {
        try
        {
            /*
            Retrieves the population of a city where the city is entered by the user
            */
            String strSelect = "SELECT SUM(c.Population) AS Population " +
                    "FROM city c " +
                    "WHERE c.Name = ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, city);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public PopulationInfo getPopulationOfAContinent(String continent)
    {
        try
        {
            /*
            Calculates the population of a continent where the continent is entered by the user
            */
            String strSelect = "SELECT SUM(c.Population) AS Population " +
                    "FROM country c " +
                    "WHERE c.Continent = ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, continent);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get world details");
            return null;
        }
    }
    
    /*
     Serialises the SQL result into capital cities object
     */
    public static PopulationInfo processResults(ResultSet rSet)
    {
        if (rSet == null)
        {
            System.out.println("No records to process");
            return null;
        }

        try
        {
            // Do until there's unprocessed records existing
            while (rSet.next())
            {
                PopulationInfo pop = new PopulationInfo();

                pop.Population = rSet.getLong("Population");

                return pop;
            }

            return null;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
