# 🚗 Application de Location de Voiture – Projet Spring Boot

## 🎯 Objectif

Ce projet a été réalisé dans le cadre d’un **examen pratique** pour démontrer des compétences en :

- Développement Spring Boot (REST API)
- Tests unitaires, mocks, spies, tests d'intégration
- Développement avec TDD
- Tests E2E avec Cucumber
- Analyse qualité du code avec SonarQube
- Tests de charge avec JMeter

---

## ✅ Fonctionnalités de l'API

- `GET /cars` — Lister toutes les voitures
- `POST /cars/rent/{registrationNumber}` — Louer une voiture
- `POST /cars/return/{registrationNumber}` — Retourner une voiture
- `POST /cars/add` — Ajouter une voiture (nouvelle fonctionnalité via TDD)
- `GET /cars/search?model={model}` — Rechercher une voiture par modèle (nouvelle fonctionnalité via TDD)

---

## 🧪 Lancer les tests

### ▶️ Tous les tests JUnit (unitaires, mocks, spies, intégration)

```bash
./mvnw clean test
```

### ▶️ Tests E2E (Cucumber)

```bash
./mvnw verify
```

---

## 🔍 Analyse avec SonarQube

### 1. Lancer SonarQube avec Docker

```bash
docker-compose up -d
```

### 2. Analyser le projet avec SonarScanner

```bash
./mvnw clean verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=VOTRE_TOKEN
```

> Remplace `VOTRE_TOKEN` par ton token personnel SonarQube.

---

## 💥 Tests de charge (JMeter)

### 1. Ouvrir le rapport

```bash
start jm-report\index.html
```

---

## 📦 Fichiers livrables

- ✅ Code source (Java + Spring Boot)
- ✅ Fichiers de test unitaires, mocks, spies et intégration
- ✅ Fichiers `.feature` + step definitions Cucumber
- ✅ Fichier Docker Compose (`docker-compose.yml`)
- ✅ Fichier `.jmx` pour tests JMeter + rapport dans `jm-report/`
- ✅ Couverture et qualité de code analysée via SonarQube

---

## 🙌 Réalisé par

**Nicolas Robles**  
M1 Informatique — Examen TDD — Avril 2025