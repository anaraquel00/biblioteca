package com.fuctura.biblioteca.repositories;

import com.fuctura.biblioteca.models.Categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNome(String nome);
    //@Query("SELECT c FROM Categoria as c WHERE LOWER(c.nome) = LOWER(:nome)") //consulta JPQL
    Optional<Categoria> findByNomeIgnoreCaseContaining(String nome);



}
