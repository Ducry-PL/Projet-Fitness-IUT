package com.fitness.iut.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 120, message = "Le nom doit contenir entre 2 et 120 caractères")
    @Column(nullable = false, length = 120)
    private String name;

    @NotNull(message = "Le nombre de répétitions est obligatoire")
    @Min(value = 1, message = "Le nombre de répétitions doit être au moins 1")
    @Column(nullable = false)
    private Integer repetitions;

    @NotNull(message = "Le poids est obligatoire")
    @Min(value = 0, message = "Le poids doit être positif ou nul")
    @Column(nullable = false)
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    @JsonIgnore
    private Routine routine;

    // Constructors
    public Exercise() {
    }

    public Exercise(String name, Integer repetitions, Double weight) {
        this.name = name;
        this.repetitions = repetitions;
        this.weight = weight;
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

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
