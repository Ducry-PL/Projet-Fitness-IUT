# Guide d'utilisation des fonctionnalités CRUD

## ✅ Toutes les fonctionnalités CRUD sont déjà implémentées !

### 1. Créer une Routine

**Où trouver cette fonctionnalité ?**
- Allez sur la page principale : http://localhost:8081/routines
- Cliquez sur le bouton bleu **"Nouvelle Routine"** en haut à droite
- Remplissez le formulaire avec :
  - Nom de la routine (obligatoire, 3-120 caractères)
  - Description (optionnel)
  - Statut : Active ou Inactive (obligatoire)
- Cliquez sur **"Créer"**

**Route du contrôleur :**
- GET `/routines/create` - Affiche le formulaire
- POST `/routines/create` - Enregistre la routine

**Fichiers concernés :**
- Controller : `RoutineController.java` lignes 69-106
- Template : `routines/form.html`

---

### 2. Ajouter un Exercice

**Où trouver cette fonctionnalité ?**
- Allez sur la page de détail d'une routine : http://localhost:8081/routines/{id}
- Dans la section "Exercices associés", cliquez sur le bouton bleu **"Ajouter un Exercice"**
- Remplissez le formulaire avec :
  - Nom de l'exercice (obligatoire, 2-120 caractères)
  - Nombre de répétitions (obligatoire, >= 1)
  - Poids en kg (obligatoire, >= 0)
- Cliquez sur **"Ajouter"**

**Route du contrôleur :**
- GET `/routines/{routineId}/exercises/create` - Affiche le formulaire
- POST `/routines/{routineId}/exercises/create` - Enregistre l'exercice

**Fichiers concernés :**
- Controller : `RoutineController.java` lignes 179-225
- Template : `exercises/form.html`

---

### 3. Modifier un Exercice

**Où trouver cette fonctionnalité ?**
- Allez sur la page de détail d'une routine : http://localhost:8081/routines/{id}
- Dans le tableau des exercices, trouvez l'exercice à modifier
- Cliquez sur le bouton orange **"Modifier"** à droite de l'exercice
- Le formulaire apparaît pré-rempli avec les données actuelles
- Modifiez les champs souhaités
- Cliquez sur **"Modifier"**

**Route du contrôleur :**
- GET `/routines/{routineId}/exercises/{exerciseId}/edit` - Affiche le formulaire
- POST `/routines/{routineId}/exercises/{exerciseId}/edit` - Met à jour l'exercice

**Fichiers concernés :**
- Controller : `RoutineController.java` lignes 227-284
- Template : `exercises/form.html`

---

## Vérification rapide

Pour vérifier que tout fonctionne :

1. **Démarrer l'application**
```bash
java -jar target/fitness-iut-1.0.0.jar
```

2. **Accéder à l'application**
```
http://localhost:8081
```

3. **Tester "Créer une routine"**
   - Vous devriez voir le bouton "Nouvelle Routine" sur la page d'accueil
   - En cliquant dessus, vous accédez à un formulaire avec 3 champs

4. **Tester "Ajouter un exercice"**
   - Cliquez sur "Détails" d'une routine existante
   - Vous devriez voir le bouton "Ajouter un Exercice"
   - En cliquant dessus, vous accédez à un formulaire avec 3 champs

5. **Tester "Modifier un exercice"**
   - Dans la page de détail d'une routine avec des exercices
   - Chaque ligne du tableau a un bouton "Modifier" (orange)
   - En cliquant dessus, vous accédez au formulaire pré-rempli

---

## Messages de confirmation

Après chaque action, vous devriez voir un message vert de confirmation :
- "Routine créée avec succès !"
- "Exercice ajouté avec succès !"
- "Exercice modifié avec succès !"

---

## En cas de problème

Si vous ne voyez pas ces boutons ou si les formulaires ne fonctionnent pas :

1. Vérifiez que la base de données est bien configurée et démarrée
2. Vérifiez que l'application démarre sans erreur
3. Vérifiez dans les logs qu'il n'y a pas d'erreur au démarrage
4. Consultez la console du navigateur pour voir s'il y a des erreurs JavaScript ou CSS

---

## Récapitulatif des URLs

| Fonctionnalité | URL |
|----------------|-----|
| Liste des routines | http://localhost:8081/routines |
| Créer une routine | http://localhost:8081/routines/create |
| Détail d'une routine | http://localhost:8081/routines/1 |
| Modifier une routine | http://localhost:8081/routines/1/edit |
| Ajouter un exercice | http://localhost:8081/routines/1/exercises/create |
| Modifier un exercice | http://localhost:8081/routines/1/exercises/2/edit |

(Remplacez les IDs 1 et 2 par les IDs réels de vos données)
