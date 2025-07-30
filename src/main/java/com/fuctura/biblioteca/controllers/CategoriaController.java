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

public class CategoriaController {

     @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/{id}")
    public ResponseEntity <CategoriaDto> findById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        CategoriaDto catDto = new CategoriaDto(categoria);
        return ok().body(new CategoriaDto(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        return ok().body(list.stream().map(obj -> modelMapper.map(obj, CategoriaDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/categoria")
    public List<Categoria> filtrarPorNome(@RequestParam(required = false) String nome) {

        CategoriaRepository categoriaRepository = null;
        if (nome != null) {
            return categoriaRepository.findByNomeContaining(nome);
        }
        return categoriaRepository.findAll();
    }

    @GetMapping("/buscar-por-nome")
    public ResponseEntity<List<CategoriaDto>> findByNome() {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDto> listDto = list.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaDto.class))
                .collect(Collectors.toList());
        return ok().body(listDto);

    }

    @PostMapping
    public ResponseEntity <CategoriaDto> save(@Valid @RequestBody CategoriaDto categoriaDto) {

        Categoria categoria = modelMapper.map(categoriaDto, Categoria.class);
        Categoria cat = categoriaService.save(categoria);
        CategoriaDto catDto = modelMapper.map(cat, CategoriaDto.class);
        return ok().body(catDto);

    }

    @PutMapping("/{id}")
    public Categoria update(@Valid @PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        return categoriaService.update(categoria);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoriaService.delete(id);
    }

    @GetMapping("/listar-categorias")
    public ResponseEntity<Page<Categoria>> listarCategorias(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int itensPorPagina,
            @RequestParam(defaultValue = "nome") String ordenarPor) {

        Page<Categoria> categoria;
        if (nome != null) {
            categoria = categoriaService.buscarPorNome(nome, pagina, itensPorPagina, ordenarPor);
        } else {
            categoria = categoriaService.listarTodas(pagina, itensPorPagina, ordenarPor);
        }
        return ok(categoria);
    }

}
