package mx.edu.uteq.grupos.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.uteq.grupos.model.entity.Grupo;

public interface GrupoRepo extends JpaRepository <Grupo, Integer>{
 List<Grupo> findByCarrera(String carrera);
 Optional<Grupo> findByNombre(String nombre);
}
