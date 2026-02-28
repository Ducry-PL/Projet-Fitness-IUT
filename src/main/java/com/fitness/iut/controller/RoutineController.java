package com.fitness.iut.controller;

import com.fitness.iut.entity.Exercise;
import com.fitness.iut.entity.Routine;
import com.fitness.iut.repository.ExerciseRepository;
import com.fitness.iut.repository.RoutineRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/routines")
public class RoutineController {

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping
    public String listRoutines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String mc,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Routine> routinesPage;

        if (mc != null && !mc.trim().isEmpty()) {
            routinesPage = routineRepository.searchRoutines(mc.trim(), pageable);
        } else {
            routinesPage = routineRepository.findAll(pageable);
        }

        model.addAttribute("routines", routinesPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("mc", mc != null ? mc : "");
        model.addAttribute("totalPages", routinesPage.getTotalPages());

        return "routines/list";
    }

    @GetMapping("/{id}")
    public String viewRoutine(@PathVariable Long id, Model model,
                             @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) Integer size,
                             @RequestParam(required = false) String mc) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine non trouvée"));

        model.addAttribute("routine", routine);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("mc", mc);

        return "routines/detail";
    }

    @GetMapping("/create")
    public String createRoutineForm(Model model,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) String mc) {
        model.addAttribute("routine", new Routine());
        model.addAttribute("isEdit", false);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("mc", mc);
        return "routines/form";
    }

    @PostMapping("/create")
    public String createRoutine(@Valid @ModelAttribute Routine routine,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer size,
                               @RequestParam(required = false) String mc) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            model.addAttribute("mc", mc);
            return "routines/form";
        }

        routineRepository.save(routine);
        redirectAttributes.addFlashAttribute("successMessage", "Routine créée avec succès !");
        
        String redirectUrl = "/routines?page=" + (page != null ? page : 0) + 
                            "&size=" + (size != null ? size : 10);
        if (mc != null && !mc.isEmpty()) {
            redirectUrl += "&mc=" + mc;
        }
        
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/{id}/edit")
    public String editRoutineForm(@PathVariable Long id, Model model,
                                 @RequestParam(required = false) Integer page,
                                 @RequestParam(required = false) Integer size,
                                 @RequestParam(required = false) String mc) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine non trouvée"));

        model.addAttribute("routine", routine);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("mc", mc);
        model.addAttribute("isEdit", true);

        return "routines/form";
    }

    @PostMapping("/{id}/edit")
    public String editRoutine(@PathVariable Long id,
                             @Valid @ModelAttribute Routine routine,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) Integer size,
                             @RequestParam(required = false) String mc) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            model.addAttribute("mc", mc);
            return "routines/form";
        }

        Routine existingRoutine = routineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Routine non trouvée"));

        existingRoutine.setName(routine.getName());
        existingRoutine.setDescription(routine.getDescription());
        existingRoutine.setStatus(routine.getStatus());

        routineRepository.save(existingRoutine);
        redirectAttributes.addFlashAttribute("successMessage", "Routine modifiée avec succès !");

        String redirectUrl = "/routines?page=" + (page != null ? page : 0) + 
                            "&size=" + (size != null ? size : 10);
        if (mc != null && !mc.isEmpty()) {
            redirectUrl += "&mc=" + mc;
        }

        return "redirect:" + redirectUrl;
    }

    @PostMapping("/{id}/delete")
    public String deleteRoutine(@PathVariable Long id,
                               RedirectAttributes redirectAttributes,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer size,
                               @RequestParam(required = false) String mc) {
        routineRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Routine supprimée avec succès !");

        String redirectUrl = "/routines?page=" + (page != null ? page : 0) + 
                            "&size=" + (size != null ? size : 10);
        if (mc != null && !mc.isEmpty()) {
            redirectUrl += "&mc=" + mc;
        }

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/{routineId}/exercises/create")
    public String createExerciseForm(@PathVariable Long routineId, Model model,
                                    @RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size,
                                    @RequestParam(required = false) String mc) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Routine non trouvée"));

        Exercise exercise = new Exercise();
        exercise.setRoutine(routine);

        model.addAttribute("exercise", exercise);
        model.addAttribute("routine", routine);
        model.addAttribute("isEdit", false);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("mc", mc);

        return "exercises/form";
    }

    @PostMapping("/{routineId}/exercises/create")
    public String createExercise(@PathVariable Long routineId,
                                @Valid @ModelAttribute Exercise exercise,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes,
                                @RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer size,
                                @RequestParam(required = false) String mc) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Routine non trouvée"));

        if (result.hasErrors()) {
            model.addAttribute("routine", routine);
            model.addAttribute("isEdit", false);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            model.addAttribute("mc", mc);
            return "exercises/form";
        }

        exercise.setRoutine(routine);
        exerciseRepository.save(exercise);

        redirectAttributes.addFlashAttribute("successMessage", "Exercice ajouté avec succès !");

        return buildExerciseRedirect(routineId, page, size, mc);
    }

    @GetMapping("/{routineId}/exercises/{exerciseId}/edit")
    public String editExerciseForm(@PathVariable Long routineId,
                                  @PathVariable Long exerciseId,
                                  Model model,
                                  @RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer size,
                                  @RequestParam(required = false) String mc) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Routine non trouvée"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercice non trouvé"));

        model.addAttribute("exercise", exercise);
        model.addAttribute("routine", routine);
        model.addAttribute("isEdit", true);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("mc", mc);

        return "exercises/form";
    }

    @PostMapping("/{routineId}/exercises/{exerciseId}/edit")
    public String editExercise(@PathVariable Long routineId,
                              @PathVariable Long exerciseId,
                              @Valid @ModelAttribute Exercise exercise,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              @RequestParam(required = false) Integer page,
                              @RequestParam(required = false) Integer size,
                              @RequestParam(required = false) String mc) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Routine non trouvée"));

        if (result.hasErrors()) {
            model.addAttribute("routine", routine);
            model.addAttribute("isEdit", true);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            model.addAttribute("mc", mc);
            return "exercises/form";
        }

        Exercise existingExercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercice non trouvé"));

        existingExercise.setName(exercise.getName());
        existingExercise.setRepetitions(exercise.getRepetitions());
        existingExercise.setWeight(exercise.getWeight());

        exerciseRepository.save(existingExercise);

        redirectAttributes.addFlashAttribute("successMessage", "Exercice modifié avec succès !");

        return buildExerciseRedirect(routineId, page, size, mc);
    }

    @PostMapping("/{routineId}/exercises/{exerciseId}/delete")
    public String deleteExercise(@PathVariable Long routineId,
                                @PathVariable Long exerciseId,
                                RedirectAttributes redirectAttributes,
                                @RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer size,
                                @RequestParam(required = false) String mc) {
        exerciseRepository.deleteById(exerciseId);

        redirectAttributes.addFlashAttribute("successMessage", "Exercice supprimé avec succès !");

        return buildExerciseRedirect(routineId, page, size, mc);
    }

    private String buildExerciseRedirect(Long routineId, Integer page, Integer size, String mc) {
        StringBuilder redirectUrl = new StringBuilder("/routines/" + routineId);
        
        boolean hasParams = false;
        if (page != null) {
            redirectUrl.append(hasParams ? "&" : "?").append("page=").append(page);
            hasParams = true;
        }
        if (size != null) {
            redirectUrl.append(hasParams ? "&" : "?").append("size=").append(size);
            hasParams = true;
        }
        if (mc != null && !mc.isEmpty()) {
            redirectUrl.append(hasParams ? "&" : "?").append("mc=").append(mc);
        }

        return "redirect:" + redirectUrl.toString();
    }
}
