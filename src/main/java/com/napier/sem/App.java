package com.napier.sem;

import com.napier.world.connection.Connection;
import com.napier.world.models.CapitalCity;
import com.napier.world.models.City;
import com.napier.world.models.Country;
import com.napier.world.models.SpokenLanguages;
import com.napier.world.reports.Cities;
import com.napier.world.reports.Countries;
import com.napier.world.reports.SpokenLanguagesReport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.napier.world.connection.ConnectionBuilder;

import java.util.List;

@EnableAutoConfiguration
@Configuration
@ComponentScan

/*
*Starting point of the application, initialises spring framework and connects to the database
 */

public class App
{
    public static Connection Conn;

    public static void main(String[] args)
    {
        System.out.println("App starting...");

        if (args.length < 1)
        {
            connectToDatabase("localhost:3306");
        }
        else
        {
            connectToDatabase(args[0]);
        }

        SpringApplication.run(App.class, args);
    }

    public static void connectToDatabase(String name)
    {
        Conn = new ConnectionBuilder().location(name).buildConnection();
    }

}