package com.fuctura.biblioteca.services;

import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repositories.CategoriaRepository;
import com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final CategoriaRepository categoriaRepository;

    public LivroService(LivroRepository livroRepository,
                        CategoriaRepository categoriaRepository) {
        this.livroRepository = livroRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Livro save(Livro livro) {
        // Validação explícita
        if (livro.getCategoria() == null || livro.getCategoria().getId() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }

        // Carrega a categoria completa do banco
        Categoria categoria = categoriaRepository.findById(livro.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        // Associa a categoria ao livro
        livro.setCategoria(categoria);

        return livroRepository.save(livro);
    }

    public Livro getLivroById(Integer id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o id: " + id));
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public void deleteLivro(Integer id) {
        livroRepository.deleteById(id);
    }

    public Livro findById(Integer id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o id: " + id));
    }

    public Livro update(Livro livro) {
        // Verifica se o livro existe
        if (!livroRepository.existsById(livro.getId())) {
            throw new RuntimeException("Livro não encontrado com o id: " + livro.getId());
        }
        return livroRepository.save(livro);
    }

    public void delete(Integer id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com o id: " + id);
        }
        livroRepository.deleteById(id);
    }

    public Page<Livro> buscarPorNome(String nome, int pagina, int itensPorPagina, String ordenarPor) {
        Pageable pageable = PageRequest.of(pagina, itensPorPagina, Sort.by(ordenarPor));

        if (nome != null && !nome.isEmpty()) {
            return livroRepository.findByTituloContainingIgnoreCase(nome, pageable);
        }
        return livroRepository.findAll(pageable);
    }

    public Page<Livro> listarTodas(int pagina, int itensPorPagina, String ordenarPor) {
        return livroRepository.findAll(PageRequest.of(pagina, itensPorPagina, Sort.by(ordenarPor)));
    }
}
