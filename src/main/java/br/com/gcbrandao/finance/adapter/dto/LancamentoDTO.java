package br.com.gcbrandao.finance.adapter.dto;

import br.com.gcbrandao.finance.domain.entity.TipoLancamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LancamentoDTO {

    private Long codigo;
    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String observacao;
    private TipoLancamento tipo;
    private CategoriaDTO categoria;

}
