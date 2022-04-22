package br.com.gcbrandao.finance.usecase;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;

import java.util.List;

public interface CadastraCategoria {

    void adicionaCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO atualizaCategoria(CategoriaDTO categoriaDTO);

    void apagaCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO buscaCategoria(CategoriaDTO categoriaDTO);

    List<CategoriaDTO> listaCategorias();


}
