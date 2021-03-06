package br.com.gcbrandao.finance.adapter.repository;

import br.com.gcbrandao.finance.domain.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {


    // @Query("from lancameto l ")
    List<Lancamento> findAllByDataPagamento(LocalDate dataPagamento);
}