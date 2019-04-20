package com.napier.sem;

import com.napier.world.models.SpokenLanguages;
import com.napier.world.reports.SpokenLanguagesReport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Class responsible for loading data for reports of spoken languages type
 * and passing the loaded data to the view
 */
@Controller
@RequestMapping("/info")
public class SpokenLanguagesController {

    @RequestMapping("/spoken-languages")
    @NameAnnotation("Spoken languages")
    public String SpokenLanguages(Model model) {

        List<SpokenLanguages> langs = new SpokenLanguagesReport(App.Conn).getLanguagesSpoken();

        model.addAttribute("languages", langs);
        model.addAttribute("title", "Languages spoken in the world");
        return "spokenlanguages";
    }
}
