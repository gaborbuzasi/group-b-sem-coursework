package com.napier.sem;

import world.models.Country;

import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        List<Country> countries = Countries.getCountries();
        System.out.println("Retrieved data with number of rows " + countries.size());

        if (countries != null)
        {
            for (Country c : countries)
            {
                System.out.println("Name: "  + c.Name + " - Population: " + c.Population);
            }
        }
        else
        {
            System.out.println("Something bad happened...");
        }


        System.out.println("Application stops");
    }
}