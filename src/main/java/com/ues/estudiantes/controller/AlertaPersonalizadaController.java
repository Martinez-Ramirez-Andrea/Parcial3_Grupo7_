package com.ues.estudiantes.controller;

import com.ues.estudiantes.model.AlertaPersonalizada;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alertas")
public class AlertaPersonalizadaController {
    // Muestra el formulario para crear una alerta personalizada
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("alertaPersonalizada", new AlertaPersonalizada());
        return "observaciones/crear_alerta"; // Nombre del archivo Thymeleaf
    }

}
