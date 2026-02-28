package com.fitness.iut.controller;

import com.fitness.iut.entity.Routine;
import com.fitness.iut.repository.RoutineRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/routines")
public class RoutineRestController {

    @Autowired
    private RoutineRepository routineRepository;

    @GetMapping
    public ResponseEntity<?> getAllRoutines(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String mc) {

        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Routine> routinesPage;

            if (mc != null && !mc.trim().isEmpty()) {
                routinesPage = routineRepository.searchRoutines(mc.trim(), pageable);
            } else {
                routinesPage = routineRepository.findAll(pageable);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("routines", routinesPage.getContent());
            response.put("currentPage", routinesPage.getNumber());
            response.put("totalItems", routinesPage.getTotalElements());
            response.put("totalPages", routinesPage.getTotalPages());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(routineRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoutineById(@PathVariable Long id) {
        return routineRepository.findById(id)
                .map(routine -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", routine.getId());
                    response.put("name", routine.getName());
                    response.put("description", routine.getDescription());
                    response.put("creationDate", routine.getCreationDate());
                    response.put("status", routine.getStatus());
                    response.put("exercises", routine.getExercises());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Routine non trouvée")));
    }

    @PostMapping
    public ResponseEntity<?> createRoutine(@Valid @RequestBody Routine routine) {
        try {
            Routine savedRoutine = routineRepository.save(routine);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRoutine);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Erreur lors de la création de la routine"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoutine(@PathVariable Long id) {
        if (!routineRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Routine non trouvée"));
        }

        try {
            routineRepository.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Routine supprimée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur lors de la suppression"));
        }
    }
}
