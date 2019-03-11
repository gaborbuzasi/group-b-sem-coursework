package com.napier.sem;

import world.models.CapitalCity;
import world.models.City;
import world.models.Country;
import world.models.SpokenLanguages;
import world.reports.Cities;
import world.reports.Countries;
import world.reports.SpokenLanguagesReport;

import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        System.out.println("App starting...");

        List<CapitalCity> allCapitalCities = Cities.getAllOrNCapitalCities(null);
        List<City> top5PopulatedCities = Cities.getNPopulatedCities(5);
        List<Country> top5PopulatedCountries = Countries.getNPopulatedCountries(5);
        List<Country> allCountriesByDescPopulation = Countries.getCountriesByDescPopulation();
        List<City> allCitiesByDescPopulation = Cities.getCitiesByDescPopulation();
        List<SpokenLanguages> spokenLanguages = SpokenLanguagesReport.getCountriesByDescPopulation();

        if (allCapitalCities != null)
        {
            System.out.println();
            System.out.println("Printing out All capital cities: ");
            for (CapitalCity c : allCapitalCities)
            {
                System.out.println("Name: " + c.Name + " - Country: " + c.Country + " - Population: " + c.Population);
            }
        }

        printCities(top5PopulatedCities, "Top 5 populated cities");
        printCountries(top5PopulatedCountries, "Top 5 populated countries");
        printCountries(allCountriesByDescPopulation, "All countries by descending population");
        printCities(allCitiesByDescPopulation, "All cities by descending population");
        printSpokenLanguages(spokenLanguages);

        System.out.println("Application stops");
    }

    private static void printCountries(List<Country> countries, String message)
    {
        if (countries != null)
        {
            System.out.println();
            System.out.println("Printing out " + message);
            for (Country c : countries)
            {
                System.out.println("Code: " + c.Code +
                        " - Name: "  + c.Name +
                        " - Continent: " + c.Continent +
                        " - Region: " + c.Region +
                        " - Population: " + c.Population +
                        " - Capital: " + c.Capital);
            }
        }
    }

    private static void printCities(List<City> cities, String message)
    {
        System.out.println();
        System.out.println("Printing out " + message);

        if (cities != null)
        {
            for (City c : cities)
            {
                System.out.println("Name: " + c.Name + " - Country: " + c.Country + " - District: " + c.District + " - Population: " + c.Population);
            }
        }
    }

    private static void printSpokenLanguages(List<SpokenLanguages> languages)
    {
        System.out.println();
        System.out.println("Printing out " + "Spoken Languages report");

        if (languages != null)
        {
            for (SpokenLanguages s : languages)
            {
                System.out.println("Language: " + s.Language + " - Population: " + s.Population + " - Percentage: " + s.Percentage);
            }
        }
    }

}