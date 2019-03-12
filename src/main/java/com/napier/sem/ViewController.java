package com.napier.sem;

import com.napier.world.models.Country;
import com.napier.world.reports.Countries;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String Index() {
        return "index";
    }

    @RequestMapping("/all-countries")
    public String AllCountries(Model model) {

        List<Country> countries = new Countries(App.Conn).getCountriesByDescPopulation();

        model.addAttribute("countries", countries);
        model.addAttribute("title", "Countries");
        return "countries";
    }

    @RequestMapping("/topn-countries")
    public String TopNPopulatedCountries(@RequestParam int numberOfCountries, Model model) {
        List<Country> countries = new Countries(App.Conn).getNPopulatedCountries(numberOfCountries);
        model.addAttribute("countries", countries);
        model.addAttribute("title", "Top " + numberOfCountries + " populated countries");
        return "countries";
    }


}
