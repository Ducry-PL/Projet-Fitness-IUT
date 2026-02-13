# Projet Fitness IUT

Application web de gestion de fitness développée avec Spring Boot et MariaDB.

## 🏋️ Description

Fitness IUT est une application web complète permettant de gérer des utilisateurs, des exercices et des plans d'entraînement. L'application offre à la fois une interface web conviviale et une API REST pour l'intégration avec d'autres systèmes.

## 🚀 Technologies utilisées

- **Backend** : Spring Boot 3.2.0
- **Base de données** : MariaDB 11.2
- **ORM** : Spring Data JPA / Hibernate
- **Template Engine** : Thymeleaf
- **Build Tool** : Maven
- **Java** : 17

## 📋 Prérequis

- Java 17 ou supérieur
- Maven 3.6+
- Docker et Docker Compose (pour la base de données)

## 🛠️ Installation et démarrage

### 1. Cloner le repository

```bash
git clone https://github.com/Ducry-PL/Projet-Fitness-IUT.git
cd Projet-Fitness-IUT
```

### 2. Démarrer la base de données MariaDB

```bash
docker-compose up -d
```

### 3. Compiler le projet

```bash
mvn clean install
```

### 4. Lancer l'application

```bash
mvn spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

## 🌐 Fonctionnalités

### Interface Web

- **Page d'accueil** : Vue d'ensemble avec statistiques
- **Gestion des utilisateurs** : Liste et consultation des profils
- **Bibliothèque d'exercices** : Catalogue complet des exercices disponibles
- **Plans d'entraînement** : Consultation des programmes personnalisés

### API REST

#### Utilisateurs (`/api/users`)

- `GET /api/users` - Liste tous les utilisateurs
- `GET /api/users/{id}` - Obtenir un utilisateur spécifique
- `POST /api/users` - Créer un nouvel utilisateur
- `PUT /api/users/{id}` - Mettre à jour un utilisateur
- `DELETE /api/users/{id}` - Supprimer un utilisateur

#### Exercices (`/api/exercises`)

- `GET /api/exercises` - Liste tous les exercices
- `GET /api/exercises/{id}` - Obtenir un exercice spécifique
- `GET /api/exercises/category/{category}` - Filtrer par catégorie
- `POST /api/exercises` - Créer un nouvel exercice
- `PUT /api/exercises/{id}` - Mettre à jour un exercice
- `DELETE /api/exercises/{id}` - Supprimer un exercice

#### Plans d'entraînement (`/api/workout-plans`)

- `GET /api/workout-plans` - Liste tous les plans
- `GET /api/workout-plans/{id}` - Obtenir un plan spécifique
- `GET /api/workout-plans/user/{userId}` - Plans d'un utilisateur
- `POST /api/workout-plans` - Créer un nouveau plan
- `PUT /api/workout-plans/{id}` - Mettre à jour un plan
- `DELETE /api/workout-plans/{id}` - Supprimer un plan

## 📊 Modèle de données

### User (Utilisateur)
- id, username, email, password
- firstName, lastName
- age, weight, height
- createdAt

### Exercise (Exercice)
- id, name, description
- category, difficulty
- duration, caloriesBurned

### WorkoutPlan (Plan d'entraînement)
- id, name, description
- user (relation ManyToOne)
- exercise (relation ManyToOne)
- sets, reps
- createdAt

## 🔧 Configuration

La configuration de l'application se trouve dans `src/main/resources/application.properties`.

### Base de données

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/fitness_iut
spring.datasource.username=fitness_user
spring.datasource.password=fitness_password
```

## 🧪 Tests

Pour exécuter les tests :

```bash
mvn test
```

## 📝 Structure du projet

```
Projet-Fitness-IUT/
├── src/
│   ├── main/
│   │   ├── java/com/iut/fitness/
│   │   │   ├── controller/       # Contrôleurs REST et Web
│   │   │   ├── model/            # Entités JPA
│   │   │   ├── repository/       # Repositories Spring Data
│   │   │   ├── service/          # Logique métier
│   │   │   └── FitnessApplication.java
│   │   └── resources/
│   │       ├── static/css/       # Fichiers CSS
│   │       ├── templates/        # Templates Thymeleaf
│   │       └── application.properties
│   └── test/                     # Tests unitaires
├── docker-compose.yml
├── pom.xml
└── README.md
```

## 👥 Auteurs

Projet réalisé dans le cadre du cours IUT

## 📄 Licence

Ce projet est un projet académique