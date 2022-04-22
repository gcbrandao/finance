package br.com.gcbrandao.finance.adapter.repository;

import br.com.gcbrandao.finance.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}