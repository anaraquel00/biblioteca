package com.fuctura.biblioteca.controllers;

import com.fuctura.biblioteca.dtos.LivroDto;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/livro")  // Altere o endpoint
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<LivroDto>> findAll() {

        List<Livro> list = livroService.findAll();
        return ResponseEntity.ok().body(list.stream()
                .map(obj -> modelMapper.map(obj, LivroDto.class))
                .collect(Collectors.toList()));
    }
    //localhost:8080/livro/1
    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable Integer id) {
        Livro livro = livroService.findById(id);
        LivroDto livroDto = modelMapper.map(livro, LivroDto.class);
        return ResponseEntity.ok().body(livroDto);
    }
    //localhost:8080/livro?categoria=1
    //@GetMapping
    //public ResponseEntity<List<LivroDto>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
        //List<Livro> list = livroService.findAll(id_cat);
        //return ResponseEntity.ok().body(list.stream().map(obj -> modelMapper.map(obj, LivroDto.class))
                //.collect(Collectors.toList()));
   // }

    @PostMapping
    public ResponseEntity<LivroDto> criarLivro(
            @RequestParam Integer categoria,  // Obrigatório (removi defaultValue)
            @Valid @RequestBody LivroDto livroDto) {

        // Converte DTO para Entidade
        Livro livro = new Livro();
        livro.setTitulo(livroDto.getTitulo());
        livro.setAutor(livroDto.getAutor());
        livro.setTexto(livroDto.getTexto());
        livro.setTamanho(livroDto.getTamanho());

        // Salva com a categoria
        Livro livroSalvo = livroService.save(categoria, livro);

        // Converte de volta para DTO
        LivroDto responseDto = new LivroDto();
        responseDto.setTitulo(livroSalvo.getTitulo());
        responseDto.setAutor(livroSalvo.getAutor());
        responseDto.setTexto(livroSalvo.getTexto());
        responseDto.setTamanho(livroSalvo.getTamanho());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
  }


