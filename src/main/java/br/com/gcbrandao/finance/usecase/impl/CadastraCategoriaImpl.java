package br.com.gcbrandao.finance.usecase.impl;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;
import br.com.gcbrandao.finance.adapter.repository.CategoriaRepository;
import br.com.gcbrandao.finance.domain.entity.Categoria;
import br.com.gcbrandao.finance.domain.exception.NotFoundException;
import br.com.gcbrandao.finance.usecase.CadastraCategoria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CadastraCategoriaImpl implements CadastraCategoria {

    private final CategoriaRepository categoriaRepository;

    public CadastraCategoriaImpl(final CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void adicionaCategoria(final CategoriaDTO categoriaDTO) {

        final Categoria categoria = new Categoria();

        BeanUtils.copyProperties(categoriaDTO, categoria, "id");
        log.info(categoria.toString());

        categoriaRepository.save(categoria);

    }

    @Override
    public CategoriaDTO atualizaCategoria(final CategoriaDTO categoriaDTO) {

        final Categoria categoria = categoriaRepository.findById(categoriaDTO.getId()).orElseThrow(() -> new NotFoundException(
                String.format("Categoria nao encontrda: %s", categoriaDTO.getNome())));

        log.info(String.format("Categoria nao encontrda: %s", categoriaDTO.getNome()));


        return null;
    }

    @Override
    public void apagaCategoria(final CategoriaDTO categoriaDTO) {

    }

    @Override
    public CategoriaDTO buscaCategoria(final CategoriaDTO categoriaDTO) {
        return null;
    }

    @Override
    public List<CategoriaDTO> listaCategorias() {
        return null;
    }
}
