package com.example.demo.Controller;

import com.example.demo.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductDBController.class)
class ProductDBControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ✅ EINZIGER Mock: Service
    @MockitoBean
    private ProductService productService;

    /**
     * Testet /produkt/berechnen
     * View: result
     * Model enthält berechnete Losgröße
     */
    @Test
    void berechnen_returnsResultViewWithLosgroesse() throws Exception {
        // GIVEN
        when(productService.berechneLosgroesse(
                1000, 100, 10, 5
        )).thenReturn(200.0);

        // WHEN + THEN
        mockMvc.perform(post("/produkt/berechnen")
                        .param("name", "TestProdukt")
                        .param("jahresmenge", "1000")
                        .param("ruestkosten", "100")
                        .param("stueckkosten", "10")
                        .param("zinsfuss", "5"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("name", "TestProdukt"))
                .andExpect(model().attribute("losgroesse", 200.0));

        verify(productService).berechneLosgroesse(1000, 100, 10, 5);
    }

    /**
     * Testet /produkt/speichern
     * Redirect zur Startseite
     */
    @Test
    void speichern_redirectsToHome() throws Exception {
        mockMvc.perform(post("/produkt/speichern")
                        .param("name", "TestProdukt")
                        .param("jahresmenge", "1000")
                        .param("ruestkosten", "100")
                        .param("stueckkosten", "10")
                        .param("zinsfuss", "5")
                        .param("losgroesse", "200"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(productService).erstelleUndSpeichereProdukt(
                anyString(),
                anyDouble(),
                anyDouble(),
                anyDouble(),
                anyDouble(),
                anyDouble()
        );
    }
}
