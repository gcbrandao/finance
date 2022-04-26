package br.com.gcbrandao.finance.adapter.controller;


import br.com.gcbrandao.finance.adapter.dto.LancamentoConsolidadoDTO;
import br.com.gcbrandao.finance.usecase.CadastraLancamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/finance/lancamento/consolidado")
@Slf4j
public class ConsolidaLancamentosController {

    private final CadastraLancamento cadastraLancamento;

    public ConsolidaLancamentosController(final CadastraLancamento cadastraLancamento) {
        this.cadastraLancamento = cadastraLancamento;
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public LancamentoConsolidadoDTO consolidado(final String dataConsolidacao) {

        log.info("Buscando lancamentos consolidados");

        final LocalDate dataCons = LocalDate.parse(dataConsolidacao);

        return cadastraLancamento.consolidaLancamentos(dataCons);
    }


}
