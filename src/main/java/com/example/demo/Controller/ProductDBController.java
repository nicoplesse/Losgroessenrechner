package com.example.demo.Controller;

import com.example.demo.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ProductDBController {

    private final ProductService productService;

    public ProductDBController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/produkt/berechnen")
    public String berechnen(
            @RequestParam String name,
            @RequestParam double jahresmenge,
            @RequestParam double ruestkosten,
            @RequestParam double stueckkosten,
            @RequestParam double zinsfuss,
            Model model
    ) {

        double losgroesse = productService.berechneLosgroesse(
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

    @PostMapping("/produkt/speichern")
    public String speichern(
            @RequestParam String name,
            @RequestParam double jahresmenge,
            @RequestParam double ruestkosten,
            @RequestParam double stueckkosten,
            @RequestParam double zinsfuss,
            @RequestParam double losgroesse
    ) {
        productService.erstelleUndSpeichereProdukt(
                name,
                jahresmenge,
                ruestkosten,
                stueckkosten,
                zinsfuss,
                losgroesse
        );

        return "redirect:/";
    }
}
