package com.iut.fitness.controller;

import com.iut.fitness.model.Exercise;
import com.iut.fitness.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    
    @Autowired
    private ExerciseService exerciseService;
    
    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/category/{category}")
    public List<Exercise> getExercisesByCategory(@PathVariable String category) {
        return exerciseService.getExercisesByCategory(category);
    }
    
    @PostMapping
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseService.saveExercise(exercise);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
        return exerciseService.getExerciseById(id)
                .map(existingExercise -> {
                    exercise.setId(id);
                    return ResponseEntity.ok(exerciseService.saveExercise(exercise));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        if (exerciseService.getExerciseById(id).isPresent()) {
            exerciseService.deleteExercise(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
