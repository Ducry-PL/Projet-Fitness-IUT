# Emplacement des boutons CRUD dans l'interface

## Page : Liste des Routines (`/routines`)

```
┌─────────────────────────────────────────────────────────────────┐
│ Header: Fitness IUT                                              │
└─────────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────────┐
│                                                                   │
│  Liste des Routines                   [Nouvelle Routine] ← CRÉER │
│                                                                   │
│  [Recherche: _____________] [Rechercher] [Réinitialiser]        │
│                                                                   │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │ ID │ Nom            │ Description │ Date │ Statut │Actions││  │
│  ├───────────────────────────────────────────────────────────┤  │
│  │ 1  │ Routine Déb... │ Programme..│...   │ Active │ [Détails] [Modifier] [Supprimer] │
│  │ 2  │ Force et Pu... │ Programme..│...   │ Active │ [Détails] [Modifier] [Supprimer] │
│  └───────────────────────────────────────────────────────────┘  │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

**Bouton "Nouvelle Routine" :**
- Position : En haut à droite, à côté du titre
- Couleur : Bleu (btn-primary)
- Action : Ouvre le formulaire de création de routine

---

## Page : Détail d'une Routine (`/routines/{id}`)

```
┌─────────────────────────────────────────────────────────────────┐
│ Header: Fitness IUT                                              │
└─────────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────────┐
│                                                                   │
│  Détail de la Routine            [Modifier] [Retour à la liste] │
│                                                                   │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │ ID: 1                                                    │    │
│  │ Nom: Routine Débutant                                   │    │
│  │ Description: Programme pour débutants                   │    │
│  │ Date de création: 15/01/2024                            │    │
│  │ Statut: [Active]                                        │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                   │
│  Exercices associés              [Ajouter un Exercice] ← AJOUTER│
│                                                                   │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │ ID │ Nom     │ Répétitions │ Poids (kg) │ Actions        │  │
│  ├───────────────────────────────────────────────────────────┤  │
│  │ 1  │ Pompes  │     15      │    0.0     │ [Modifier] [Supprimer] │ ← MODIFIER
│  │ 2  │ Squats  │     20      │    0.0     │ [Modifier] [Supprimer] │
│  │ 3  │ Abdos   │     30      │    0.0     │ [Modifier] [Supprimer] │
│  └───────────────────────────────────────────────────────────┘  │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

**Bouton "Ajouter un Exercice" :**
- Position : Au-dessus du tableau des exercices, à droite
- Couleur : Bleu (btn-primary)
- Action : Ouvre le formulaire d'ajout d'exercice

**Bouton "Modifier" (pour chaque exercice) :**
- Position : Dans la colonne "Actions" de chaque ligne du tableau
- Couleur : Orange (btn-warning)
- Action : Ouvre le formulaire de modification avec les données pré-remplies

---

## Page : Formulaire de Création de Routine (`/routines/create`)

```
┌─────────────────────────────────────────────────────────────────┐
│ Header: Fitness IUT                                              │
└─────────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────────┐
│                                                                   │
│  Créer une Routine                              [Annuler]        │
│                                                                   │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                                                          │    │
│  │  Nom de la routine *                                    │    │
│  │  [_____________________________________]                │    │
│  │                                                          │    │
│  │  Description                                            │    │
│  │  [_____________________________________]                │    │
│  │  [_____________________________________]                │    │
│  │  [_____________________________________]                │    │
│  │                                                          │    │
│  │  Statut *                                               │    │
│  │  [▼ -- Sélectionner un statut --     ]                 │    │
│  │     - Active                                            │    │
│  │     - Inactive                                          │    │
│  │                                                          │    │
│  │  [Créer] [Annuler]                                      │    │
│  │                                                          │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

---

## Page : Formulaire d'Ajout d'Exercice (`/routines/{routineId}/exercises/create`)

```
┌─────────────────────────────────────────────────────────────────┐
│ Header: Fitness IUT                                              │
└─────────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────────┐
│                                                                   │
│  Ajouter un Exercice                            [Annuler]        │
│                                                                   │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │ Routine: Routine Débutant                               │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                   │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                                                          │    │
│  │  Nom de l'exercice *                                    │    │
│  │  [_____________________________________]                │    │
│  │                                                          │    │
│  │  Nombre de répétitions *                                │    │
│  │  [_____________________________________]                │    │
│  │                                                          │    │
│  │  Poids (kg) *                                           │    │
│  │  [_____________________________________]                │    │
│  │                                                          │    │
│  │  [Ajouter] [Annuler]                                    │    │
│  │                                                          │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

---

## Page : Formulaire de Modification d'Exercice (`/routines/{routineId}/exercises/{exerciseId}/edit`)

```
┌─────────────────────────────────────────────────────────────────┐
│ Header: Fitness IUT                                              │
└─────────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────────┐
│                                                                   │
│  Modifier l'Exercice                            [Annuler]        │
│                                                                   │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │ Routine: Routine Débutant                               │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                   │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                                                          │    │
│  │  Nom de l'exercice *                                    │    │
│  │  [Pompes__________________________]  ← Pré-rempli      │    │
│  │                                                          │    │
│  │  Nombre de répétitions *                                │    │
│  │  [15______________________________]  ← Pré-rempli      │    │
│  │                                                          │    │
│  │  Poids (kg) *                                           │    │
│  │  [0.0_____________________________]  ← Pré-rempli      │    │
│  │                                                          │    │
│  │  [Modifier] [Annuler]                                   │    │
│  │                                                          │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

---

## Résumé des couleurs des boutons

- **Bleu (btn-primary)** : Actions principales (Créer, Ajouter)
- **Orange (btn-warning)** : Modifier
- **Rouge (btn-danger)** : Supprimer
- **Gris (btn-secondary)** : Annuler, Retour
- **Turquoise (btn-info)** : Détails

---

## Navigation complète

```
Liste des Routines
    |
    ├─> [Nouvelle Routine] → Formulaire Créer Routine → Enregistrer → Retour Liste
    |
    └─> [Détails] → Détail de la Routine
                        |
                        ├─> [Modifier] → Formulaire Modifier Routine → Enregistrer → Retour Liste
                        |
                        ├─> [Ajouter un Exercice] → Formulaire Ajouter Exercice → Enregistrer → Retour Détail
                        |
                        └─> [Modifier] (exercice) → Formulaire Modifier Exercice → Enregistrer → Retour Détail
```
