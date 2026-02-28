package com.fitness.iut.controller;

import com.fitness.iut.entity.Exercise;
import com.fitness.iut.entity.Routine;
import com.fitness.iut.repository.ExerciseRepository;
import com.fitness.iut.repository.RoutineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoutineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    private Routine testRoutine;

    @BeforeEach
    void setUp() {
        exerciseRepository.deleteAll();
        routineRepository.deleteAll();

        testRoutine = new Routine("Test Routine", "Test Description", "active");
        testRoutine = routineRepository.save(testRoutine);
    }

    @Test
    void createRoutineForm_shouldReturn200() throws Exception {
        mockMvc.perform(get("/routines/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("routines/form"))
                .andExpect(model().attributeExists("routine"))
                .andExpect(model().attribute("isEdit", false));
    }

    @Test
    void createRoutine_shouldRedirect() throws Exception {
        mockMvc.perform(post("/routines/create")
                        .param("name", "New Routine")
                        .param("description", "New Description")
                        .param("status", "active"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void createExerciseForm_shouldReturn200() throws Exception {
        mockMvc.perform(get("/routines/" + testRoutine.getId() + "/exercises/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("exercises/form"))
                .andExpect(model().attributeExists("exercise"))
                .andExpect(model().attributeExists("routine"))
                .andExpect(model().attribute("isEdit", false));
    }

    @Test
    void createExercise_shouldRedirect() throws Exception {
        mockMvc.perform(post("/routines/" + testRoutine.getId() + "/exercises/create")
                        .param("name", "Pompes")
                        .param("repetitions", "10")
                        .param("weight", "0"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void editExerciseForm_shouldReturn200() throws Exception {
        Exercise exercise = new Exercise("Pompes", 10, 0.0);
        exercise.setRoutine(testRoutine);
        exercise = exerciseRepository.save(exercise);

        mockMvc.perform(get("/routines/" + testRoutine.getId() + "/exercises/" + exercise.getId() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("exercises/form"))
                .andExpect(model().attributeExists("exercise"))
                .andExpect(model().attributeExists("routine"))
                .andExpect(model().attribute("isEdit", true));
    }

    @Test
    void editExercise_shouldRedirect() throws Exception {
        Exercise exercise = new Exercise("Pompes", 10, 0.0);
        exercise.setRoutine(testRoutine);
        exercise = exerciseRepository.save(exercise);

        mockMvc.perform(post("/routines/" + testRoutine.getId() + "/exercises/" + exercise.getId() + "/edit")
                        .param("name", "Pompes Modifiees")
                        .param("repetitions", "20")
                        .param("weight", "5"))
                .andExpect(status().is3xxRedirection());
    }
}
