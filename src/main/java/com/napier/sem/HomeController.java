package com.napier.sem;

import com.napier.world.models.Country;
import com.napier.world.reports.Countries;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @RequestMapping("/")
    public String Index(Model model)
    {
        Map<String, Object> controllers;
        controllers = listableBeanFactory.getBeansWithAnnotation(Controller.class);


        return "index";
    }
}
