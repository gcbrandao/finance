package br.com.gcbrandao.finance.usecase;

import br.com.gcbrandao.finance.adapter.dto.LancamentoConsolidadoDTO;
import br.com.gcbrandao.finance.adapter.dto.LancamentoDTO;

import java.time.LocalDate;
import java.util.List;

public interface CadastraLancamento {

    List<LancamentoDTO> listaLancamentos();

    LancamentoDTO buscaLancamento(Long lancamentoID);

    void salvaLancamento(LancamentoDTO lancamentoDTO);

    void apagaLancamento(Long lancamentoID);

    void alteraLancamento(LancamentoDTO lancamentoDTO);


    LancamentoConsolidadoDTO consolidaLancamentos(LocalDate dataPagamento);


}
