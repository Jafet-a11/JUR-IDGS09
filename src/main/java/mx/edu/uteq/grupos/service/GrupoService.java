package mx.edu.uteq.grupos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uteq.grupos.model.entity.Grupo;
import mx.edu.uteq.grupos.model.repository.GrupoRepo;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepo repo;

    @Transactional(readOnly = true)
    public List<Grupo> getAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Grupo> getById(int id) {
        return repo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Grupo> getByCarrera(String carrera) {
        return repo.findByCarrera(carrera);
    }

    @Transactional(readOnly = true)
    public Optional<Grupo> getByNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    @Transactional
    public Grupo save(Grupo grupo) {
        return repo.save(grupo);
    }

    @Transactional
    public Optional<Grupo> update(int id, Grupo grupo) {
        Optional<Grupo> opt = repo.findById(id);
        if (opt.isPresent()) {
            Grupo g = opt.get();
            g.setNombre(grupo.getNombre());
            g.setCarrera(grupo.getCarrera());
            g.setEstado(grupo.isEstado());
            return Optional.of(repo.save(g));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(int id) {
        Optional<Grupo> opt = repo.findById(id);
        if (opt.isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
