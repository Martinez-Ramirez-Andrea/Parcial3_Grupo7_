package com.ues.estudiantes.controller;

import com.ues.estudiantes.model.Estudiante;
import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.repository.EstudianteRepository;
import com.ues.estudiantes.service.ObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/historial")
public class HistorialObservacionesController {

    @Autowired
    private ObservacionService observacionService;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/{id}")
    public String verHistorial(
            @PathVariable Long id,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta,
            Model model
    ) {
        System.out.println("DEBUG >>> tipo: " + tipo + ", desde: " + desde + ", hasta: " + hasta);

        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);

        if (estudiante == null) {
            model.addAttribute("error", "Estudiante no encontrado");
            return "error";
        }

        // Convertir Date a LocalDate para el service
        LocalDate desdeLocal = (desde != null) ? Instant.ofEpochMilli(desde.getTime()).atZone(ZoneId.systemDefault()).toLocalDate() : null;
        LocalDate hastaLocal = (hasta != null) ? Instant.ofEpochMilli(hasta.getTime()).atZone(ZoneId.systemDefault()).toLocalDate() : null;

        List<Observacion> historial = observacionService.buscarPorFiltros(id, tipo, desdeLocal, hastaLocal);

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("historial", historial);
        model.addAttribute("tipo", tipo);
        model.addAttribute("desde", desde);
        model.addAttribute("hasta", hasta);

        return "historial/historial-observaciones";
    }
}
