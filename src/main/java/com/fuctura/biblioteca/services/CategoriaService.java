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

    public List<Categoria> findAll() {
        List<Categoria> list = categoriaRepository.findAll();
        return list;
    }

    public Categoria save(Categoria categoria) {
        categoriaRepository.findByNome(categoria.getNome());
        categoria.getId();
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }

    public Categoria update(Categoria categoria) {
        findById(categoria.getId());
        findByNome(categoria);
        Categoria cat = categoriaRepository.save(categoria);
        return cat;
    }
    public void delete(Integer id) {
     Categoria cat = findById(id);
     if (!cat.getLivros().isEmpty()) {
         new DataIntegrityViolationException("Categoria não pode ser deletada, pois possui livros associados.");

        } else {
         categoriaRepository.deleteById(id);
        }
        //findById(id);
        //categoriaRepository.deleteById(id);
        //return ResponseEntity.noContent().build();
    }
    private void findByNome(Categoria categoria) {
        Optional<Categoria> cat = categoriaRepository.findByNome(categoria.getNome());
        if (cat.isPresent()) {
            return;
        }
        String nome = "";
        throw new ObjectNotFoundException("Categoria não encontrada com o nome: " + nome);
    }

    public Categoria findByNomeIgnoreCaseContaining(Categoria categoria) {

        Optional<Categoria> cat = categoriaRepository.findByNomeIgnoreCaseContaining(categoria.getNome());
        if (categoria.isPresent()) {
            return categoria.get();
        }
        String nome = "";
        throw new ObjectNotFoundException("Categoria não encontrada com o nome: " + nome);
    }

    public Page<Categoria> buscarPorNome(String nome, int pagina, int itensPorPagina, String ordenarPor) {
        Pageable pageable = PageRequest.of(pagina, itensPorPagina, Sort.by(ordenarPor));
        if (pagina < 0) {
            throw new IllegalArgumentException("Número da página não pode ser negativo");
        }
        if (itensPorPagina < 1 || itensPorPagina > 100) {
            throw new IllegalArgumentException("Itens por página deve estar entre 1 e 100");
        }
        return categoriaRepository.findByNomeContaining(nome, pageable);

        // Ou para ordenação fixa:
        // return repository.findByNomeContainingOrderByNomeAsc(nome, pageable);
    } public void deleteById (Integer id) {
        findById(id);
        categoriaRepository.deleteById(id);
    }

    public Page<Categoria> listarTodas(int pagina, int itensPorPagina, String ordenarPor) {
        Pageable pageable = PageRequest.of(pagina, itensPorPagina, Sort.by(ordenarPor));
        return categoriaRepository.findAll(pageable);
    }


}


