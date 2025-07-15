package mx.edu.uteq.grupos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.grupos.model.entity.Grupo;
import mx.edu.uteq.grupos.model.repository.GrupoRepo;
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/grupo")

public class GrupoController {
    @Autowired
    private GrupoRepo grupoRepo;

    @GetMapping
    public List<Grupo> getGrupos() {
        return grupoRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGrupo(@PathVariable int id) {
        Optional<Grupo> opt = grupoRepo.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }
    //Buscar por nombre de carrera
    @GetMapping("/carrera/{carrera}")
    public ResponseEntity<?> getGrupoCarrera(@PathVariable String carrera) {
        List<Grupo> grupos = grupoRepo.findByCarrera(carrera);
        if (!grupos.isEmpty()) {
           return ResponseEntity.ok(grupos);
        }
        return ResponseEntity.notFound().build();
    }
    //Buscar por nombre de grupo
    @GetMapping("/nombre-grupo/{nombre}")
    public ResponseEntity<?> getGrupoNombre(@PathVariable String nombre) {
        Optional<Grupo> opt = grupoRepo.findByNombre(nombre);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }
    //Agregar un nuevo grupo
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Grupo grupo) {
        return ResponseEntity.ok(grupoRepo.save(grupo));
    }
    //Modificar un grupo
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Grupo grupo) {
        Optional<Grupo> opt = grupoRepo.findById(id);
        if (opt.isPresent()) {
            Grupo g = opt.get();
            g.setNombre(grupo.getNombre());
            g.setCarrera(grupo.getCarrera());
            g.setEstado(grupo.isEstado());
            return ResponseEntity.ok(grupoRepo.save(g));
        }
        return ResponseEntity.notFound().build();
    }
    //Eliminar un grupo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Grupo> opt = grupoRepo.findById(id);
        if (opt.isPresent()) {
            grupoRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

}
