# Fitness IUT - Application de gestion de routines de fitness

## Description

Application Spring Boot permettant de gérer des routines de fitness et leurs exercices associés.

## Technologies utilisées

- **Java 21**
- **Spring Boot 3.2.2**
- **Maven**
- **MariaDB / MySQL**
- **Thymeleaf** (pour les vues MVC)
- **Spring Data JPA**
- **Bean Validation**

## Configuration

### Prérequis

- Java 21 installé
- MariaDB ou MySQL installé et démarré
- Maven installé

### Base de données

1. Créer la base de données en important le fichier SQL fourni :

```bash
mysql -u root -p < fitness_iut_dump.sql
```

Le fichier `fitness_iut_dump.sql` crée la base de données `fitness_iut` avec :
- Table `routines` : id, name, description, creation_date, status
- Table `exercises` : id, name, repetitions, weight, routine_id (FK avec CASCADE DELETE)

2. Configurer les paramètres de connexion dans `src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/fitness_iut
spring.datasource.username=root
spring.datasource.password=
```

### Port de l'application

L'application démarre sur le **port 8081** (configurable dans `application.properties`).

## Compilation et exécution

### Compilation avec Maven

```bash
mvn clean package
```

Le JAR exécutable sera généré dans `target/fitness-iut-1.0.0.jar`.

### Exécution

```bash
java -jar target/fitness-iut-1.0.0.jar
```

L'application sera accessible à l'adresse : http://localhost:8081

## Fonctionnalités MVC (Interface Web)

### Routines

1. **Liste des routines** (`/routines`)
   - Liste paginée des routines
   - Recherche par nom ou description (paramètre `mc`)
   - Choix de la taille de page (5, 10, 20, 50 éléments)
   - Conservation des paramètres de pagination et recherche lors de la navigation

2. **Détail d'une routine** (`/routines/{id}`)
   - Affichage des informations de la routine
   - Liste des exercices associés

3. **Créer une routine** (`/routines/create`)
   - Formulaire de création
   - Validation côté serveur
   - Redirection avec message de confirmation

4. **Modifier une routine** (`/routines/{id}/edit`)
   - Formulaire pré-rempli
   - Validation côté serveur
   - Redirection avec message de confirmation

5. **Supprimer une routine** (`POST /routines/{id}/delete`)
   - Confirmation JavaScript côté client
   - Suppression en cascade des exercices
   - Redirection avec conservation du contexte

### Exercices

6. **Ajouter un exercice** (`/routines/{routineId}/exercises/create`)
   - Formulaire d'ajout d'exercice à une routine
   - Validation côté serveur

7. **Modifier un exercice** (`/routines/{routineId}/exercises/{exerciseId}/edit`)
   - Formulaire pré-rempli
   - Validation côté serveur

8. **Supprimer un exercice** (`POST /routines/{routineId}/exercises/{exerciseId}/delete`)
   - Suppression de l'exercice
   - Redirection avec conservation du contexte

## Validation des données

### Routine

- **name** : Obligatoire, entre 3 et 120 caractères
- **status** : Obligatoire, valeurs acceptées : "active" ou "inactive"
- **description** : Optionnel

### Exercice

- **name** : Obligatoire, entre 2 et 120 caractères
- **repetitions** : Obligatoire, entier >= 1
- **weight** : Obligatoire, nombre >= 0 (en kilogrammes)

## API REST

L'API REST est accessible sous le préfixe `/api`.

### Endpoints disponibles

#### 1. GET /api/routines

Liste toutes les routines.

**Paramètres optionnels :**
- `page` : Numéro de la page (commence à 0)
- `size` : Nombre d'éléments par page
- `mc` : Mot-clé de recherche (recherche dans le nom et la description)

**Exemple sans pagination :**
```bash
curl http://localhost:8081/api/routines
```

**Exemple avec pagination :**
```bash
curl http://localhost:8081/api/routines?page=0&size=10
```

**Exemple avec recherche et pagination :**
```bash
curl http://localhost:8081/api/routines?page=0&size=10&mc=débutant
```

**Réponse (sans pagination) :**
```json
[
  {
    "id": 1,
    "name": "Routine Débutant",
    "description": "Programme pour débutants",
    "creationDate": "2024-01-15",
    "status": "active",
    "exercises": []
  }
]
```

**Réponse (avec pagination) :**
```json
{
  "routines": [...],
  "currentPage": 0,
  "totalItems": 4,
  "totalPages": 1
}
```

#### 2. GET /api/routines/{id}

