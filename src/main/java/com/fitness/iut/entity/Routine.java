package com.fitness.iut.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routines")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 120, message = "Le nom doit contenir entre 3 et 120 caractères")
    @Column(nullable = false, length = 120)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotBlank(message = "Le statut est obligatoire")
    @Pattern(regexp = "active|inactive", message = "Le statut doit être 'active' ou 'inactive'")
    @Column(nullable = false, length = 20)
    private String status;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();

    // Constructors
    public Routine() {
        this.creationDate = LocalDate.now();
        this.status = "active";
    }

    public Routine(String name, String description, String status) {
        this();
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    // Helper methods
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        exercise.setRoutine(this);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
        exercise.setRoutine(null);
    }
}
