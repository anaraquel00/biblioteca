package com.fuctura.biblioteca.dtos;


import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CategoriaDto {


    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @Length(min = 4, max = 50, message = "O nome deve ter entre 4 e 50 caracteres")
    private String nome;

    @NotNull(message = "A descrição não pode ser nula")
    @Length(min = 5, max = 200, message = "A descrição deve ter entre 5 e 200 caracteres")
    private String descricao;


    public CategoriaDto() {
    }

    public CategoriaDto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