Récupère le détail d'une routine avec ses exercices.

**Exemple :**
```bash
curl http://localhost:8081/api/routines/1
```

**Réponse :**
```json
{
  "id": 1,
  "name": "Routine Débutant",
  "description": "Programme pour débutants",
  "creationDate": "2024-01-15",
  "status": "active",
  "exercises": [
    {
      "id": 1,
      "name": "Pompes",
      "repetitions": 15,
      "weight": 0.0
    },
    {
      "id": 2,
      "name": "Squats",
      "repetitions": 20,
      "weight": 0.0
    }
  ]
}
```

**Erreur (routine non trouvée) :**
```json
{
  "error": "Routine non trouvée"
}
```

#### 3. POST /api/routines

Crée une nouvelle routine.

**Exemple :**
```bash
curl -X POST http://localhost:8081/api/routines \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Nouvelle Routine",
    "description": "Description de la routine",
    "status": "active"
  }'
```

**Réponse (succès) :**
```json
{
  "id": 5,
  "name": "Nouvelle Routine",
  "description": "Description de la routine",
  "creationDate": "2024-02-13",
  "status": "active",
  "exercises": []
}
```

**Erreur (validation échouée) :**
```json
{
  "error": "Erreur lors de la création de la routine"
}
```

#### 4. DELETE /api/routines/{id}

Supprime une routine et tous ses exercices (cascade).

**Exemple :**
```bash
curl -X DELETE http://localhost:8081/api/routines/5
```

**Réponse (succès) :**
```json
{
  "message": "Routine supprimée avec succès"
}
```

**Erreur (routine non trouvée) :**
```json
{
  "error": "Routine non trouvée"
}
```

## Structure du projet

```
src/
├── main/
│   ├── java/com/fitness/iut/
│   │   ├── FitnessIutApplication.java          # Classe principale
│   │   ├── controller/
│   │   │   ├── HomeController.java              # Redirection vers /routines
│   │   │   ├── RoutineController.java           # Contrôleur MVC
│   │   │   └── RoutineRestController.java       # Contrôleur REST
│   │   ├── entity/
│   │   │   ├── Routine.java                     # Entité Routine
│   │   │   └── Exercise.java                    # Entité Exercise
│   │   └── repository/
│   │       ├── RoutineRepository.java           # Repository Routine
│   │       └── ExerciseRepository.java          # Repository Exercise
│   └── resources/
│       ├── application.properties               # Configuration
│       ├── static/css/
│       │   └── style.css                        # CSS de l'application
│       └── templates/
│           ├── routines/
│           │   ├── list.html                    # Liste des routines
│           │   ├── detail.html                  # Détail d'une routine
│           │   └── form.html                    # Formulaire routine
│           └── exercises/
│               └── form.html                    # Formulaire exercice
```

## Liste des fonctionnalités développées

### ✅ Fonctionnalités obligatoires

#### MVC - Routines (8 points)
- ✅ Liste paginée avec choix de taille + conservation p/s
- ✅ Recherche mc + conservation mc dans pagination/édition
- ✅ CRUD routine (create/update) avec redirections + messages
- ✅ Suppression routine + cohérence (cascade/aucun orphelin)

#### MVC - Exercices (5 points)
- ✅ Ajouter un exercice à une routine
- ✅ Modifier un exercice
- ✅ Supprimer un exercice + retour propre

#### Validation (2 points)
- ✅ Validation routine (serveur) + affichage erreurs
- ✅ Validation exercice (serveur) + affichage erreurs

#### REST (3 points)
- ✅ GET /api/routines : liste des routines
- ✅ GET /api/routines/{id} : détail d'une routine
- ✅ POST /api/routines : création d'une routine
- ✅ DELETE /api/routines/{id} : suppression d'une routine

### ✅ Options bonus (valorisées)
- ✅ Pagination/filtre sur l'API REST (paramètres page, size, mc)
- ✅ Gestion d'erreurs REST propre (codes HTTP, messages JSON)

## Notes importantes

- La base de données est fournie via le fichier `fitness_iut_dump.sql` et doit être utilisée telle quelle
- Les suppressions de routines sont cohérentes grâce au `CASCADE DELETE` sur la clé étrangère
- L'application utilise Bean Validation avec `@Valid` et `BindingResult`
- Tous les paramètres de pagination et recherche sont conservés lors de la navigation
- Confirmation JavaScript avant suppression de routine
- Messages de confirmation après création/modification/suppression

## Auteur

Projet réalisé dans le cadre du module Spring Boot à l'IUT.
