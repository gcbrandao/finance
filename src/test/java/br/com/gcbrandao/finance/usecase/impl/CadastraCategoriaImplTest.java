package br.com.gcbrandao.finance.usecase.impl;

import br.com.gcbrandao.finance.adapter.dto.CategoriaDTO;
import br.com.gcbrandao.finance.adapter.repository.CategoriaRepository;
import br.com.gcbrandao.finance.domain.entity.Categoria;
import br.com.gcbrandao.finance.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Cadastra categoria Test UseCase")
class CadastraCategoriaImplTest {

    @Mock
    CategoriaRepository categoriaRepository;

    @Mock
    CategoriaDTO categoriaDTO;

    @InjectMocks
    private CadastraCategoriaImpl cadastraCategoria;


    Categoria categoria1;
    List<Categoria> categorias;

    @BeforeEach
    public void setUp() {


        categorias = new ArrayList<>();

        categoria1 = Categoria.builder()
                .codigo(1L)
                .nome("Teste-1")
                .build();

        final Categoria categoria2 = Categoria.builder()
                .codigo(2L)
                .nome("Teste-2")
                .build();

        final Categoria categoria3 = Categoria.builder()
                .codigo(3L)
                .nome("Teste-3")
                .build();

        final Categoria categoria4 = Categoria.builder()
                .codigo(4L)
                .nome("Teste-4")
                .build();

        final Categoria categoria5 = Categoria.builder()
                .codigo(5L)
                .nome("Teste-5")
                .build();

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);

    }

    @Test
    @DisplayName("Testa Adiciona Categoria")
    void adicionaCategoria() {

        // Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(categoria1));

        cadastraCategoria.adicionaCategoria(categoriaDTO);

    }

    @Test
    @DisplayName("Testa Atualiza categoria")
    void atualizaCategoria() {

        Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(categoria1));

        cadastraCategoria.atualizaCategoria(categoriaDTO);


    }

    @Test
    @DisplayName("Testa apagar categoria")
    void apagaCategoria() {

        Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(categoria1));

        cadastraCategoria.apagaCategoria(Mockito.anyLong());

    }

    @Test
    @DisplayName("Testa Busca de Categoria")
    void buscaCategoria() {
        Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(categoria1));

        final CategoriaDTO categoriaDTO = cadastraCategoria.buscaCategoria(Mockito.anyLong());

        assertEquals(categoriaDTO.getCodigo(), 1L);
        assertEquals(categoriaDTO.getNome(), "Teste-1");
    }

    @Test
    @DisplayName("Testa busca Categoria Not Found")
    void busacaCategoriaNotFoundTest() {

        final Exception exception = assertThrows(NotFoundException.class, () -> {

            Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenThrow(new NotFoundException("Categoria nao encontrda: 1"));

            final CategoriaDTO categoriaDTO = cadastraCategoria.buscaCategoria(Mockito.anyLong());
        });

        final String expectedMessage = "Categoria nao encontrda: 1";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    @DisplayName("Testa Lista  Categoria")
    void listaCategorias() {

        Mockito.when(categoriaRepository.findAll()).thenReturn(categorias);
        final List<CategoriaDTO> categoriaDTOS = cadastraCategoria.listaCategorias();

        assertEquals(categoriaDTOS.size(), 5);

    }
}