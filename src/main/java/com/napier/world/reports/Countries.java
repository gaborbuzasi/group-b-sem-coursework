package com.napier.world.reports;

import com.napier.world.connection.Connection;
import com.napier.world.connection.ConnectionBuilder;
import com.napier.world.models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains reports for countries type information requirements
 */
public class Countries {
    Connection Conn;

    /**
     * Initializes class with a connection to the database passed as argument
     * @param conn Connection to database to run queries against
     */
    public Countries(Connection conn) {
        Conn = conn;
    }

    public List<Country> getAllCountriesInWorldByDescPopulation() {
        try {
            // Initializes a connection to the database
            Statement stmt = Conn.conn.createStatement();

            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "ORDER BY c.Population DESC";

            List<Country> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

            return processResults(rSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public List<Country> getAllCountriesInContinentByDescPopulation(String continent)
    {
        try
        {
            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE c.Continent = ? " +
                    "ORDER BY c.Population DESC ";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, continent);

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

    public List<Country> getAllCountriesInRegionByDescPopulation(String region)
    {
        try
        {
            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE c.Region = ? " +
                    "ORDER BY c.Population DESC ";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, region);

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

    public List<Country> getNPopulatedCountriesInWorldByDescPopulation(int numberOfRows) {
        try {
            // Initializes a connection to the database
            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE c.Population > 0 " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setInt(1, numberOfRows);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public List<Country> getNPopulatedCountriesInContinentByDescPopulation(int numberOfRows, String continent) {
        try {
            String strSelect = "SELECT c.Name, c.Population, c.Region, c.Code, c.Continent, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE c.Continent = ? " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);

            stmt.setString(1, continent);
            stmt.setInt(2, numberOfRows);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public List<Country> getNPopulatedCountriesInRegionByDescPopulation(int numberOfRows, String region) {
        try {
            String strSelect = "SELECT c.Name, c.Population, c.Region, c.Code, c.Continent, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE c.Region = ? " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);

            stmt.setString(1, region);
            stmt.setInt(2, numberOfRows);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Serializes dynamic result set returned from SQL to Country objects
     * @param rSet Result set returned from the database
     * @return List of Country objects
     */
    public static List<Country> processResults(ResultSet rSet) {
        if (rSet == null) {
            System.out.println("No records to process");
            return null;
        }

        List<Country> result = new ArrayList<>();

        try {
            // Do until there's unprocessed records existing
            while (rSet.next()) {
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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
