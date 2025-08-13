package com.fuctura.biblioteca.repositories;

import com.fuctura.biblioteca.models.Categoria;


import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNome(String nome);

    Optional<Categoria> findByNomeIgnoreCaseContaining(String nome);

    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.livros WHERE c.id = :id")
    Optional<Categoria> findByIdWithLivros(@Param("id") Integer id);

    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.livros WHERE c.nome = :nome")
    Optional<Categoria> findByNomeWithLivros(@Param("nome") String nome);

    List<Categoria> findByNomeContaining(String nome);

    //Método com paginação
    Page<Categoria> findByNomeContaining(String nome, Pageable pageable);

    // Versão alternativa com ordenação padrão
    Page<Categoria> findByNomeContainingOrderByNomeAsc(String nome, Pageable pageable);

}
