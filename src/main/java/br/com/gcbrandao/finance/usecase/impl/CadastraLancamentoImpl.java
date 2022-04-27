package br.com.gcbrandao.finance.usecase.impl;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;
import br.com.gcbrandao.finance.adapter.dto.LancamentoConsolidadoDTO;
import br.com.gcbrandao.finance.adapter.dto.LancamentoDTO;
import br.com.gcbrandao.finance.adapter.repository.LancamentoRepository;
import br.com.gcbrandao.finance.domain.entity.Categoria;
import br.com.gcbrandao.finance.domain.entity.Lancamento;
import br.com.gcbrandao.finance.domain.entity.TipoLancamento;
import br.com.gcbrandao.finance.domain.exception.NotFoundException;
import br.com.gcbrandao.finance.usecase.CadastraLancamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CadastraLancamentoImpl implements CadastraLancamento {

    private final LancamentoRepository lancamentoRepository;

    public CadastraLancamentoImpl(final LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Override
    public List<LancamentoDTO> listaLancamentos() {

        final List<Lancamento> lancamentos = lancamentoRepository.findAll();

        final List<LancamentoDTO> lancamentoDTOList = new ArrayList<>();
        for (final Lancamento lancamento : lancamentos) {
            final LancamentoDTO lancamentoDTO = new LancamentoDTO();
            final CategoriaDTO categoriaDTO = new CategoriaDTO();
            BeanUtils.copyProperties(lancamento, lancamentoDTO);
            BeanUtils.copyProperties(lancamento.getCategoria(), categoriaDTO);
            lancamentoDTO.setCategoria(categoriaDTO);

            lancamentoDTOList.add(lancamentoDTO);
        }

        return lancamentoDTOList;
    }

    @Override
    public LancamentoDTO buscaLancamento(final Long lancamentoID) {

        final Lancamento lancamento = lancamentoRepository.findById(lancamentoID).orElseThrow(() -> new NotFoundException(
                String.format("Lançamento nao encontrdo: %s", lancamentoID)));
        final LancamentoDTO lancamentoDTO = new LancamentoDTO();
        final CategoriaDTO categoriaDTO = new CategoriaDTO();

        BeanUtils.copyProperties(lancamento, lancamentoDTO);
        BeanUtils.copyProperties(lancamento.getCategoria(), categoriaDTO);
        lancamentoDTO.setCategoria(categoriaDTO);

        return lancamentoDTO;
    }

    @Override
    public void salvaLancamento(final LancamentoDTO lancamentoDTO) {

        final Lancamento lancamento = new Lancamento();
        final Categoria categoria = new Categoria();

        BeanUtils.copyProperties(lancamentoDTO, lancamento);
        BeanUtils.copyProperties(lancamentoDTO.getCategoria(), categoria);

        lancamento.setCategoria(categoria);

        lancamentoRepository.save(lancamento);
    }

    @Override
    public void apagaLancamento(final Long lancamentoID) {
        final Lancamento lancamento = lancamentoRepository.findById(lancamentoID).orElseThrow(() -> new NotFoundException(
                String.format("Lançamento nao encontrdo: %s", lancamentoID)));

        lancamentoRepository.delete(lancamento);

    }

    @Override
    public void alteraLancamento(final LancamentoDTO lancamentoDTO) {
        final Lancamento lancamento = lancamentoRepository.findById(lancamentoDTO.getCodigo())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Lançamento nao encontrdo: %s", lancamentoDTO.getCodigo())));

        final Categoria categoria = new Categoria();
        BeanUtils.copyProperties(lancamentoDTO, lancamento, "codigo");
        BeanUtils.copyProperties(lancamentoDTO.getCategoria(), categoria);

        lancamento.setCategoria(categoria);

        lancamentoRepository.save(lancamento);

    }

    @Override
    public LancamentoConsolidadoDTO consolidaLancamentos(final LocalDate dataPagamento) {


        final List<Lancamento> lancamentos = lancamentoRepository.findAllByDataPagamento(dataPagamento);

        BigDecimal totalDespesas = new BigDecimal("0.00");
        BigDecimal totalReceitas = new BigDecimal("0.00");
        BigDecimal saldo = new BigDecimal("0.00");


        for (final Lancamento lancamento : lancamentos) {
            if (TipoLancamento.DESPESA.equals(lancamento.getTipo())) {
                totalDespesas = totalDespesas.add(lancamento.getValor());
                saldo = saldo.subtract(lancamento.getValor());
            } else if (TipoLancamento.RECEITA.equals(lancamento.getTipo())) {
                totalReceitas = totalReceitas.add(lancamento.getValor());
                saldo = saldo.add(lancamento.getValor());
            }
        }


        return LancamentoConsolidadoDTO.builder()
                .dataConsolidacao(dataPagamento)
                .totalDespesas(totalDespesas)
                .totalReceita(totalReceitas)
                .saldo(saldo)
                .build();
    }
}
