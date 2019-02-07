package world.reports;

import world.connection.Connection;
import world.models.Country;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Countries {
    public static List<Country> getCountries()
    {
        try
        {
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                                "FROM countries c " +
                                "LEFT JOIN city ci on c.Capital = ci.ID;";

            List<Country> result = new ArrayList<Country>();

            ResultSet rSet = stmt.executeQuery(strSelect);

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

            return result;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
