package com.iut.fitness.service;

import com.iut.fitness.model.Exercise;
import com.iut.fitness.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository exerciseRepository;
    
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
    
    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }
    
    public List<Exercise> getExercisesByCategory(String category) {
        return exerciseRepository.findByCategory(category);
    }
    
    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
    
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
