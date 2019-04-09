package com.napier.world.reports;

import com.napier.world.connection.Connection;
import com.napier.world.connection.ConnectionBuilder;
import com.napier.world.models.City;
import com.napier.world.models.CapitalCity;
import com.napier.world.models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
Contains reports for cities type requirements
 */

public class Cities {
    Connection Conn;

    public Cities(Connection conn) {
        Conn = conn;
    }

    public List<CapitalCity> getAllOrNCapitalCities(Integer numberOfRows)
    {
        /*
        Retrieves all or N capital cities in the world where N is a number entered by the user
         */
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

            ResultSet rSet = stmt.executeQuery();

            return processCapitalCitiesResult(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public List<CapitalCity> getAllCapitalCitiesInRegion(String region)
    {
        /*
        Retrieves all capital cities in a region where the region is entered by the user
         */
        try
        {
            // Initializes a connection to the database
            String strSelect = "SELECT ci.Name AS Capital, c.Name AS Country, ci.Population AS Population " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE c.Region = ? " +
                    "ORDER BY c.Population DESC ";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);

            stmt.setString(1, region);

            ResultSet rSet = stmt.executeQuery();

            return processCapitalCitiesResult(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public List<City> NPopulatedCitiesWithinRegion(int numberOfRows, String region)
    {
        /*
        Retrieves N most populous cities in a region where N is a number entered by the user and the region is entered by the user
         */
        try {

            String strSelect = "SELECT ci.Name, ci.Population, ci.District, c.Code AS Country " +
                    "FROM city ci " +
                    "JOIN country c on ci.CountryCode = c.Code " +
                    "WHERE c.Region = ? " +
                    "ORDER BY ci.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setInt(2, numberOfRows);
            stmt.setString(1, region);

            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public List<CapitalCity> getNPopulatedCapitalCitiesInRegion(Integer numberOfRows, String region)
    {
        /*
        Retrieves N most populous capital cities in a region where N is a number entered by the user and the region is entered by the user
         */
        try
        {
            // Initializes a connection to the database
            String strSelect = "SELECT ci.Name AS Capital, c.Name AS Country, ci.Population AS Population " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE ci.Population > 0 and " +
                    "c.Region = ? " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);

            stmt.setString(1, region);
            stmt.setInt(2, numberOfRows);

            ResultSet rSet = stmt.executeQuery();

            return processCapitalCitiesResult(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public List<CapitalCity> getNPopulatedCapitalCitiesInContinent(Integer numberOfRows, String continent)
    {
        /*
        Retrieves N most populous capital cities in a continent where N is a number entered by the user and the continent is entered by the user
         */
        try
        {
            // Initializes a connection to the database
            String strSelect = "SELECT ci.Name AS Capital, c.Name AS Country, ci.Population AS Population " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Capital = ci.ID " +
                    "WHERE ci.Population > 0 and " +
                    "c.Continent = ? " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);

            stmt.setString(1, continent);
            stmt.setInt(2, numberOfRows);

            ResultSet rSet = stmt.executeQuery();

            return processCapitalCitiesResult(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

       /*
        Serialises the SQL result into capital cities object
         */
    public static List<CapitalCity> processCapitalCitiesResult(ResultSet rSet)
    {
        if (rSet == null)
        {
            return null;
        }

        List<CapitalCity> result = new ArrayList<>();
        // Do until there's unprocessed records existing
        try
        {
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
        catch (SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }

    public List<City> getCitiesByDescPopulation()
    {
        /*
        Retrieves all of the cities in the world in order from most to least populous
         */
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

    public List<City> getNPopulatedCities(int numberOfRows)
    {
        /*
        Retrieves N cities in the world in order from most to least populous where N is a number entered by the user
         */
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

    public List<City> getNPopulatedCitiesInContinent(int numberOfRows, String continent)
    {
        /*
        Retrieves N cities in a continent in order from most to least populous where N is a number entered by the user and the continent is entered by the user
         */
        try
        {
            String strSelect =  "SELECT ci.Name, c.Name as Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "LEFT JOIN country c on ci.CountryCode = c.Code " +
                    "WHERE ci.Population > 0 AND " +
                    "c.Continent = ? " +
                    "ORDER BY ci.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, continent);
            stmt.setInt(2, numberOfRows);
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
    
        public List<City> getNPopulatedCitiesInCountry(int numberOfRows, String country)
        {
            /*
        Retrieves N cities in a city in order from most to least populous where N is a number entered by the user and the country is entered by the user
         */
        try
        {
            String strSelect =  "SELECT ci.Name, c.Name as Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "LEFT JOIN country c on ci.CountryCode = c.Code " +
                    "WHERE ci.Population > 0 AND " +
                    "c.Name = ? " +
                    "ORDER BY ci.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, country);
            stmt.setInt(2, numberOfRows);
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

    public List<City> getNPopulatedCitiesInDistrict(int numberOfRows, String district)
    {
        /*
        Retrieves N cities in a district in order from most to least populous where N is a number entered by the user and the district is entered by the user
         */
        try
        {
            // Initializes a connection to the database
            String strSelect =  "SELECT ci.Name, c.Name as Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "LEFT JOIN country c on ci.CountryCode = c.Code " +
                    "WHERE ci.Population > 0 AND " +
                    "ci.District = ? " +
                    "ORDER BY ci.Population DESC " +
                    "LIMIT ?";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, district);
            stmt.setInt(2, numberOfRows);
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

    public List<City> getAllCitiesInDistrict(String district)
    {
        /*
        Retrieves all cities in a district in order from most to least populous where the district is entered by the user
         */
        try
        {
            // Initializes a connection to the database
            String strSelect =  "SELECT ci.Name, c.Name as Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "LEFT JOIN country c on ci.CountryCode = c.Code " +
                    "WHERE ci.District = ? " +
                    "ORDER BY ci.Population DESC ";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, district);
            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve cities in district details");
            return null;
        }
    }

    public List<City> getPopulationOfCity(String cityName)
    {
         /*
        Retrieves the population of a city where the city is a number entered by the user
         */
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

    public List<City> getAllCitiesInRegionByDescPopulation(String region)
    {
         /*
        Retrieves all cities in a region in order from most to least populous where the region is entered by the user
         */

        try
        {
            String strSelect =  "SELECT ci.Name, c.Name AS Country, ci.District, ci.Population " +
                                "FROM country c " +
                                "LEFT JOIN city ci on c.Code = ci.CountryCode " +
                                "WHERE c.Region = ? " +
                                "ORDER BY ci.Population DESC ";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, region);
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

    public List<City> getAllCitiesInContinentByDescPopulation(String continent)
    {
         /*
        Retrieves all cities in a continent in order from most to least populous where the continent entered by the user
         */
        try
        {
            String strSelect =  "SELECT ci.Name, c.Name AS Country, ci.District, ci.Population " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Code = ci.CountryCode " +
                    "WHERE c.Continent = ? " +
                    "ORDER BY ci.Population DESC ";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, continent);
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

    public List<City> getAllCitiesInCountryByDescPopulation(String country)
    {
         /*
        Retrieves all cities in a country in order from most to least populous where the country is entered by the user
         */
        try
        {
            String strSelect =  "SELECT ci.Name, c.Name AS Country, ci.District, ci.Population " +
                    "FROM country c " +
                    "LEFT JOIN city ci on c.Code = ci.CountryCode " +
                    "WHERE c.Name = ? " +
                    "ORDER BY ci.Population DESC ";

            PreparedStatement stmt = Conn.conn.prepareStatement(strSelect);
            stmt.setString(1, country);
            ResultSet rSet = stmt.executeQuery();

            return processResults(rSet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve country's cities population details");
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