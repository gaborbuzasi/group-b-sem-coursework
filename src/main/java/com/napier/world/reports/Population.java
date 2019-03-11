package com.napier.world.reports;

import java.util.List;

public class Population {
    public static List<com.napier.world.models.Population> getPopulationOfContinent(String continent)
    {
        try
        {
            //TODO: implement this

            return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
