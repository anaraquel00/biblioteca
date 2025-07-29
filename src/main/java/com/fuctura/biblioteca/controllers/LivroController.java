package com.fuctura.biblioteca.controllers;

import com.fuctura.biblioteca.dtos.LivroDto;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;
@RequestMapping("/livros")
@Controller
public class LivroController {

    // This class can be used to handle requests related to "livros" (books).
    // Currently, it does not contain any methods, but you can add methods
    // to handle specific HTTP requests (GET, POST, etc.) for book-related operations.

    @Autowired
    private LivroService categoriaService;

    @Autowired
    private ModelMapper modelMapper;
    LivroService livroService;
    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable Integer id) {

        Livro livro = livroService.findById(id);
        LivroDto livroDto = modelMapper.map(livro, LivroDto.class);
        return ok().body(new LivroDto(livroDto));
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> findAll() {
        List<Livro> list = livroService.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> modelMapper.map(obj, LivroDto.class)).collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> findByNome() {
        List<Livro> list = livroService.findAll();
        List<LivroDto> listDto = list.stream()
                .map(livro -> modelMapper.map(livro, LivroDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }

    @PostMapping
    public ResponseEntity <LivroDto> save(@Valid @RequestBody LivroDto LivroDto) {

        Livro livro = null;
        Categoria categoria = modelMapper.map(livro, Livro.class).getCategoria();
        livro = LivroService.save(livro);
        LivroDto livroDto = modelMapper.map(livro, LivroDto.class);
        return ResponseEntity.ok().body(livroDto);

    }

    @PutMapping("/{id}")
    public Livro update(@Valid @PathVariable Integer id, @RequestBody Livro livro) {
        livro.setId(id);
        return livroService.update(livro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        livroService.delete(id);
    }

}




