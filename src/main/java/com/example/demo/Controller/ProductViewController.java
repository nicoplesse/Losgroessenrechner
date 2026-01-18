package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/produkte")
    public String produktUebersicht(Model model) {
        model.addAttribute("products", productService.findeAlleProdukte());
        return "products";
    }

    @PostMapping("/produkt/loeschen/{id}")
    public String produktLoeschen(@PathVariable Long id) {
        productService.loescheProdukt(id);
        return "redirect:/produkte";
    }

    @GetMapping("/produkt/graph/{id}")
    public String showGraph(@PathVariable Long id, Model model) {
        Product product = productService.findeProduktOderThrow(id);

        model.addAttribute("name", product.getName());
        model.addAttribute("jahresmenge", product.getJahresmenge());
        model.addAttribute("ruestkosten", product.getRuestkosten());
        model.addAttribute("stueckkosten", product.getStueckkosten());
        model.addAttribute("zinsfuss", product.getZinsfuss());
        model.addAttribute("losgroesse", product.getOptimaleLosgroesse());

        return "graph";
    }
}
