package br.com.gcbrandao.finance.adapter.repository;

import br.com.gcbrandao.finance.domain.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}