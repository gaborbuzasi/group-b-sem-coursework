package com.napier.sem;

import com.napier.world.models.PopulationInfo;
import com.napier.world.reports.PopulationInfos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Class responsible for loading data for reports of populationInfo type
 * and passing the loaded data to the view
 */
@Controller
@RequestMapping("/info")
public class PopulationInfoController {

    @RequestMapping("/population-of-world")
    @NameAnnotation("The population of the world")
    public String PopulationOfWorld(Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfWorld();

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of world");
        return "populationinfo";
    }

    @RequestMapping("/population-of-continent")
    @NameAnnotation("The population of a continent")
    public String PopulationOfContinent(@RequestParam String continent, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfAContinent(continent);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of continent " + continent);
        return "populationinfo";
    }

    @RequestMapping("/population-of-region")
    @NameAnnotation("The population of a region")
    public String PopulationOfRegion(@RequestParam String region, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfARegion(region);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of region " + region);
        return "populationinfo";
    }

    @RequestMapping("/population-of-country")
    @NameAnnotation("The population of a country")
    public String PopulationOfCountry(@RequestParam String country, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfACountry(country);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of country " + country);
        return "populationinfo";
    }

    @RequestMapping("/population-of-district")
    @NameAnnotation("The population of a district")
    public String PopulationOfDistrict(@RequestParam String district, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfADistrict(district);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of district " + district);
        return "populationinfo";
    }

    @RequestMapping("/population-of-city")
    @NameAnnotation("The population of a city")
    public String PopulationOfCity(@RequestParam String city, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfACity(city);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of city " + city);
        return "populationinfo";
    }
}
