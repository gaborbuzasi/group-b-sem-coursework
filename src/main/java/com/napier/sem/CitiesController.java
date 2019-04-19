package com.napier.sem;

import com.napier.world.models.CapitalCity;
import com.napier.world.models.City;
import com.napier.world.reports.Cities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CitiesController {
    @RequestMapping("/all-in-world")
    public String AllCitiesInWorld(Model model) {

        List<City> cities = new Cities(App.Conn).getAllCitiesInWorldByDescPopulation();

        model.addAttribute("cities", cities);
        model.addAttribute("title", "Cities in world");
        return "cities";
    }

    @RequestMapping("/all-in-continent")
    public String AllCitiesInContinent(@RequestParam String continent, Model model) {

        List<City> cities = new Cities(App.Conn).getAllCitiesInContinentByDescPopulation(continent);

        model.addAttribute("cities", cities);
        model.addAttribute("title", "Cities in " + continent);
        return "cities";
    }

    @RequestMapping("/all-in-region")
    public String AllCitiesInRegion(@RequestParam String region, Model model) {

        List<City> cities = new Cities(App.Conn).getAllCitiesInRegionByDescPopulation(region);

        model.addAttribute("cities", cities);
        model.addAttribute("title", "Cities in " + region);
        return "cities";
    }

    @RequestMapping("/all-in-country")
    public String AllCitiesInCountry(@RequestParam String country, Model model) {

        List<City> cities = new Cities(App.Conn).getAllCitiesInCountryByDescPopulation(country);

        model.addAttribute("cities", cities);
        model.addAttribute("title", "Cities in " + country);
        return "cities";
    }

    @RequestMapping("/all-in-district")
    public String AllCitiesInDistrict(@RequestParam String district, Model model) {

        List<City> cities = new Cities(App.Conn).getAllCitiesInDistrictByDescPopulation(district);

        model.addAttribute("cities", cities);
        model.addAttribute("title", "Cities in " + district);
        return "cities";
    }

    @RequestMapping("/top-n-populated-in-world")
    public String TopNPopulatedCitiesInWorld(@RequestParam int numberOfCities, Model model) {
        List<City> cities = new Cities(App.Conn).getNPopulatedCitiesInWorldByDescPopulation(numberOfCities);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Top " + numberOfCities + " populated cities in the world");
        return "cities";
    }

    @RequestMapping("/top-n-populated-in-continent")
    public String TopNPopulatedCitiesInContinent(@RequestParam String continent, @RequestParam int numberOfCities, Model model) {
        List<City> cities = new Cities(App.Conn).getNPopulatedCitiesInContinentByDescPopulation(numberOfCities, continent);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Top " + numberOfCities + " populated cities in continent " + continent);
        return "cities";
    }

    @RequestMapping("/top-n-populated-in-region")
    public String TopNPopulatedCitiesInRegion(@RequestParam String region, @RequestParam int numberOfCities, Model model) {
        List<City> cities = new Cities(App.Conn).getNPopulatedCitiesWithinRegionByDescPopulation(numberOfCities, region);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Top " + numberOfCities + " populated cities in region " + region);
        return "cities";
    }

    @RequestMapping("/top-n-populated-in-country")
    public String TopNPopulatedCitiesInCountry(@RequestParam String country, @RequestParam int numberOfCities, Model model) {
        List<City> cities = new Cities(App.Conn).getNPopulatedCitiesInCountryByDescPopulation(numberOfCities, country);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Top " + numberOfCities + " populated cities in country " + country);
        return "cities";
    }

    @RequestMapping("/top-n-populated-in-district")
    public String TopNPopulatedCitiesInDistrict(@RequestParam String district, @RequestParam int numberOfCities, Model model) {
        List<City> cities = new Cities(App.Conn).getNPopulatedCitiesInDistrictByDescPopulation(numberOfCities, district);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Top " + numberOfCities + " populated cities in district " + district);
        return "cities";
    }

    @RequestMapping("/all-capital-cities-in-world")
    public String AllCapitalCitiesInWorld(Model model) {
        List<CapitalCity> cities = new Cities(App.Conn).getAllOrNCapitalCitiesInWorldByDescPopulation(null);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "All capital cities in world");
        return "capitalcities";
    }

    @RequestMapping("/all-capital-cities-in-continent")
    public String AllCapitalCitiesInContinent(@RequestParam String continent, Model model) {
        List<CapitalCity> cities = new Cities(App.Conn).getAllCapitalCitiesInContinentByDescPopulation(continent);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "All capital cities in continent " + continent);
        return "capitalcities";
    }

    @RequestMapping("/all-capital-cities-in-region")
    public String AllCapitalCitiesInRegion(@RequestParam String region, Model model) {
        List<CapitalCity> cities = new Cities(App.Conn).getAllCapitalCitiesInRegionByDescPopulation(region);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "All capital cities in region " + region);
        return "capitalcities";
    }

    @RequestMapping("/top-n-populated-capital-city-in-world")
    public String TopNPopulatedCapitalCitiesInDistrict(@RequestParam int numberOfCities, Model model) {
        List<CapitalCity> cities = new Cities(App.Conn).getNPopulatedCapitalCitiesInWorldByDescPopulation(numberOfCities);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Top " + numberOfCities + " populated capital cities in the world");
        return "capitalcities";
    }

    @RequestMapping("/top-n-populated-capital-city-in-continent")
    public String TopNPopulatedCapitalCitiesInContinent(@RequestParam String continent, @RequestParam int numberOfCities, Model model) {
        List<CapitalCity> cities = new Cities(App.Conn).getNPopulatedCapitalCitiesInContinentByDescPopulation(numberOfCities, continent);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Top " + numberOfCities + " populated capital cities in continent " + continent);
        return "capitalcities";
    }

    @RequestMapping("/top-n-populated-capital-city-in-region")
    public String TopNPopulatedCapitalCitiesInRegion(@RequestParam String region, @RequestParam int numberOfCities, Model model) {
        List<CapitalCity> cities = new Cities(App.Conn).getNPopulatedCapitalCitiesInRegionByDescPopulation(numberOfCities, region);
        model.addAttribute("cities", cities);
        model.addAttribute("title", "Top " + numberOfCities + " populated capital cities in region " + region);
        return "capitalcities";
    }
}
