package br.com.gcbrandao.finance.adapter.controller;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;
import br.com.gcbrandao.finance.usecase.CadastraCategoria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance/categoria")
@Slf4j
public class CadastraCategoriaController {

    private final CadastraCategoria cadastraCategoria;

    public CadastraCategoriaController(final CadastraCategoria cadastraCategoria) {
        this.cadastraCategoria = cadastraCategoria;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionaCategoria(@RequestBody final CategoriaDTO categoriaDTO) {

        log.info(String.format("Adicionando a Categoria: %s", categoriaDTO.getNome()));
        cadastraCategoria.adicionaCategoria(categoriaDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void alteraCategoria(@RequestBody final CategoriaDTO categoriaDTO) {

        log.info(String.format("Alterando a Categoria: %s", categoriaDTO.getNome()));
        cadastraCategoria.atualizaCategoria(categoriaDTO);


    }

    @DeleteMapping("/{categoriaID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagaCategoria(@PathVariable final Long categoriaID) {

        log.info(String.format("Apagando a Categoria: %s", categoriaID));
        cadastraCategoria.apagaCategoria(categoriaID);
    }


    @GetMapping("/{categoriaID}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO buscaCategoria(@PathVariable final Long categoriaID) {

        log.info(String.format("Buscando a Categoria: %s", categoriaID));
        return cadastraCategoria.buscaCategoria(categoriaID);

    }

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaDTO> listarCategorias() {

        log.info("Listando todas as categorias");
        return cadastraCategoria.listaCategorias();

    }

}
