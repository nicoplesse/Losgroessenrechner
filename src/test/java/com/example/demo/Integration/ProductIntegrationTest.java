package com.example.demo.integration;

import com.example.demo.Repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepo productRepo;

    /**
     *Integrationstest prüft das Zusammenspiel von Controller, Service, Repository und Datenbank.
     *Das Produkt wird über einen HTTP-Request gespeichert und anschließend aus der Datenbank verifiziert.
     */

    @Test
    void productIsCalculatedAndStoredInDatabase() throws Exception {

        // WHEN: Produkt wird über Controller gespeichert
        mockMvc.perform(post("/produkt/speichern")
                        .param("name", "IntegrationTestProdukt")
                        .param("jahresmenge", "1000")
                        .param("ruestkosten", "100")
                        .param("stueckkosten", "10")
                        .param("zinsfuss", "5")
                        .param("losgroesse", "200"))
                .andExpect(status().is3xxRedirection());

        // THEN: Produkt ist wirklich in der Datenbank
        assertThat(productRepo.findAll())
                .anyMatch(p -> p.getName().equals("IntegrationTestProdukt"));
    }
}
