package com.napier.sem;

import world.models.Country;
import world.reports.Countries;

import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        List<Country> countries = Countries.getCountries();

        for (Country c : countries)
        {
            System.out.print(c.Name);
        }
    }
}