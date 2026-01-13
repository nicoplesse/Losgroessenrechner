package com.example.demo.Controller;

import com.example.demo.Service.AndlerFormel;
import com.example.demo.Entity.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProductDBController.class)
class ProductDBControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // MockBeans für Service & Repo, damit der Controller geladen wird
    @MockitoBean
    private AndlerFormel andlerFormel;

    @MockitoBean
    private ProductRepo productRepo;




    @Test
    void berechnen_returnsResultViewWithLosgroesse() throws Exception {
        // GIVEN
        String name = "TestProdukt";
        double jahresmenge = 1000;
        double ruestkosten = 100;
        double stueckkosten = 10;
        double zinsfuss = 5;
        double expectedLosgroesse = 200.0;

        // Service Mock
        when(andlerFormel.berechneOptimaleLosgroesse(jahresmenge, ruestkosten, stueckkosten, zinsfuss))
                .thenReturn(expectedLosgroesse);

        // WHEN + THEN
        mockMvc.perform(post("/produkt/berechnen")
                        .param("name", name)
                        .param("jahresmenge", String.valueOf(jahresmenge))
                        .param("ruestkosten", String.valueOf(ruestkosten))
                        .param("stueckkosten", String.valueOf(stueckkosten))
                        .param("zinsfuss", String.valueOf(zinsfuss)))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("name", name))
                .andExpect(model().attribute("losgroesse", expectedLosgroesse));


    }



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
    }

    //Füge noch eine Methode hinzu die das Speichern testet
}
