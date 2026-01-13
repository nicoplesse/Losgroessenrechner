package com.example.demo.Controller;


import com.example.demo.Entity.Product;
import com.example.demo.Entity.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductViewController.class)
public class ProductViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductRepo productRepo;

    /**
     * Testet, dass die Produktübersicht geladen wird
     * und die Produktliste im Model enthalten ist.
     */
    @Test
    void produktUebersicht_returnsProductsViewWithModel() throws Exception {
        // GIVEN
        Product product = new Product();
        product.setId(1L);
        product.setName("TestProdukt");

        when(productRepo.findAll()).thenReturn(List.of(product));

        // WHEN + THEN
        mockMvc.perform(get("/produkte"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attributeExists("products"));
    }


    /**
     * Testet, dass ein Produkt gelöscht wird
     * und anschließend zur Produktübersicht weitergeleitet wird.
     */
    @Test
    void produktLoeschen_redirectsToProdukte() throws Exception {
        // WHEN + THEN
        mockMvc.perform(post("/produkt/loeschen/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/produkte"));

        verify(productRepo).deleteById(1L);
    }

    /**
     * Testet, dass die Graph-View geladen wird
     * und alle benötigten Produktdaten im Model stehen.
     */
    @Test
    void showGraph_returnsGraphViewWithProductData() throws Exception {
        // GIVEN
        Product product = new Product();
        product.setId(1L);
        product.setName("TestProdukt");
        product.setJahresmenge(1000);
        product.setRuestkosten(100);
        product.setStueckkosten(10);
        product.setZinsfuss(5);
        product.setOptimaleLosgroesse(200);

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // WHEN + THEN
        mockMvc.perform(get("/produkt/graph/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("graph"))
                .andExpect(model().attribute("name", "TestProdukt"))
                .andExpect(model().attribute("losgroesse", 200.0));
    }

    /**
     * Testet, dass bei nicht vorhandenem Produkt
     * eine Exception ausgelöst wird.
     */
    @Test
    void showGraph_productNotFound_returns404() throws Exception {
        when(productRepo.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/produkt/graph/99"))
                .andExpect(status().isNotFound());
    }







}
