package com.fuctura.biblioteca.services;

import com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.repositories.CategoriaRepository;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.util.ClassUtils.ifPresent;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return categoria.get();
        }
        throw new ObjectNotFoundException("Categoria não encontrada com o id: " + id);
    }

    public Categoria findByNome(String nome) {
        Optional<Categoria> categoria = categoriaRepository.findByNomeIgnoreCaseContaining(nome);
        if (categoria.isPresent()) {
            return categoria.get();
        }
        throw new ObjectNotFoundException("Categoria não encontrada com o nome: " + nome);
    }

    public List<Categoria> findAll() {
        List<Categoria> list = categoriaRepository.findAll();
        return list;
    }

    public Categoria save(Categoria categoria) {
        findByNome(categoria);
        categoria.setId(null);
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }

    public void delete(Integer id) {
        Categoria cat = findById(id);
        if (!cat.getLivros().isEmpty()) {
            throw new DataIntegrityViolationException("Categoria não pode ser deletada, pois possui livros associados.");
        }
        categoriaRepository.deleteById(id);
    }

    public Categoria update(Categoria categoria) {
        findById(categoria.getId());
        findByNome(categoria);
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }

    private void findByNome(Categoria categoria) {
        Optional<Categoria> cat = categoriaRepository.findByNome(categoria.getNome());
        if (cat.isPresent() && cat.get().getNome().equals(categoria.getNome())) {
            throw new IllegalArgumentException("Categoria já existe com o nome: " + categoria.getNome());
        }
    }


}


