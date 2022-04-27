package br.com.gcbrandao.finance.usecase.impl;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;
import br.com.gcbrandao.finance.adapter.dto.LancamentoConsolidadoDTO;
import br.com.gcbrandao.finance.adapter.dto.LancamentoDTO;
import br.com.gcbrandao.finance.adapter.repository.LancamentoRepository;
import br.com.gcbrandao.finance.domain.entity.Categoria;
import br.com.gcbrandao.finance.domain.entity.Lancamento;
import br.com.gcbrandao.finance.domain.entity.TipoLancamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("Cadastra lancamentos Test UseCase")
class CadastraLancamentoImplTest {


    @Mock
    LancamentoRepository lancamentoRepository;

    @Mock
    LancamentoDTO lancamentoDTO;

    @InjectMocks
    private CadastraLancamentoImpl cadastraLancamento;

    Lancamento lancamento1;
    List<Lancamento> lancamentos;
    LocalDate dataPagamento;
    LocalDate dataVencimento;


    @BeforeEach
    void setUp() {
        lancamentos = new ArrayList<>();
        dataPagamento = LocalDate.parse("2022-04-26");
        dataVencimento = LocalDate.parse("2022-04-26");

        lancamento1 = Lancamento.builder()
                .categoria(Categoria.builder().nome("Teste").codigo(1L).build())
                .codigo(1L)
                .dataPagamento(dataPagamento)
                .dataVencimento(dataVencimento)
                .descricao("Descrição Teste")
                .tipo(TipoLancamento.DESPESA)
                .valor(new BigDecimal("200.00"))
                .build();

        final Lancamento lancamento2 = Lancamento.builder()
                .categoria(Categoria.builder().nome("Teste2").codigo(2L).build())
                .codigo(2L)
                .dataPagamento(dataPagamento)
                .dataVencimento(dataVencimento)
                .descricao("Descrição Teste2")
                .tipo(TipoLancamento.DESPESA)
                .valor(new BigDecimal("220.00"))
                .build();

        final Lancamento lancamento3 = Lancamento.builder()
                .categoria(Categoria.builder().nome("Teste3").codigo(3L).build())
                .codigo(3L)
                .dataPagamento(dataPagamento)
                .dataVencimento(dataVencimento)
                .descricao("Descrição Teste3")
                .tipo(TipoLancamento.RECEITA)
                .valor(new BigDecimal("400.00"))
                .build();

        final Lancamento lancamento4 = Lancamento.builder()
                .categoria(Categoria.builder().nome("Teste4").codigo(4L).build())
                .codigo(4L)
                .dataPagamento(dataPagamento)
                .dataVencimento(dataVencimento)
                .descricao("Descrição Teste4")
                .tipo(TipoLancamento.RECEITA)
                .valor(new BigDecimal("440.00"))
                .build();


        lancamentos.add(lancamento1);
        lancamentos.add(lancamento2);
        lancamentos.add(lancamento3);
        lancamentos.add(lancamento4);

    }

    @Test
    @DisplayName("Testa lista Lancamentos")
    void listaLancamentos() {
        Mockito.when(lancamentoRepository.findAll()).thenReturn(lancamentos);
        final List<LancamentoDTO> lancamentoDTOS = cadastraLancamento.listaLancamentos();

        assertEquals(lancamentoDTOS.size(), 4);
    }

    @Test
    void buscaLancamento() {
        Mockito.when(lancamentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(lancamento1));

        final LancamentoDTO lancamentoDTOreturn = cadastraLancamento.buscaLancamento(Mockito.anyLong());

        assertEquals(lancamentoDTOreturn.getCodigo(), 1L);
        assertEquals(lancamentoDTOreturn.getValor(), new BigDecimal("200.00"));
    }

    @Test
    void salvaLancamento() {

        final LancamentoDTO dto = LancamentoDTO.builder()
                .categoria(CategoriaDTO.builder().codigo(1L).nome("Categoria Teste").build())
                .build();

        cadastraLancamento.salvaLancamento(dto);

    }

    @Test
    void apagaLancamento() {

        Mockito.when(lancamentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(lancamento1));

        cadastraLancamento.apagaLancamento(Mockito.anyLong());
    }

    @Test
    void alteraLancamento() {

        final LancamentoDTO dto = LancamentoDTO.builder()
                .categoria(CategoriaDTO.builder().codigo(1L).nome("Categoria Teste").build())
                .codigo(1L)
                .build();

        Mockito.when(lancamentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(lancamento1));

        cadastraLancamento.alteraLancamento(dto);
    }

    @Test
    void consolidaLancamentos() {

        Mockito.when(lancamentoRepository.findAllByDataPagamento(dataPagamento)).thenReturn(lancamentos);

        final LancamentoConsolidadoDTO lancamentoConsolidadoDTO =
                cadastraLancamento.consolidaLancamentos(dataPagamento);

        assertEquals(lancamentoConsolidadoDTO.getTotalDespesas(), new BigDecimal("420.00"));
        assertEquals(lancamentoConsolidadoDTO.getTotalReceita(), new BigDecimal("840.00"));
        assertEquals(lancamentoConsolidadoDTO.getSaldo(), new BigDecimal("420.00"));

    }
}