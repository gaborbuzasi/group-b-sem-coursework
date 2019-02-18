package com.napier.sem;

import world.models.CapitalCity;
import world.models.Country;
import world.reports.Cities;
import world.reports.Countries;

import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        System.out.println("App starting...");
        List<Country> countries = Countries.getCountries();
        List<CapitalCity> capitalCities = Cities.getCapitalCities(5);
        //System.out.println("Retrieved data with number of rows " + countries.size());

        //List<Country> countries = null;
        //List<CapitalCity> capitalCities = null;

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

        if (capitalCities != null)
        {
            System.out.println();
            System.out.println("Printing out Capital cities: ");
            for (CapitalCity c : capitalCities)
            {
                System.out.println("Name: " + c.Name + " - Country: " + c.Country + " - Population: " + c.Population);
            }
        }

        System.out.println("Application stops");
    }
}