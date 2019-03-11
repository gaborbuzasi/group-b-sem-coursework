package com.napier.world.reports;

import com.napier.world.connection.Connection;
import com.napier.world.models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Countries {
    public static List<Country> getCountriesByDescPopulation()
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

            return processResults(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public static List<Country> getNPopulatedCountries(int numberOfRows)
    {
        try
        {
            // Initializes a connection to the database
            Connection conFactory = new Connection();

            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE c.Population > 0 " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT ?";

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

    public static List<Country> processResults(ResultSet rSet)
    {
        if (rSet == null)
        {
            System.out.println("No records to process");
            return null;
        }

        List<Country> result = new ArrayList<>();

        try
        {
            // Do until there's unprocessed records existing
            while (rSet.next())
            {
                String code = rSet.getString("Code");
                String name = rSet.getString("Name");
                String continent = rSet.getString("Continent");
                String region = rSet.getString("Region");
                int population = rSet.getInt("Population");
                String capital = rSet.getString("Capital");

                Country country = new Country();
                country.Code = code;
                country.Name = name;
                country.Continent = continent;
                country.Region = region;
                country.Population = population;
                country.Capital = capital;

                result.add(country);
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
