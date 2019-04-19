package com.napier.sem;

import com.napier.world.models.Population;
import com.napier.world.models.PopulationInfo;
import com.napier.world.reports.PopulationInfos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/info")
public class PopulationInfoController {
    @RequestMapping("/population-of-world")
    public String PopulationOfWorld(Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfWorld();

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of world");
        return "populationInfo";
    }

    @RequestMapping("/population-of-continent")
    public String PopulationOfContinent(@RequestParam String continent, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfAContinent(continent);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of continent" + continent);
        return "populationInfo";
    }

    @RequestMapping("/population-of-region")
    public String PopulationOfRegion(@RequestParam String region, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfARegion(region);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of region" + region);
        return "populationInfo";
    }

    @RequestMapping("/population-of-country")
    public String PopulationOfCountry(@RequestParam String country, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfACountry(country);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of country" + country);
        return "populationInfo";
    }

    @RequestMapping("/population-of-district")
    public String PopulationOfDistrict(@RequestParam String district, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfADistrict(district);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of district" + district);
        return "populationInfo";
    }

    @RequestMapping("/population-of-city")
    public String PopulationOfCity(@RequestParam String city, Model model) {

        PopulationInfo population = new PopulationInfos(App.Conn).getPopulationOfACity(city);

        model.addAttribute("population", population);
        model.addAttribute("title", "Population information of city" + city);
        return "populationInfo";
    }
}
