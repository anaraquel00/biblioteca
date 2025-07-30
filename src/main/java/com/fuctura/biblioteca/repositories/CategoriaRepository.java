package com.fuctura.biblioteca.repositories;

import com.fuctura.biblioteca.models.Categoria;


import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNome(String nome);
    //@Query("SELECT c FROM Categoria as c WHERE LOWER(c.nome) = LOWER(:nome)") //consulta JPQL
    Optional<Categoria> findByNomeIgnoreCaseContaining(String nome);

    List<Categoria> findByNomeContaining(String nome);

    // Método com paginação
    Page<Categoria> findByNomeContaining(String nome, Pageable pageable);

    // Versão alternativa com ordenação padrão
    Page<Categoria> findByNomeContainingOrderByNomeAsc(String nome, Pageable pageable);

}
