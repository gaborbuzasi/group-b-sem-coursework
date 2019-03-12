package com.napier.world.reports;

import com.napier.world.connection.Connection;
import com.napier.world.connection.ConnectionBuilder;
import com.napier.world.models.City;
import com.napier.world.models.CapitalCity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cities {
    Connection Conn;

    public Cities(Connection conn) {
        Conn = conn;
    }

    public List<CapitalCity> getAllOrNCapitalCities(Integer numberOfRows)
    {
        try
        {
            // Initializes a connection to the database
            String strSelect = "SELECT ci.Name AS Capital, c.Name AS Country, ci.Population AS Population " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "ORDER BY c.Population DESC ";

            if (numberOfRows != null) {
                strSelect += " LIMIT ?";
            }

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);

            if (numberOfRows != null) {
                stmt.setInt(1, numberOfRows);
            }

            List<CapitalCity> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery();

            // Do until there's unprocessed records existing
            while (rSet.next())
            {
                String capital = rSet.getString("Capital");
                String country = rSet.getString("Country");
                int population = rSet.getInt("Population");

                CapitalCity city = new CapitalCity();
                city.Name = capital;
                city.Country = country;
                city.Population = population;

                result.add(city);
            }

            System.out.println("Finished reading data");
            return result;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public List<City> getCitiesByDescPopulation() {
        try
        {
            // Initializes a connection to the database
            Statement stmt = Conn.conn.createStatement();

            String strSelect = "SELECT ci.Name, c.Name AS Country, ci.District, ci.Population " +
                               "FROM city ci " +
                               "LEFT JOIN country c on ci.CountryCode = c.Code " +
                               "ORDER BY ci.Population DESC;";

            ResultSet rSet = stmt.executeQuery(strSelect);

            return processResults(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details");
            return null;
        }
    }

    public List<City> getNPopulatedCities(int numberOfRows) {
        try
        {
            // Initializes a connection to the database
            String strSelect =  "SELECT ci.Name, c.Name as Country, ci.District, ci.Population " +
                                "FROM city ci " +
                                "LEFT JOIN country c on ci.CountryCode = c.Code " +
                                "WHERE ci.Population > 0 " +
                                "ORDER BY ci.Population DESC " +
                                "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setInt(1, numberOfRows);
            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details");
            return null;
        }
    }
    public List<City> getPopulationOfCity(String cityName) {
        try
        {            
                    String strSelect =  "SELECT ci.Name as Name, " +
                                               "ci.Population, " +
                            "ci.District ," +
                            "ci.CountryCode AS Country " +
                    "FROM city ci " +
                    "WHERE ci.Name = ? " +
                    "ORDER BY ci.Population DESC " ;

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, cityName);
            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city population details");
            return null;
        }
    }

    public static List<City> processResults(ResultSet rSet)
    {
        if (rSet == null)
        {
            return null;
        }

        List<City> results = new ArrayList<>();

        try
        {
            //Continue to do until there are unprocessed records
            while (rSet.next())
            {
                String name = rSet.getString("Name");
                String country = rSet.getString("Country");
                String district = rSet.getString("District");
                int population = rSet.getInt("Population");

                City city = new City();
                city.Name = name;
                city.Country = country;
                city.District = district;
                city.Population = population;

                results.add(city);
            }

            System.out.println("Finished reading data");
            return results;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to retrieve city details");
            return null;
        }
    }
}