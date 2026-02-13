# Projet-Fitness-IUT

Application Spring Boot de gestion de routines de fitness.

## Démarrage rapide

1. Importer la base de données :
```bash
mysql -u root -p < fitness_iut_dump.sql
```

2. Compiler le projet :
```bash
mvn clean package
```

3. Lancer l'application :
```bash
java -jar target/fitness-iut-1.0.0.jar
```

4. Accéder à l'application : http://localhost:8081

## Documentation complète

Voir le fichier [DOCUMENTATION.md](DOCUMENTATION.md) pour :
- Instructions d'installation détaillées
- Liste des fonctionnalités MVC
- Documentation complète de l'API REST
- Structure du projet
- Exemples d'utilisation