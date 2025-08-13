package com.fuctura.biblioteca.services;

import com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;
    
    public Page<Livro> findAllPaged(Integer id_cat, int page, int linesPerPage, String direction, String orderBy) {
        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        if (id_cat == 0) {
            return livroRepository.findAll(pageable);
        } else {
            return (Page<Livro>) livroRepository.findByCategoriaId(id_cat, pageable);
        }
    }

    public Livro findById(Integer id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new ObjectNotFoundException("Livro não encontrada com o id: " + id);
    }

    public List<Livro> findAll(Integer id_cat) {
        categoriaService.findById(id_cat);
        Pageable pageable = Pageable.unpaged();
        List<Livro> list =  livroRepository.findByCategoriaId(id_cat, pageable);
        return list;
    }

    public List<Livro> findAll() {
        return livroRepository.findAll(); // Método padrão do JpaRepository
    }

    public Livro save(Integer categoriaId, Livro livro) {
        // Verifica se a categoria existe
        Categoria cat = categoriaService.findById(categoriaId);

        livro.setCategoria(cat);
        return livroRepository.save(livro);
    }

    public Livro update(Integer id_cat, Livro livro) {
        Livro existingLivro = findById(livro.getId());

        Categoria cat = categoriaService.findById(id_cat);

        existingLivro.setTitulo(livro.getTitulo());
        existingLivro.setAutor(livro.getAutor());
        existingLivro.setCategoria(cat);
        existingLivro.setTexto(livro.getTexto());
        existingLivro.setTamanho(livro.getTamanho());

        return livroRepository.save(existingLivro);

    }

    public void delete(Integer id) {
        findById(id);
        livroRepository.deleteById(id);
    }
}
