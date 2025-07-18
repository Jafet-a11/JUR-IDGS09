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
import mx.edu.uteq.grupos.service.GrupoService;
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/grupo")

public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public ResponseEntity<List<Grupo>> getGrupos() {
        return ResponseEntity.ok(grupoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGrupo(@PathVariable int id) {
        Optional<Grupo> opt = grupoService.getById(id);
        return opt.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/carrera/{carrera}")
    public ResponseEntity<?> getGrupoCarrera(@PathVariable String carrera) {
        List<Grupo> grupos = grupoService.getByCarrera(carrera);
        return grupos.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(grupos);
    }

    @GetMapping("/nombre-grupo/{nombre}")
    public ResponseEntity<?> getGrupoNombre(@PathVariable String nombre) {
        Optional<Grupo> opt = grupoService.getByNombre(nombre);
        return opt.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Grupo> create(@RequestBody Grupo grupo) {
        return ResponseEntity.ok(grupoService.save(grupo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Grupo grupo) {
        Optional<Grupo> updated = grupoService.update(id, grupo);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean deleted = grupoService.delete(id);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                       : ResponseEntity.notFound().build();
    }
}