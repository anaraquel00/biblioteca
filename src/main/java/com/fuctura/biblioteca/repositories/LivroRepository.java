package com.fuctura.biblioteca.repositories;

import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    //@Query(value = "SELECT obj FROM Livro obj WHERE obj.categoria.id =:id_cat")
    //List<Livro> findByCategoriaId(@Param(value = "id_cat") Integer id_cat);

    List<Livro> findByCategoriaId(Integer id_cat, Pageable pageable);

    Optional<Livro> findByTitulo(String titulo);
    //@Query("SELECT c FROM Categoria as c WHERE LOWER(c.nome) = LOWER(:nome)") //consulta JPQL
    Optional<Livro> findByTituloIgnoreCaseContaining(String titulo);

    @Query("SELECT l FROM Livro l LEFT JOIN FETCH l.categoria")
    List<Livro> findAllWithCategoria();

    Page<Livro> findByTituloContaining(String titulo, Pageable pageable);
    // Versão alternativa com ordenação padrão
    Page<Livro> findByTituloContainingOrderByTituloAsc(String titulo, Pageable pageable);

    Page<Livro> findByTituloContainingIgnoreCase(String titulo, PageRequest of);
    List<Livro> findByAutorContaining(String autor);
    List<Livro> findByTituloContainingAndAutorContaining(String titulo, String autor);

    Page<Livro> findByAutorContainingIgnoreCase(String autor, PageRequest of);
    Page<Livro> findByCategoria(Categoria categoria, Pageable pageable);
    Page<Livro> findByCategoriaAndTituloContainingIgnoreCase(Categoria categoria, String titulo, Pageable pageable);

    Page<Livro> findByTituloContainingIgnoreCase(String nome, Pageable pageable);

}
