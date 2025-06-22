package com.ues.estudiantes.service;

import com.ues.estudiantes.model.Estudiante;
import com.ues.estudiantes.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository repo;
    private final ObservacionService observacionService;

    
    public EstudianteService(EstudianteRepository repo, ObservacionService observacionService) {
        this.repo = repo;
        this.observacionService = observacionService;
    }

    public List<Estudiante> obtenerTodos() {
        return repo.findAll();
    }

    public Optional<Estudiante> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Estudiante guardar(Estudiante e) {
        return repo.save(e);
    }

    public List<Estudiante> buscar(String palabra) {
        return repo.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(palabra, palabra);
    }

    
    @Transactional
    public void eliminar(Long id) {
        observacionService.eliminarPorEstudianteId(id); 
        repo.deleteById(id); 
    }
}
