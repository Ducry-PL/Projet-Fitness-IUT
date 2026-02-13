# Guide de démarrage rapide - Fitness IUT

## Démarrage avec Docker Compose

### 1. Démarrer la base de données MariaDB

```bash
docker-compose up -d
```

Cette commande va :
- Télécharger l'image MariaDB 11.2
- Créer un conteneur avec la base de données `fitness_iut`
- Configurer l'utilisateur `fitness_user` avec le mot de passe `fitness_password`
- Exposer le port 3306

### 2. Compiler et lancer l'application

```bash
mvn clean install
mvn spring-boot:run
```

L'application sera accessible sur : http://localhost:8080

### 3. Arrêter les services

```bash
# Arrêter l'application : Ctrl+C dans le terminal

# Arrêter la base de données
docker-compose down

# Arrêter et supprimer les données
docker-compose down -v
```

## Structure du projet

```
src/
├── main/
│   ├── java/com/iut/fitness/
│   │   ├── controller/          # Contrôleurs REST et Web
│   │   │   ├── ExerciseController.java
│   │   │   ├── HomeController.java
│   │   │   ├── UserController.java
│   │   │   └── WorkoutPlanController.java
│   │   ├── model/               # Entités JPA
│   │   │   ├── Exercise.java
│   │   │   ├── User.java
│   │   │   └── WorkoutPlan.java
│   │   ├── repository/          # Repositories Spring Data
│   │   │   ├── ExerciseRepository.java
│   │   │   ├── UserRepository.java
│   │   │   └── WorkoutPlanRepository.java
│   │   ├── service/             # Services métier
│   │   │   ├── ExerciseService.java
│   │   │   ├── UserService.java
│   │   │   └── WorkoutPlanService.java
│   │   └── FitnessApplication.java
│   └── resources/
│       ├── static/css/          # Fichiers CSS
│       │   └── style.css
│       ├── templates/           # Templates Thymeleaf
│       │   ├── index.html
│       │   ├── users.html
│       │   ├── exercises.html
│   │       └── workout-plans.html
│       └── application.properties
└── test/                        # Tests unitaires
    ├── java/com/iut/fitness/
    │   ├── FitnessApplicationTests.java
    │   └── service/
    │       └── UserServiceTest.java
    └── resources/
        └── application.properties  # Config de test avec H2
```

## Tester l'API avec curl

### Créer un utilisateur

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe",
    "age": 25,
    "weight": 75.5,
    "height": 180.0
  }'
```

### Récupérer tous les utilisateurs

```bash
curl http://localhost:8080/api/users
```

### Créer un exercice

```bash
curl -X POST http://localhost:8080/api/exercises \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Push-ups",
    "description": "Exercice de musculation pour le haut du corps",
    "category": "Force",
    "difficulty": "Intermédiaire",
    "duration": 15,
    "caloriesBurned": 100
  }'
```

### Récupérer tous les exercices

```bash
curl http://localhost:8080/api/exercises
```

## Tests

### Exécuter tous les tests

```bash
mvn test
```

Les tests utilisent une base de données H2 en mémoire, donc pas besoin de MariaDB pour les tests.

### Exécuter les tests avec rapport de couverture

```bash
mvn clean test
```

## Pages Web disponibles

- **Accueil** : http://localhost:8080/
- **Utilisateurs** : http://localhost:8080/users
- **Exercices** : http://localhost:8080/exercises
- **Plans d'entraînement** : http://localhost:8080/workout-plans

## Configuration

### application.properties (production)

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/fitness_iut
spring.datasource.username=fitness_user
spring.datasource.password=fitness_password
spring.jpa.hibernate.ddl-auto=update
```

### src/test/resources/application.properties (tests)

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## Dépannage

### La base de données ne démarre pas

```bash
# Vérifier les logs
docker-compose logs mariadb

# Redémarrer
docker-compose restart mariadb
```

### L'application ne peut pas se connecter à la base

1. Vérifier que le conteneur MariaDB est en cours d'exécution :
   ```bash
   docker ps
   ```

2. Vérifier la connexion :
   ```bash
   docker exec -it fitness-iut-db mysql -u fitness_user -pfitness_password fitness_iut
   ```

### Port 8080 déjà utilisé

Modifier le port dans `application.properties` :
```properties
server.port=8081
```

## Technologies et dépendances

- Spring Boot 3.2.0
- Spring Data JPA
- Spring Boot Starter Web
- Thymeleaf
- MariaDB Driver
- Lombok
- H2 Database (tests)
- JUnit 5 & Mockito (tests)
