package com.napier.sem;

import com.napier.world.models.Country;
import com.napier.world.reports.Countries;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Class responsible for loading data for reports of countries type
 * and passing the loaded data to the view
 */
@Controller
@RequestMapping("/countries")
public class CountriesController {

    @RequestMapping("/all-in-world")
    @NameAnnotation("All the countries in the world organised by largest population to smallest")
    public String AllCountriesInWorld(Model model) {

        List<Country> countries = new Countries(App.Conn).getAllCountriesInWorldByDescPopulation();

        model.addAttribute("countries", countries);
        model.addAttribute("title", "Countries in world");
        return "countries";
    }

    @RequestMapping("/all-in-continent")
    @NameAnnotation("All the countries in a continent organised by largest population to smallest")
    public String AllCountriesInContinent(@RequestParam String continent, Model model) {

        List<Country> countries = new Countries(App.Conn).getAllCountriesInContinentByDescPopulation(continent);

        model.addAttribute("countries", countries);
        model.addAttribute("title", "Countries in " + continent);
        return "countries";
    }

    @RequestMapping("/all-in-region")
    @NameAnnotation("All the countries in a region organised by largest population to smallest")
    public String AllCountriesInRegion(@RequestParam String region, Model model) {

        List<Country> countries = new Countries(App.Conn).getAllCountriesInRegionByDescPopulation(region);

        model.addAttribute("countries", countries);
        model.addAttribute("title", "Countries in " + region);
        return "countries";
    }

    @RequestMapping("/top-n-populated-in-world")
    @NameAnnotation("The top N populated countries in the world where N is provided by the user")
    public String TopNPopulatedCountriesInWorld(@RequestParam int numberOfCountries, Model model) {
        List<Country> countries = new Countries(App.Conn).getNPopulatedCountriesInWorldByDescPopulation(numberOfCountries);
        model.addAttribute("countries", countries);
        model.addAttribute("title", "Top " + numberOfCountries + " populated countries in the world");
        return "countries";
    }

    @RequestMapping("/top-n-populated-in-continent")
    @NameAnnotation("The top N populated countries in a continent where N is provided by the user")
    public String TopNPopulatedCountriesInContinent(@RequestParam String continent, @RequestParam int numberOfCountries, Model model) {
        List<Country> countries = new Countries(App.Conn).getNPopulatedCountriesInContinentByDescPopulation(numberOfCountries, continent);
        model.addAttribute("countries", countries);
        model.addAttribute("title", "Top " + numberOfCountries + " populated countries in continent " + continent);
        return "countries";
    }

    @RequestMapping("/top-n-populated-in-region")
    @NameAnnotation("The top N populated countries in a region where N is provided by the user")
    public String TopNPopulatedCountriesInRegion(@RequestParam String region, @RequestParam int numberOfCountries, Model model) {
        List<Country> countries = new Countries(App.Conn).getNPopulatedCountriesInRegionByDescPopulation(numberOfCountries, region);
        model.addAttribute("countries", countries);
        model.addAttribute("title", "Top " + numberOfCountries + " populated countries in region " + region);
        return "countries";
    }


}
