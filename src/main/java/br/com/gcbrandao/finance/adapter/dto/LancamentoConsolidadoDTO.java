package br.com.gcbrandao.finance.adapter.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LancamentoConsolidadoDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataConsolidacao;

    private BigDecimal totalReceita;
    private BigDecimal totalDespesas;
    private BigDecimal saldo;


}
