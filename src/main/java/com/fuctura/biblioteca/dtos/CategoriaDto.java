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

    @NotNull (message = "O nome da categoria não pode ser nulo")
    @Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50) caracteres")
    private String nome;

    @NotNull
    @Length(min = 5, max = 200, message = "A descrição deve ter entre 5 e 200 caracteres")
    private String descricao;
    private List<Livro> livros = new ArrayList<>();

    public CategoriaDto (Categoria categoria) {
     }

    public CategoriaDto() {}

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

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}

