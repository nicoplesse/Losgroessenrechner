package com.example.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class AndlerFormel {

    /**
     * Berechnet die optimale Losgröße nach Andler
     *
     * @param jahresmenge Produktionsmenge p.a. (M)
     * @param ruestkosten Rüstkosten pro Los (Kr)
     * @param stueckkosten Kosten pro Stück (c)
     * @param zinsfuss Kapitalkosten in Prozent (z. B. 10 für 10 %)
     * @return optimale Losgröße
     */
    public double berechneOptimaleLosgroesse(
            double jahresmenge,
            double ruestkosten,
            double stueckkosten,
            double zinsfuss
    ) {

        // Prozent → Dezimal
        double i = zinsfuss / 100.0;

        // Andler-Formel
        double losgroesse = Math.sqrt((2 * jahresmenge * ruestkosten) / (stueckkosten * i));

        // auf 2 Nachkommastellen runden
        return Math.round(losgroesse * 100.0) / 100.0;
    }

}
