package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final AndlerFormel andlerFormel;

    public ProductService(ProductRepo productRepo, AndlerFormel andlerFormel) {
        this.productRepo = productRepo;
        this.andlerFormel = andlerFormel;
    }

    // Berechnung + aufrufen der AndlerFormel
    public double berechneLosgroesse(
            double jahresmenge,
            double ruestkosten,
            double stueckkosten,
            double zinsfuss
    ) {
        return andlerFormel.berechneOptimaleLosgroesse(
                jahresmenge,
                ruestkosten,
                stueckkosten,
                zinsfuss
        );
    }

    // Produkt erstellen + speichern
    public Product erstelleUndSpeichereProdukt(
            String name,
            double jahresmenge,
            double ruestkosten,
            double stueckkosten,
            double zinsfuss,
            double losgroesse
    ) {
        Product product = new Product();
        product.setName(name);
        product.setJahresmenge(jahresmenge);
        product.setRuestkosten(ruestkosten);
        product.setStueckkosten(stueckkosten);
        product.setZinsfuss(zinsfuss);
        product.setOptimaleLosgroesse(losgroesse);

        return productRepo.save(product);
    }

    // Alle Produkte laden
    public List<Product> findeAlleProdukte() {
        return productRepo.findAll();
    }

    // Produkt nach ID laden
    public Product findeProduktOderThrow(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Produkt nicht gefunden: " + id)
                );
    }

    // Produkt löschen
    public void loescheProdukt(Long id) {
        productRepo.deleteById(id);
    }
}
