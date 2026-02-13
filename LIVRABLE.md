# Livrable Final - Projet Fitness IUT

## 1. Code Source Complet ✅

Le projet complet est disponible sur le dépôt GitHub dans la branche TESTING.

Structure du projet :
```
Projet-Fitness-IUT/
├── pom.xml                              # Configuration Maven
├── fitness_iut_dump.sql                 # Dump SQL de la base de données
├── README.md                            # Instructions rapides
├── DOCUMENTATION.md                     # Documentation complète
├── .gitignore                           # Fichiers à exclure du dépôt
└── src/
    └── main/
        ├── java/com/fitness/iut/
        │   ├── FitnessIutApplication.java
        │   ├── controller/
        │   │   ├── HomeController.java
        │   │   ├── RoutineController.java
        │   │   └── RoutineRestController.java
        │   ├── entity/
        │   │   ├── Routine.java
        │   │   └── Exercise.java
        │   └── repository/
        │       ├── RoutineRepository.java
        │       └── ExerciseRepository.java
        └── resources/
            ├── application.properties
            ├── static/css/style.css
            └── templates/
                ├── routines/
                │   ├── list.html
                │   ├── detail.html
                │   └── form.html
                └── exercises/
                    └── form.html
```

## 2. JAR Exécutable ✅

**Nom du fichier** : `fitness-iut-1.0.0.jar`
**Emplacement** : `target/fitness-iut-1.0.0.jar` (après compilation)

**Compilation** :
```bash
mvn clean package
```

**Exécution** :
```bash
java -jar target/fitness-iut-1.0.0.jar
```

L'application démarre sur le port **8081** : http://localhost:8081

## 3. Documentation PDF

### A. Liste des fonctionnalités développées

#### ✅ MVC - Routines (8 points sur 8)

1. **Liste paginée + choix taille + conservation p/s** (3 pts) ✅
   - Liste paginée des routines avec navigation
   - Choix de la taille de page : 5, 10, 20, 50 éléments
   - Conservation des paramètres `page` et `size` dans tous les liens

2. **Recherche mc + conservation mc dans pagination/édition** (2 pts) ✅
   - Recherche par mot-clé dans le nom ou la description
   - Conservation du paramètre `mc` lors de la pagination
   - Conservation du paramètre `mc` lors de l'édition/retour

3. **CRUD routine (create/update) avec redirections + messages** (2 pts) ✅
   - Création de routine avec formulaire
   - Modification de routine avec formulaire pré-rempli
   - Validation serveur avec Bean Validation
   - Redirections vers la liste avec messages de confirmation
   - Messages flash affichés après chaque opération

4. **Suppression routine + cohérence (cascade/aucun orphelin)** (1 pt) ✅
   - Suppression avec confirmation JavaScript
   - Cascade DELETE au niveau de la base de données (FK)
   - Aucun exercice orphelin après suppression
   - Redirection avec conservation du contexte

#### ✅ MVC - Exercices (5 points sur 5)

5. **Ajouter un exercice à une routine** (2 pts) ✅
   - Formulaire d'ajout d'exercice
   - Association automatique à la routine
   - Validation serveur
   - Redirection vers le détail de la routine

6. **Modifier un exercice** (1 pt) ✅
   - Formulaire pré-rempli
   - Validation serveur
   - Redirection propre

7. **Supprimer un exercice + retour propre** (1 pt) ✅
   - Suppression de l'exercice
   - Redirection vers le détail de la routine
   - Conservation des paramètres de contexte

8. **Retour propre après actions exercices** (1 pt) ✅
   - Tous les retours conservent page/size/mc
   - Messages de confirmation affichés

#### ✅ Validation (2 points sur 2)

9. **Validation routine (serveur) + affichage erreurs** (1 pt) ✅
   - `@Valid` avec `BindingResult`
   - Annotations de validation sur l'entité Routine :
     - `name` : @NotBlank, @Size(min=3, max=120)
     - `status` : @NotBlank, @Pattern("active|inactive")
   - Affichage des erreurs dans le formulaire

10. **Validation exercice (serveur) + affichage erreurs** (1 pt) ✅
    - `@Valid` avec `BindingResult`
    - Annotations de validation sur l'entité Exercise :
      - `name` : @NotBlank, @Size(min=2, max=120)
      - `repetitions` : @NotNull, @Min(1)
      - `weight` : @NotNull, @Min(0)
    - Affichage des erreurs dans le formulaire

#### ✅ REST API (3 points sur 3)

11. **GET /api/routines** ✅
    - Liste de toutes les routines
    - Support de la pagination (paramètres `page`, `size`)
    - Support de la recherche (paramètre `mc`)
    - Retourne JSON

12. **GET /api/routines/{id}** ✅
    - Détail d'une routine avec ses exercices
    - Retourne JSON
    - Code HTTP 404 si non trouvée

13. **POST /api/routines** ✅
    - Création d'une nouvelle routine
    - Validation automatique avec Bean Validation
    - Code HTTP 201 (Created) en cas de succès
    - Code HTTP 400 (Bad Request) en cas d'erreur

