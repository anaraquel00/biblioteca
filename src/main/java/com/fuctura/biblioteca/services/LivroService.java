package com.fuctura.biblioteca.services;

import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    LivroRepository livroRepository;
    @Autowired
    public static Livro save(Livro livro) {

        LivroRepository livroRepository = null;
        livroRepository.save(livro); // Save the book using the repository
        return livro; // Return the saved book
    }

    public Livro createLivro(Livro livro) {

        LivroRepository livroRepository = null;
        livroRepository.save();
        return livro; // Return the saved book
     }

    public Livro getLivroById(Integer id) {
        // Logic to retrieve a book by its ID
        // This could involve calling a repository method to find the book in the database.
        // For example:
        LivroRepository livroRepository = null;
        return livroRepository.findById(id).orElse(null); // Return the book if found, or null if not found
    }

    public List<Livro> findAll() {

        LivroRepository livroRepository = null;
        return livroRepository.findAll();
    }

     public void deleteLivro(Integer id) {

       LivroRepository livroRepository = null;
        livroRepository.deleteById(id); // Delete the book by its ID
     }

    public Livro findById(Integer id) {
        LivroRepository livroRepository = null;
        return livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado com o id: " + id));
    }

    public Livro update(Livro livro) {
        LivroRepository livroRepository = null;
        Livro existingLivro = findById(livro.getId());
        existingLivro.setTitulo(livro.getTitulo());
        existingLivro.setAutor(livro.getAutor());
        existingLivro.setCategoria(livro.getCategoria());
        return livroRepository.save(existingLivro);
    }

    public void delete(Integer id) {
        LivroRepository livroRepository = null;
        Livro livro = findById(id);
        if (livro != null) {
            livroRepository.deleteById(id); // Delete the book by its ID
        } else {
            throw new RuntimeException("Livro não encontrado com o id: " + id);
        }
    }
}
