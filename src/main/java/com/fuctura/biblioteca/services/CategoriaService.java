package com.fuctura.biblioteca.services;

import com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Categoria update(Categoria categoria) {
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }

    public void delete(Integer id) {

        categoriaRepository.deleteById(id);
    }

    private void findByNome(Categoria categoria) {
        Optional<Categoria> cat = categoriaRepository.findByNome(categoria.getNome());
        if (cat.isPresent()) {
            throw new ObjectNotFoundException("Categoria já cadastrada com o nome: " + categoria.getNome());
        }
    }  

}