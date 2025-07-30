package com.fuctura.biblioteca.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Livro> livros = new ArrayList<>();

    public Categoria() {}

    public Categoria(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Categoria(Long categoriaId) {
    }

    //generate getters and setters
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
    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", livros=" + livros +
                '}';
    }
    public boolean isPresent() {
        return id != null && nome != null && descricao != null;
    }
    /**
     * Método para obter a categoria, caso esteja presente.
     * @return Categoria se estiver presente, caso contrário lança uma exceção.
     */

    public Categoria get() {
        if (isPresent()) {
            return this;
        } else {
            throw new IllegalStateException("Categoria não encontrada");
        }
    }

    public Categoria orElseThrow(Object categoriaNãoEncontrada) {
        if (isPresent()) {
            return this;
        } else {
            throw new IllegalStateException("Categoria não encontrada");
        }
    }
}
