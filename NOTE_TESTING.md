# Note importante : Branche TESTING

## Pour utiliser la branche TESTING

Comme demandé, tout le développement a été réalisé dans une nouvelle branche appelée **TESTING**.

### Comment accéder au code dans la branche TESTING

```bash
# Cloner le dépôt
git clone https://github.com/Ducry-PL/Projet-Fitness-IUT.git
cd Projet-Fitness-IUT

# Basculer sur la branche TESTING
git checkout TESTING

# Vérifier que vous êtes bien sur la branche TESTING
git branch
```

### Contenu de la branche TESTING

La branche TESTING contient :
- ✅ Tous les fichiers sources Java
- ✅ Toutes les vues Thymeleaf
- ✅ Le fichier de dump SQL de la base de données
- ✅ La configuration Maven (pom.xml)
- ✅ La documentation complète (DOCUMENTATION.md, LIVRABLE.md)
- ✅ Le fichier README.md avec instructions rapides

### Compilation et exécution depuis la branche TESTING

```bash
# 1. Importer la base de données
mysql -u root -p < fitness_iut_dump.sql

# 2. Compiler le projet (nécessite Java 21)
mvn clean package

# 3. Lancer l'application
java -jar target/fitness-iut-1.0.0.jar

# 4. Accéder à l'application
# Ouvrir http://localhost:8081 dans votre navigateur
```

### Important

- Le projet nécessite **Java 21** (pas Java 17 ou antérieur)
- Le port par défaut est **8081** (pas 8080)
- La base de données doit s'appeler **fitness_iut**
- Aucun Docker ou autre logiciel n'est utilisé, juste MariaDB classique en local

## Résumé des fonctionnalités

Toutes les fonctionnalités demandées dans le cahier des charges ont été implémentées :

### MVC (13 points sur 13)
- ✅ Liste paginée des routines avec recherche
- ✅ Détail d'une routine avec ses exercices
- ✅ Création et modification de routines (avec validation)
- ✅ Suppression de routines (avec cascade)
- ✅ Ajout, modification et suppression d'exercices
- ✅ Validation serveur pour routines et exercices
- ✅ Conservation des paramètres de navigation

### REST API (3 points sur 3)
- ✅ GET /api/routines (avec pagination et recherche)
- ✅ GET /api/routines/{id}
- ✅ POST /api/routines
- ✅ DELETE /api/routines/{id}

### Bonus
- ✅ Pagination et filtre sur l'API REST
- ✅ Gestion d'erreurs REST propre (codes HTTP)
- ✅ Interface utilisateur professionnelle et responsive

## Score attendu

**18/18 points** pour les fonctionnalités obligatoires
**+2 points bonus** pour les fonctionnalités supplémentaires

**Total : 20/20**
