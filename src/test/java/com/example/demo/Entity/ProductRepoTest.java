package com.example.demo.Entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    /**
     * Testet, ob ein Produkt korrekt in der Datenbank gespeichert
     * und anschließend wieder über das Repository geladen werden kann.
     */

    @Test
    void saveAndFindProduct_successful() {
        // GIVEN: Ein neues Produkt
        Product product = new Product();
        product.setName("TestProdukt");
        product.setJahresmenge(1000);
        product.setRuestkosten(100);
        product.setStueckkosten(10);
        product.setZinsfuss(5);
        product.setOptimaleLosgroesse(200);

        // WHEN: Produkt wird gespeichert
        Product savedProduct = productRepo.save(product);

        // THEN: Produkt kann wieder gefunden werden
        Optional<Product> foundProduct = productRepo.findById(savedProduct.getId());

        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("TestProdukt");
        assertThat(foundProduct.get().getOptimaleLosgroesse()).isEqualTo(200);
    }
}
