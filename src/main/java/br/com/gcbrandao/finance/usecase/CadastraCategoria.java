package br.com.gcbrandao.finance.usecase;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;

import java.util.List;

public interface CadastraCategoria {

    void adicionaCategoria(CategoriaDTO categoriaDTO);

    void atualizaCategoria(CategoriaDTO categoriaDTO);

    void apagaCategoria(Long categoriaID);

    CategoriaDTO buscaCategoria(Long categoriaID);

    List<CategoriaDTO> listaCategorias();


}
