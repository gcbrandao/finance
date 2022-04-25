package br.com.gcbrandao.finance.adapter.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoriaDTO {

    private Long codigo;
    private String nome;
}
