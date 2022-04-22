package br.com.gcbrandao.finance.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Builder
@Entity
@Table(name = "categoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;


    @Column(nullable = false)
    @Size(min = 3, max = 20)
    String nome;

}
