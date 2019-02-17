package world.reports;

import world.connection.Connection;
import world.models.City;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Cities_by_Population {


    private static Object City;

    public static <city> List<city> getCities() {

        try

        {// Initializes a connection to the database
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT ci.Name, ci.Population" +
                    "FROM city ci " +
                    "ORDER BY ci.Population DESC;";

            List<city> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

                    //Continue to do until there are unprocessed records
            final List<city> result1 = result;
            while (rSet.next())
            {
                String name = rSet.getString("Name");
                int population = rSet.getInt("Population");

                City city = new City();
                city.Name = name;
                city.population = population;

                result1.add((city) city);
            }

        System.out.println("Finished reading data");
            return result1;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details");
            return null;
        }


    }
}