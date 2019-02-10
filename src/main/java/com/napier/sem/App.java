package com.napier.sem;

import world.models.Country;
import world.reports.Countries;

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
                System.out.println(c.Name);
            }
        }
        else
        {
            System.out.println("Something bad happened...");
        }

    }
}