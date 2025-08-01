package com.fuctura.biblioteca.controllers;

import com.fuctura.biblioteca.dtos.LivroDto;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/livros")  // Altere o endpoint
public class LivroController {

    @Autowired
    private LivroService livroService;  // Injete o LivroService
    private LivroController categoriaService;

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> list = livroService.findAll();
        if (list.isEmpty()) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Nenhum livro encontrado");
        }
        return ok().body(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id) {
        Livro obj = livroService.findById(id);
        return ok().body(obj);
    }

    @GetMapping("/listar-livros")
    public ResponseEntity<Page<Livro>> listarCategorias(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int itensPorPagina,
            @RequestParam(defaultValue = "nome") String ordenarPor) {

        Page<Livro> livro;
        if (nome != null) {
            livro = livroService.buscarPorNome(nome, pagina, itensPorPagina, ordenarPor);
        } else {
            livro = livroService.listarTodas(pagina, itensPorPagina, ordenarPor);
        }
        return ok(livro);
    }

    @PostMapping
    public ResponseEntity<?> criarLivro(
            @RequestParam Integer categoriaId,
            @Valid @RequestBody LivroDto livroDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Livro livro = livroService.criarLivroComCategoria(livroDto, categoriaId);

            return ResponseEntity
                    .created(ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(livro.getId())
                            .toUri())
                    .body(new LivroDto(livro));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro livro) {
        livro.setId(id);
        Livro updatedLivro = livroService.update(livro);
        return ok().body(updatedLivro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
  }


