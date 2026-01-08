package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ------------------------
    // Eingabedaten
    // ------------------------

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private double jahresmenge;        // Produktionsmenge p.a.

    @Column(nullable = false)
    private double ruestkosten;         // Rüstkosten pro Los

    @Column(nullable = false)
    private double stueckkosten;        // Kosten pro Stück

    @Column(nullable = false)
    private double zinsfuss;            // Kapitalkosten in %

    // ------------------------
    // Ergebnis
    // ------------------------

    @Column(nullable = false)
    private double optimaleLosgroesse;

    // ------------------------
    // Meta
    // ------------------------

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // ------------------------
    // Lifecycle
    // ------------------------

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ------------------------
    // Getter & Setter
    // ------------------------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getJahresmenge() {
        return jahresmenge;
    }

    public void setJahresmenge(double jahresmenge) {
        this.jahresmenge = jahresmenge;
    }

    public double getRuestkosten() {
        return ruestkosten;
    }

    public void setRuestkosten(double ruestkosten) {
        this.ruestkosten = ruestkosten;
    }

    public double getStueckkosten() {
        return stueckkosten;
    }

    public void setStueckkosten(double stueckkosten) {
        this.stueckkosten = stueckkosten;
    }

    public double getZinsfuss() {
        return zinsfuss;
    }

    public void setZinsfuss(double zinsfuss) {
        this.zinsfuss = zinsfuss;
    }

    public double getOptimaleLosgroesse() {
        return optimaleLosgroesse;
    }

    public void setOptimaleLosgroesse(double optimaleLosgroesse) {
        this.optimaleLosgroesse = optimaleLosgroesse;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
