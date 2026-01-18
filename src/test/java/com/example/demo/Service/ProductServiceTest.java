package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    @Mock
    private AndlerFormel andlerFormel;

    @InjectMocks
    private ProductService productService;

    // 🔹 Berechnung testen
    @Test
    void berechneLosgroesse_shouldReturnCalculatedValue() {
        when(andlerFormel.berechneOptimaleLosgroesse(1000, 50, 10, 0.1))
                .thenReturn(200.0);

        double result = productService.berechneLosgroesse(
                1000, 50, 10, 0.1
        );

        assertThat(result).isEqualTo(200.0);
        verify(andlerFormel).berechneOptimaleLosgroesse(1000, 50, 10, 0.1);
    }

    // 🔹 Produkt erstellen & speichern
    @Test
    void erstelleUndSpeichereProdukt_shouldSaveAndReturnProduct() {
        Product savedProduct = new Product();
        savedProduct.setName("Testprodukt");

        when(productRepo.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.erstelleUndSpeichereProdukt(
                "Testprodukt",
                1000,
                50,
                10,
                0.1,
                200
        );

        assertThat(result.getName()).isEqualTo("Testprodukt");
        verify(productRepo).save(any(Product.class));
    }

    // 🔹 Alle Produkte finden
    @Test
    void findeAlleProdukte_shouldReturnList() {
        when(productRepo.findAll()).thenReturn(List.of(
                new Product(), new Product()
        ));

        List<Product> result = productService.findeAlleProdukte();

        assertThat(result).hasSize(2);
        verify(productRepo).findAll();
    }

    // 🔹 Produkt nach ID finden (OK)
    @Test
    void findeProduktOderThrow_shouldReturnProduct_whenExists() {
        Product product = new Product();
        product.setName("Produkt A");

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.findeProduktOderThrow(1L);

        assertThat(result.getName()).isEqualTo("Produkt A");
        verify(productRepo).findById(1L);
    }

    // 🔹 Produkt nach ID finden (Fehlerfall)
    @Test
    void findeProduktOderThrow_shouldThrowException_whenNotFound() {
        when(productRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                productService.findeProduktOderThrow(99L)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Produkt nicht gefunden");

        verify(productRepo).findById(99L);
    }

    // 🔹 Produkt löschen
    @Test
    void loescheProdukt_shouldCallRepositoryDelete() {
        productService.loescheProdukt(5L);

        verify(productRepo).deleteById(5L);
    }
}
