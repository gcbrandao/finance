package br.com.gcbrandao.finance.adapter.controller;


import br.com.gcbrandao.finance.adapter.dto.LancamentoDTO;
import br.com.gcbrandao.finance.usecase.CadastraLancamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance/lancamento")
@Slf4j
public class CadastraLancamentosController {

    private final CadastraLancamento cadastraLancamento;

    public CadastraLancamentosController(final CadastraLancamento cadastraLancamento) {
        this.cadastraLancamento = cadastraLancamento;
    }


    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<LancamentoDTO> listaLancamentos() {

        log.info("Buscando lancamentos");
        return cadastraLancamento.listaLancamentos();
    }

    @GetMapping("/{lancamentoID}")
    @ResponseStatus(HttpStatus.OK)
    public LancamentoDTO buscaLancamento(@PathVariable final Long lancamentoID) {

        log.info(String.format("Buscando lancamento: %s", lancamentoID));
        return cadastraLancamento.buscaLancamento(lancamentoID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvaLancamento(@RequestBody final LancamentoDTO lancamentoDTO) {

        log.info(String.format("Salvando lancamento: %s", lancamentoDTO.getDescricao()));
        cadastraLancamento.salvaLancamento(lancamentoDTO);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void alteraLancamento(@RequestBody final LancamentoDTO lancamentoDTO) {

        log.info(String.format("Alterando  lancamento: %s", lancamentoDTO.getDescricao()));
        cadastraLancamento.alteraLancamento(lancamentoDTO);

    }

    @DeleteMapping("/{lancamentoID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagaLancamento(@PathVariable final Long lancamentoID) {


        log.info(String.format("Apagando  lancamento: %s", lancamentoID));
        cadastraLancamento.apagaLancamento(lancamentoID);

    }


}
