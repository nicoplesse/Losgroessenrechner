package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductViewController {

    private final ProductRepo productRepo;

    public ProductViewController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // 🔹 Produktübersicht
    @GetMapping("/produkte")
    public String produktUebersicht(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    // 🔹 Produkt löschen
    @PostMapping("/produkt/loeschen/{id}")
    public String produktLoeschen(@PathVariable Long id) {
        productRepo.deleteById(id);
        return "redirect:/produkte";
    }

    @GetMapping("/produkt/graph/{id}")
    public String showGraph(@PathVariable("id") Long id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden: " + id));

        model.addAttribute("name", product.getName());
        model.addAttribute("jahresmenge", product.getJahresmenge());
        model.addAttribute("ruestkosten", product.getRuestkosten());
        model.addAttribute("stueckkosten", product.getStueckkosten());
        model.addAttribute("zinsfuss", product.getZinsfuss());
        model.addAttribute("losgroesse", product.getOptimaleLosgroesse());

        return "graph"; // Thymeleaf Template: graph.html
    }
}
