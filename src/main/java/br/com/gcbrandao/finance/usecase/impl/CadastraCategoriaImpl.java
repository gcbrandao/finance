package br.com.gcbrandao.finance.usecase.impl;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;
import br.com.gcbrandao.finance.adapter.repository.CategoriaRepository;
import br.com.gcbrandao.finance.domain.entity.Categoria;
import br.com.gcbrandao.finance.domain.exception.NotFoundException;
import br.com.gcbrandao.finance.usecase.CadastraCategoria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        final Categoria categoria = categoriaRepository.findById(categoriaDTO.getCodigo()).orElseThrow(() -> new NotFoundException(
                String.format("Categoria nao encontrda: %s", categoriaDTO.getNome())));

        log.info(String.format("Atulizando a categoria: %s", categoriaDTO.getNome()));

        BeanUtils.copyProperties(categoriaDTO, categoria);
        log.info(categoriaDTO.toString());

        categoriaRepository.save(categoria);
        return categoriaDTO;
    }

    @Override
    public void apagaCategoria(final Long categoriaID) {
        final Categoria categoria = categoriaRepository.findById(categoriaID).orElseThrow(() -> new NotFoundException(
                String.format("Categoria nao encontrda: %s", categoriaID)));

        log.info(String.format("Apagando a categoria: %s", categoriaID));

        categoriaRepository.delete(categoria);
    }

    @Override
    public CategoriaDTO buscaCategoria(final Long categoriaID) {
        final Categoria categoria = categoriaRepository.findById(categoriaID).orElseThrow(() -> new NotFoundException(
                String.format("Categoria nao encontrda: %s", categoriaID)));
        final CategoriaDTO categoriaDTO = new CategoriaDTO();
        BeanUtils.copyProperties(categoria, categoriaDTO);

        return categoriaDTO;

    }

    @Override
    public List<CategoriaDTO> listaCategorias() {
        final List<Categoria> categorias = categoriaRepository.findAll();

        final List<CategoriaDTO> categList = new ArrayList<>();

        for (final Categoria categoria : categorias) {
            final CategoriaDTO categoriaDTO = new CategoriaDTO();
            BeanUtils.copyProperties(categoria, categoriaDTO);
            categList.add(categoriaDTO);

        }
        return categList;
    }
}
