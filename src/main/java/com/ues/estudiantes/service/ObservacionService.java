package com.ues.estudiantes.service;

import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.repository.ObservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ObservacionService {

    @Autowired
    private ObservacionRepository observacionRepository;

    // Método para obtener todas las observaciones
    public List<Observacion> obtenerTodas() {
        return observacionRepository.findAll();
    }

    // Obtener observaciones de un estudiante
    public List<Observacion> listarPorEstudiante(Long estudianteId) {
        return observacionRepository.findByEstudianteId(estudianteId);
    }

    // Buscar por tipo y estudiante
    public List<Observacion> buscarPorTipo(Long estudianteId, String tipo) {
        return observacionRepository.findByEstudianteIdAndTipo(estudianteId, tipo);
    }

    // Filtrar por estudiante, tipo y rango de fechas
    public List<Observacion> buscarPorFiltros(Long estudianteId, String tipo, LocalDate desde, LocalDate hasta) {
        if (tipo != null && !tipo.isEmpty() && desde != null && hasta != null) {
            return observacionRepository.findByEstudianteIdAndTipoAndFechaBetween(estudianteId, tipo, desde, hasta);
        } else if (desde != null && hasta != null) {
            return observacionRepository.findByEstudianteIdAndFechaBetween(estudianteId, desde, hasta);
        } else if (tipo != null && !tipo.isEmpty()) {
            return observacionRepository.findByEstudianteIdAndTipo(estudianteId, tipo);
        } else {
            return observacionRepository.findByEstudianteId(estudianteId);
        }
    }

    // Guardar o actualizar observacion
    public Observacion guardar(Observacion observacion) {
        return observacionRepository.save(observacion);
    }

    // Eliminar por id
    public void eliminar(Long id) {
        observacionRepository.deleteById(id);
    }

    // Buscar por id
    public Optional<Observacion> obtenerPorId(Long id) {
        return observacionRepository.findById(id);
    }

    public void eliminarPorEstudianteId(Long estudianteId) {
        observacionRepository.deleteByEstudianteId(estudianteId);
    }
}
