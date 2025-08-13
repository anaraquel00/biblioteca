package com.fuctura.biblioteca.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.fuctura.biblioteca.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.web.bind.annotation.*;

import com.fuctura.biblioteca.dtos.CategoriaDto;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.services.CategoriaService;
// @Getmapping("/categoria/{id}") = http://localhost:8080/categoria/{id}
// @PathVariable é usado para extrair o valor do id da URL
// @GetMapping = é uma anotação que mapeia solicitações HTTP GET para métodos específicos em um controlador.
// @PostMapping = é uma anotação que mapeia solicitações HTTP POST para métodos específicos em um controlador.
// @PutMapping = é uma anotação que mapeia solicitações HTTP PUT para métodos específicos em um controlador.
// @DeleteMapping = é uma anotação que mapeia solicitações HTTP DELETE para métodos específicos em um controlador.
// @RequestMapping("/categoria") = é uma anotação que mapeia solicitações HTTP para métodos específicos em um controlador.
// @RestController = é uma anotação que indica que a classe é um controlador REST, ou seja, ela lida com solicitações HTTP e retorna respostas no formato JSON ou XML.
// @Autowired = é uma anotação que indica que o Spring deve injetar uma instância da classe CategoriaService no controlador.

@RestController
@RequestMapping("/categorias")
//@CrossOrigin("*") // Permite requisições de diferentes origens (CORS) (FrontEnd)
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        //CategoriaDto categoriaDto = modelMapper.map(categoria, CategoriaDto.class;
        return ResponseEntity.ok().body(modelMapper.map(categoria, CategoriaDto.class));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<CategoriaDto> findByNome(@PathVariable String nome) {
        Categoria categoria = categoriaService.findByNome(nome);
        return ResponseEntity.ok().body(modelMapper.map(categoria, CategoriaDto.class));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> modelMapper.map(obj, CategoriaDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@Valid @RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = modelMapper.map(categoriaDto, Categoria.class);
        Categoria cat = categoriaService.save(categoria);
        CategoriaDto catDto = modelMapper.map(cat, CategoriaDto.class);
        return ResponseEntity.ok().body(catDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Integer id,@Valid @RequestBody CategoriaDto categoriaDto) {
        categoriaDto.setId(id);
        Categoria categoria = modelMapper.map(categoriaDto, Categoria.class);
        Categoria cat = categoriaService.update(categoria);
        CategoriaDto catDto = modelMapper.map(cat, CategoriaDto.class);
        return ResponseEntity.ok().body(catDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
