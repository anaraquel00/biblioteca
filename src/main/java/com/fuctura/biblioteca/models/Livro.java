package com.fuctura.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fuctura.biblioteca.enuns.Tamanho;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String autor;
    private String texto;
    private String editora;
    private Integer anoPublicacao;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull(message = "Categoria é obrigatória")
    private Categoria categoria;
    private Tamanho tamanho;

    public Livro() {}

    public Livro(Integer id, String titulo, String autor, String texto, Tamanho tamanho, Categoria categoria, String editora, Integer anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.texto = texto;
        this.tamanho = tamanho;
        this.categoria = categoria;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;

    }

    public Livro(Object o, String cleanCode, String robertinMartin, String loremIpsum, Tamanho tamanho, Categoria cat1) {
        this.titulo = cleanCode;
        this.autor = robertinMartin;
        this.texto = loremIpsum;
        this.tamanho = tamanho;
        this.categoria = cat1;
    }

    // Getters e setters para todos os campos

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Tamanho getTamanho() { return tamanho; }
    public void setTamanho(Tamanho tamanho) { this.tamanho = tamanho; }

    public String getEditora() {
        return editora;
    }
     public void setEditora(String editora) {
        this.editora = editora;
    }
     public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

     public void setAnoPublicacao(Integer anoPublicacao) {
    }
}