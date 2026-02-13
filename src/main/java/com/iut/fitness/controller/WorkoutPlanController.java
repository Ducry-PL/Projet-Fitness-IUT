package com.iut.fitness.controller;

import com.iut.fitness.model.WorkoutPlan;
import com.iut.fitness.service.WorkoutPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {
    
    @Autowired
    private WorkoutPlanService workoutPlanService;
    
    @GetMapping
    public List<WorkoutPlan> getAllWorkoutPlans() {
        return workoutPlanService.getAllWorkoutPlans();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanById(@PathVariable Long id) {
        return workoutPlanService.getWorkoutPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public List<WorkoutPlan> getWorkoutPlansByUserId(@PathVariable Long userId) {
        return workoutPlanService.getWorkoutPlansByUserId(userId);
    }
    
    @PostMapping
    public WorkoutPlan createWorkoutPlan(@Valid @RequestBody WorkoutPlan workoutPlan) {
        return workoutPlanService.saveWorkoutPlan(workoutPlan);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlan> updateWorkoutPlan(@PathVariable Long id, @Valid @RequestBody WorkoutPlan workoutPlan) {
        return workoutPlanService.getWorkoutPlanById(id)
                .map(existingPlan -> {
                    workoutPlan.setId(id);
                    return ResponseEntity.ok(workoutPlanService.saveWorkoutPlan(workoutPlan));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        if (workoutPlanService.getWorkoutPlanById(id).isPresent()) {
            workoutPlanService.deleteWorkoutPlan(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
