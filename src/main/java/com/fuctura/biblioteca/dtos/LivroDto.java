package com.fuctura.biblioteca.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class LivroDto {
    private Integer id;

   @NotNull(message = "O título do livro não pode ser nulo")
   @Length(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

   @NotNull(message = "O autor do livro não pode ser nulo")
   @Length(min = 3, max = 50, message = "O autor deve ter entre 3 e 50 caracteres")
   private String autor;

    @NotNull(message = "A editora do livro não pode ser nula")
    @Length(min = 3, max = 50, message = "A editora deve ter entre 3 e 50 caracteres")
    private String editora;

    @NotNull(message = "O ano de publicação do livro não pode ser nulo")
    @Length(min = 4, max = 4, message = "O ano de publicação deve ter 4 dígitos")
    private Integer anoPublicacao;


    public LivroDto(Integer id, String titulo, String autor, String editora, Integer anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;

    }
    public LivroDto() {}

    public LivroDto(LivroDto livroDto) {
    }
    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setId(Integer id) { this.id = id;    }
   }


