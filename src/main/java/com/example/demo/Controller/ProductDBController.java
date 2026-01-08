package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.ProductRepo;
import com.example.demo.Service.AndlerFormel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductDBController {

    //........Service
    private final AndlerFormel andlerFormel;

    //........Repo
    private final ProductRepo productRepo;



    public ProductDBController(AndlerFormel andlerFormel, ProductRepo productRepo) {
        this.andlerFormel = andlerFormel;
        this.productRepo = productRepo;
    }


    @PostMapping("/produkt/berechnen") //Produkt erstellen
    public String berechnen(
            @RequestParam String name,
            @RequestParam double jahresmenge,
            @RequestParam double ruestkosten,
            @RequestParam double stueckkosten,
            @RequestParam double zinsfuss,
            Model model
    ) {

        double losgroesse = andlerFormel.berechneOptimaleLosgroesse(
                jahresmenge,
                ruestkosten,
                stueckkosten,
                zinsfuss
        );

        model.addAttribute("name", name);
        model.addAttribute("jahresmenge", jahresmenge);
        model.addAttribute("ruestkosten", ruestkosten);
        model.addAttribute("stueckkosten", stueckkosten);
        model.addAttribute("zinsfuss", zinsfuss);
        model.addAttribute("losgroesse", losgroesse);


        return "result";
    }

    @PostMapping("/produkt/speichern") //Produkt speichern
    public String speichern(
            @RequestParam String name,
            @RequestParam double jahresmenge,
            @RequestParam double ruestkosten,
            @RequestParam double stueckkosten,
            @RequestParam double zinsfuss,
            @RequestParam double losgroesse
    ) {

        Product product = new Product();
        product.setName(name);
        product.setJahresmenge(jahresmenge);
        product.setRuestkosten(ruestkosten);
        product.setStueckkosten(stueckkosten);
        product.setZinsfuss(zinsfuss);
        product.setOptimaleLosgroesse(losgroesse);

        productRepo.save(product);

        return "redirect:/";
    }


}
