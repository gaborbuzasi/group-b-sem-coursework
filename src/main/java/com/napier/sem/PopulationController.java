package com.napier.sem;

import com.napier.world.models.Population;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Class responsible for loading data for reports of population type
 * and passing the loaded data to the view
 */
@Controller
@RequestMapping("/population")
public class PopulationController {
    @RequestMapping("/per-continent")
    @NameAnnotation("The population of people, people living in cities, and people not living in cities in each continent")
    public String PopulationInContinent(Model model) {

        List<Population> population = new com.napier.world.reports.Population(App.Conn).getPopulationByContinent();

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information by continent");
        return "population";
    }

    @RequestMapping("/per-region")
    @NameAnnotation("The population of people, people living in cities, and people not living in cities in each region")
    public String PopulationInRegion(Model model) {

        List<Population> population = new com.napier.world.reports.Population(App.Conn).getPopulationByRegion();

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information by region");
        return "population";
    }

    @RequestMapping("/per-country")
    @NameAnnotation("The population of people, people living in cities, and people not living in cities in each country")
    public String PopulationInCountry(Model model) {

        List<Population> population = new com.napier.world.reports.Population(App.Conn).getPopulationByCountries();

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information by country");
        return "population";
    }
}
