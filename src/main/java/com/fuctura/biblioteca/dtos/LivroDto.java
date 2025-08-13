package com.fuctura.biblioteca.dtos;

import com.fuctura.biblioteca.enuns.Tamanho;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.List;

public class LivroDto {

    private Integer id;
    private String titulo;
    private String autor;
    private String texto;
    private Categoria categoria;
    private Tamanho tamanho;

    public LivroDto() {}

    public LivroDto(Integer id, String titulo, String autor, String texto, Categoria categoria, Tamanho tamanho) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.texto = texto;
        this.categoria = categoria;
        this.tamanho = tamanho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
}


