package com.ues.estudiantes.repository;

import com.ues.estudiantes.model.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ObservacionRepository extends JpaRepository<Observacion, Long> {
    
    void deleteByEstudianteId(Long estudianteId);

    List<Observacion> findByEstudianteId(Long estudianteId);

    List<Observacion> findByEstudianteIdAndTipo(Long estudianteId, String tipo);

    List<Observacion> findByEstudianteIdAndFechaBetween(Long estudianteId, LocalDate desde, LocalDate hasta);

    List<Observacion> findByEstudianteIdAndTipoAndFechaBetween(Long estudianteId, String tipo, LocalDate desde, LocalDate hasta);
}
