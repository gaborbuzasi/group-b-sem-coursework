package com.napier.world.reports;

import com.napier.world.connection.Connection;

import java.util.List;

public class Population {
    Connection Conn;

    public Population(Connection conn)
    {
        Conn = conn;
    }

    public List<com.napier.world.models.Population> getPopulationOfContinent(String continent)
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
