package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductViewController.class)
class ProductViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ✅ EINZIGER Mock
    @MockitoBean
    private ProductService productService;

    /**
     * Testet die Produktübersicht
     */
    @Test
    void produktUebersicht_returnsProductsViewWithModel() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("TestProdukt");

        when(productService.findeAlleProdukte())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/produkte"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attributeExists("products"));

        verify(productService).findeAlleProdukte();
    }

    /**
     * Testet Produkt löschen
     */
    @Test
    void produktLoeschen_redirectsToProdukte() throws Exception {
        mockMvc.perform(post("/produkt/loeschen/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/produkte"));

        verify(productService).loescheProdukt(1L);
    }

    /**
     * Testet Graph-View
     */
    @Test
    void showGraph_returnsGraphViewWithProductData() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("TestProdukt");
        product.setJahresmenge(1000);
        product.setRuestkosten(100);
        product.setStueckkosten(10);
        product.setZinsfuss(5);
        product.setOptimaleLosgroesse(200);

        when(productService.findeProduktOderThrow(1L))
                .thenReturn(product);

        mockMvc.perform(get("/produkt/graph/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("graph"))
                .andExpect(model().attribute("name", "TestProdukt"))
                .andExpect(model().attribute("losgroesse", 200.0));

        verify(productService).findeProduktOderThrow(1L);
    }
}
