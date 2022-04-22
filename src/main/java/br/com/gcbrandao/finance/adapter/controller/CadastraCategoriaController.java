package br.com.gcbrandao.finance.adapter.controller;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;
import br.com.gcbrandao.finance.usecase.CadastraCategoria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

        log.info(categoriaDTO.toString());
        cadastraCategoria.adicionaCategoria(categoriaDTO);
    }


}
