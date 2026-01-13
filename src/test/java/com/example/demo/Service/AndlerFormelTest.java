package com.example.demo.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AndlerFormelTest {

    private AndlerFormel andlerFormel;

    @BeforeEach
    void setUp() {
        andlerFormel = new AndlerFormel();
    }

    @Test
    void berechneOptimaleLosgroesse_standardwerte() {
        // GIVEN
        double jahresmenge = 10_000;
        double ruestkosten = 500;
        double stueckkosten = 2;
        double zinsfuss = 10; // 10 %

        // WHEN
        double result = andlerFormel.berechneOptimaleLosgroesse(
                jahresmenge,
                ruestkosten,
                stueckkosten,
                zinsfuss
        );

        // THEN
        // Erwartungswert manuell berechnet:
        // sqrt((2 * 10000 * 500) / (2 * 0.1)) = 7071.07
        assertEquals(7071.07, result);
    }

    @Test
    void berechneOptimaleLosgroesse_rundetAufZweiNachkommastellen() {
        // GIVEN
        double jahresmenge = 1234;
        double ruestkosten = 456;
        double stueckkosten = 7.89;
        double zinsfuss = 12.5;

        // WHEN
        double result = andlerFormel.berechneOptimaleLosgroesse(
                jahresmenge,
                ruestkosten,
                stueckkosten,
                zinsfuss
        );

        // THEN
        assertEquals(1068.22, result);
        // Ohne Rundung wäre das Ergebnis 1068.2219333356322
    }

    @Test
    void berechneOptimaleLosgroesse_zinsfussNull_fuehrtZuException() {
        // Der Test stellt sicher, dass bei einem Zinssatz von 0 (zinsfuss = 0) eine Exception geworfen wird, um eine Division durch null in der Formel zu verhindern.
        assertThrows(IllegalArgumentException.class, () -> {
            andlerFormel.berechneOptimaleLosgroesse(1000, 100, 10, 0);
        });
    }

}
