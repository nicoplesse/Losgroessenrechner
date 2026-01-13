# Optimale Losgröße – Andler-Formel (Spring Boot)

Eine Webapplikation zur Berechnung und Verwaltung der **optimalen Losgröße nach der Andler-Formel**.  
Die Anwendung ermöglicht es, Produkte anzulegen, die optimale Losgröße zu berechnen, grafisch darzustellen und dauerhaft in einer PostgreSQL-Datenbank zu speichern.

**Live-Demo:** [https://losgroessenrechner.onrender.com](https://losgroessenrechner.onrender.com)

---




## Features

- Berechnung der optimalen Losgröße nach der **Andler-Formel**
- Benutzerfreundliches Web-Frontend mit **Thymeleaf**
- Grafische Darstellung der Kostenkurven
- Persistente Speicherung von Produkten (**PostgreSQL + JPA/Hibernate**)
- Produktübersicht mit Löschfunktion
- Saubere Trennung von Controller, Service, Repository und Entity
- Containerisierung der Anwendung mit **Docker**
- Automatisierte CI-Pipeline mit **GitHub Actions**
- **Umfassende Testabdeckung** (Unit, Controller, Repository, Integration)
- **Cloud-Deployment**
  - Docker-basierte Anwendung auf **Render**
  - Automatisches Deployment bei Push auf den `dev`-Branch
  - Öffentliche URL zur Live-Demonstration der Anwendung
  - Persistente PostgreSQL-Datenbank in der Cloud


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

### Backend
- Java (LTS)
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

### Frontend
- Thymeleaf
- HTML / CSS
- Chart.js (für Kosten- und Losgrößenkurven)

### Datenbank
- PostgreSQL

### Build, CI/CD & DevOps
- Maven
- Git
- GitHub
- GitHub Actions (CI)
- Docker
- Docker Hub
- Render

---



## CI/CD: Docker & Cloud-Deployment (Render)

Die Anwendung ist vollständig containerisiert und in eine automatisierte **CI/CD Docker & Cloud-Deployment-Pipeline** integriert.

### Automatischer Dockerbuild

- Bei jedem Push auf den Hauptbranch wird die Anwendung:
  - mit Maven gebaut
  - als Docker Image verpackt
  - automatisch zu **Docker Hub** gepusht
- Einheitliche Laufzeitumgebung durch Docker

---

### Automatisches Deployment mit Render

- Das GitHub-Repository ist direkt mit **Render** verbunden
- **Jeder Push auf den `dev`-Branch** triggert automatisch:
  - den Build der Anwendung
  - das Erstellen des Docker Images
  - das erneute Deployment des Services auf **Render**
- Der gesamte Deployment-Prozess läuft vollautomatisch ohne manuelle Eingriffe

### Datenbank (PostgreSQL)

- Die Anwendung verwendet eine **PostgreSQL-Datenbank**, die ebenfalls auf **Render** gehostet wird
- Persistente Speicherung aller Produkt- und Berechnungsdaten


## Testabdeckung

Die Anwendung enthält **aktuelle Tests** für alle wichtigen Komponenten:

- **Unit Tests** – Überprüfung der Berechnungslogik in Services (AndlerFormel)
- **Controller Tests** – Sicherstellen der richtigen Routen, View-Namen und Model-Attribute
- **Repository Tests** – Prüfung der JPA-Datenbankoperationen
- **Integration Tests** – End-to-End Tests, um das Zusammenspiel von Controller, Service und Repository zu prüfen

Alle Tests werden automatisch in der **GitHub Actions CI-Pipeline** ausgeführt, bevor ein Docker Image gebaut wird.

---

## Ziel des Projekts

Ziel dieses Projekts ist es, praxisnahe Fähigkeiten in folgenden Bereichen zu demonstrieren:

- Java & Spring Boot
- Webentwicklung mit Thymeleaf
- Wirtschaftliche Berechnungen
- Datenpersistenz mit JPA & PostgreSQL
- Containerisierung mit Docker
- CI/CD mit GitHub Actions
- Cloud-Deployment
- Test-Driven Development (TDD) und automatisierte Tests