# Optimale Losgröße – Andler-Formel (Spring Boot)

Eine Webapplikation zur Berechnung und Verwaltung der **optimalen Losgröße nach der Andler-Formel**.  
Die Anwendung ermöglicht es, Produkte anzulegen, die optimale Losgröße zu berechnen, grafisch darzustellen und dauerhaft in einer PostgreSQL-Datenbank zu speichern.

## Features

- Berechnung der optimalen Losgröße nach der **Andler-Formel**
- Benutzerfreundliches Web-Frontend mit **Thymeleaf**
- Grafische Darstellung der Kostenkurven
- Persistente Speicherung von Produkten (**PostgreSQL + JPA/Hibernate**)
- Produktübersicht mit Löschfunktion
- Saubere Trennung von Controller, Service, Repository und Entity

---

## Berechnungsgrundlage

Die optimale Losgröße wird mit der **Andler-Formel** berechnet:

\[
Q^* = \sqrt{\frac{2 \cdot D \cdot K_r}{K_p \cdot i}}
\]

**Dabei gilt:**
- `D` = Jahresbedarf (Produktionsmenge p.a.)
- `K_r` = Rüstkosten pro Los
- `K_p` = Kosten pro Stück
- `i` = Kapitalkostensatz (Zinsfuß)

Das Ergebnis wird auf ganze Stückzahlen gerundet.

---

## Technologie-Stack

**Backend**
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

**Frontend**
- Thymeleaf
- HTML / CSS
- Chart.js (für Kosten- und Losgrößenkurven)

**Datenbank**
- PostgreSQL

**Build & Tooling**
- Maven
- Git
- GitHub

---