14. **DELETE /api/routines/{id}** ✅
    - Suppression d'une routine et de ses exercices
    - Code HTTP 200 avec message de confirmation
    - Code HTTP 404 si routine non trouvée

### B. Options ajoutées (Bonus)

#### ✅ Pagination et filtre sur l'API REST
- L'API REST supporte les paramètres `page`, `size` et `mc`
- Format de réponse paginée avec métadonnées :
  - `routines` : tableau des routines
  - `currentPage` : numéro de page actuelle
  - `totalItems` : nombre total d'éléments
  - `totalPages` : nombre total de pages

#### ✅ Gestion d'erreurs REST propre
- Codes HTTP appropriés (200, 201, 400, 404, 500)
- Messages d'erreur en JSON
- Format cohérent pour les réponses d'erreur

#### ✅ Interface utilisateur complète et responsive
- CSS personnalisé professionnel
- Design moderne et épuré
- Interface responsive pour mobile et tablette
- Messages de confirmation/erreur visuels

### C. Liste exhaustive des endpoints REST

#### GET /api/routines

**Description** : Liste toutes les routines

**Paramètres (tous optionnels)** :
- `page` : Numéro de page (commence à 0)
- `size` : Nombre d'éléments par page
- `mc` : Mot-clé de recherche

**Codes de retour** :
- 200 : Succès

**Exemple** :
```bash
GET http://localhost:8081/api/routines
GET http://localhost:8081/api/routines?page=0&size=10
GET http://localhost:8081/api/routines?page=0&size=10&mc=débutant
```

---

#### GET /api/routines/{id}

**Description** : Récupère le détail d'une routine avec ses exercices

**Paramètres** :
- `id` : Identifiant de la routine (path parameter)

**Codes de retour** :
- 200 : Succès
- 404 : Routine non trouvée

**Exemple** :
```bash
GET http://localhost:8081/api/routines/1
```

---

#### POST /api/routines

**Description** : Crée une nouvelle routine

**Corps de la requête (JSON)** :
```json
{
  "name": "Nom de la routine",
  "description": "Description optionnelle",
  "status": "active"
}
```

**Codes de retour** :
- 201 : Routine créée avec succès
- 400 : Erreur de validation

**Exemple** :
```bash
POST http://localhost:8081/api/routines
Content-Type: application/json

{
  "name": "Nouvelle Routine",
  "description": "Description",
  "status": "active"
}
```

---

#### DELETE /api/routines/{id}

**Description** : Supprime une routine et tous ses exercices

**Paramètres** :
- `id` : Identifiant de la routine (path parameter)

**Codes de retour** :
- 200 : Suppression réussie
- 404 : Routine non trouvée
- 500 : Erreur serveur

**Exemple** :
```bash
DELETE http://localhost:8081/api/routines/5
```

---

## 4. Configuration requise

### Prérequis
- Java 21
- Maven 3.6+
- MariaDB ou MySQL 5.7+

### Installation de la base de données
```bash
mysql -u root -p < fitness_iut_dump.sql
```

### Configuration de la connexion
Modifier `src/main/resources/application.properties` si nécessaire :
```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/fitness_iut
spring.datasource.username=root
spring.datasource.password=
```

## 5. Points techniques importants

### Architecture
- Architecture MVC classique
- Séparation Entity / Repository / Controller
- Pas de couche Service (volontairement simple)
- Validation déclarative avec Bean Validation

### Base de données
- Schéma fourni via le dump SQL
- Relation One-to-Many entre Routine et Exercise
- CASCADE DELETE configuré au niveau de la base de données
- `@OneToMany` avec `cascade = CascadeType.ALL` et `orphanRemoval = true` au niveau JPA

### Sécurité
- Pas d'authentification (non demandée)
- Validation côté serveur pour toutes les entrées
- Gestion des erreurs avec codes HTTP appropriés

### Performance
- Pagination pour les grandes listes
- Index automatiques sur les clés primaires et étrangères
- FetchType.LAZY pour la relation Exercise -> Routine

## 6. Grille d'auto-évaluation

| Critère | Points max | Points obtenus | Statut |
|---------|-----------|----------------|--------|
| MVC - Routines | 8 | 8 | ✅ |
| MVC - Exercices | 5 | 5 | ✅ |
| Validation | 2 | 2 | ✅ |
| REST API | 3 | 3 | ✅ |
| **Total** | **18** | **18** | ✅ |
| Bonus : Pagination REST | +1 | +1 | ✅ |
| Bonus : Erreurs REST propres | +0.5 | +0.5 | ✅ |
| Bonus : Interface complète | +0.5 | +0.5 | ✅ |
| **Total avec bonus** | **20** | **20** | ✅ |

## 7. Captures d'écran (à générer)

1. Liste des routines avec pagination
2. Détail d'une routine avec exercices
3. Formulaire de création de routine
4. Messages de validation
5. Exemple de réponse API REST

## 8. Auteur

Projet réalisé dans le cadre du module Spring Boot à l'IUT.

Date de livraison : 13 février 2026
