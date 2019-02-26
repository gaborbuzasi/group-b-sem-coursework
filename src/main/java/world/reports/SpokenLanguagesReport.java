package world.reports;


import world.connection.Connection;
import world.models.SpokenLanguages;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpokenLanguagesReport
{
    public static List<SpokenLanguages> getCountriesByDescPopulation()
    {
        try
        {
            // Initializes a connection to the database
            Connection conFactory = new Connection();
            Statement stmt = conFactory.conn.createStatement();

            String strSelect = "SELECT DISTINCT cl.Language, SUM(c.Population) AS Population, SUM((SELECT (c.Population/SUM(Population)) FROM country)) * 100 AS Percentage " +
                    "FROM countrylanguage cl " +
                    "LEFT JOIN country c on cl.CountryCode = c.Code " +
                    "GROUP BY cl.Language " +
                    "HAVING cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                    "ORDER BY Percentage DESC ";

            List<SpokenLanguages> result = new ArrayList<>();

            ResultSet rSet = stmt.executeQuery(strSelect);

            while (rSet.next())
            {
                String language = rSet.getString("Language");
                int population = rSet.getInt("Population");
                float percentage = rSet.getFloat("Percentage");

                SpokenLanguages lang = new SpokenLanguages();
                lang.Language = language;
                lang.Population = population;
                lang.Percentage = percentage;

                result.add(lang);
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
