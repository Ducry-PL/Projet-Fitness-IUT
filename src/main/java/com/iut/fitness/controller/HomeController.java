package com.iut.fitness.controller;

import com.iut.fitness.service.ExerciseService;
import com.iut.fitness.service.UserService;
import com.iut.fitness.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ExerciseService exerciseService;
    
    @Autowired
    private WorkoutPlanService workoutPlanService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userCount", userService.getAllUsers().size());
        model.addAttribute("exerciseCount", exerciseService.getAllExercises().size());
        model.addAttribute("workoutPlanCount", workoutPlanService.getAllWorkoutPlans().size());
        return "index";
    }
    
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
    
    @GetMapping("/exercises")
    public String exercises(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        return "exercises";
    }
    
    @GetMapping("/workout-plans")
    public String workoutPlans(Model model) {
        model.addAttribute("workoutPlans", workoutPlanService.getAllWorkoutPlans());
        return "workout-plans";
    }
}
