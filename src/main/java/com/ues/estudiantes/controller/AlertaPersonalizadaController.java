package com.ues.estudiantes.controller;

import com.ues.estudiantes.model.AlertaPersonalizada;
import com.ues.estudiantes.model.Estudiante;
import com.ues.estudiantes.repository.AlertaPersonalizadaRepository;
import com.ues.estudiantes.repository.EstudianteRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alertas")
public class AlertaPersonalizadaController {

    private final AlertaPersonalizadaRepository alertaRepo;
    private final EstudianteRepository estudianteRepo;

    public AlertaPersonalizadaController(AlertaPersonalizadaRepository alertaRepo, EstudianteRepository estudianteRepo) {
        this.alertaRepo = alertaRepo;
        this.estudianteRepo = estudianteRepo;
    }

    // Mostrar formulario
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("alertaPersonalizada", new AlertaPersonalizada());
        model.addAttribute("estudiantes", estudianteRepo.findAll()); 
        return "observaciones/crear_alerta"; 
    }

    // Guardar alerta
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long estudianteId,
                          @RequestParam int umbralObservaciones,
                          @RequestParam int periodoDias,
                          @RequestParam String tipoObservacion) {

        AlertaPersonalizada alerta = new AlertaPersonalizada();
        alerta.setNumeroObservaciones(umbralObservaciones);
        alerta.setPeriodoDias(periodoDias);
        alerta.setTipoObservacion(tipoObservacion);

        Estudiante estudiante = estudianteRepo.findById(estudianteId).orElse(null);
        alerta.setEstudiante(estudiante);

        alertaRepo.save(alerta);
        return "redirect:/menu";
    }
}
