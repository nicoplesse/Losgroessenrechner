package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {



    @GetMapping("/") // Homepage
    public String home() {
        return "home";
    }

    @GetMapping("/produkt/neu") //Neues Produkt
    public String newProduct() {
        return "newproduct";
    }



}
