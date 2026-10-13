package com.marcos.imobflow.adapters.out.repository;

import com.marcos.imobflow.domain.model.Imovel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MySqlImovelRepositoryTest {

    private JpaImovelRepository jpaImovelRepository;
    private MySqlImovelRepository repository;

    @BeforeEach
    void setUp() {
        jpaImovelRepository = mock(JpaImovelRepository.class);
        repository = new MySqlImovelRepository(jpaImovelRepository);
    }

    @Test
    void deveSalvarConvertendoEntidadeERetornandoDominioComIdGerado() {
        Imovel imovelSemId = criarImovel(null, "Apartamento novo");
        ImovelEntity entitySalva = criarEntity(10L, "Apartamento novo");
        when(jpaImovelRepository.save(any(ImovelEntity.class))).thenReturn(entitySalva);

        Imovel salvo = repository.salvar(imovelSemId);

        ArgumentCaptor<ImovelEntity> captor = ArgumentCaptor.forClass(ImovelEntity.class);
        verify(jpaImovelRepository).save(captor.capture());
        assertNull(captor.getValue().getId());
        assertEquals(10L, salvo.getId());
        assertEquals("Apartamento novo", salvo.getTitulo());
    }

    @Test
    void deveListarConvertendoTodasAsEntidadesParaDominio() {
        when(jpaImovelRepository.findAll()).thenReturn(List.of(
                criarEntity(1L, "Apartamento 1"),
                criarEntity(2L, "Apartamento 2")
        ));

        List<Imovel> resultado = repository.listar();

        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertEquals(2L, resultado.get(1).getId());
    }

    @Test
    void deveListarPorFiltrosMantendoFiltrosCombinados() {
        when(jpaImovelRepository.findAll(ArgumentMatchers.<Specification<ImovelEntity>>any()))
                .thenReturn(List.of(criarEntity(1L, "Apartamento filtrado")));

        List<Imovel> resultado = repository.listarPorFiltros(
                300000.0,
                500000.0,
                "São Bernardo do Campo"
        );

        verify(jpaImovelRepository).findAll(ArgumentMatchers.<Specification<ImovelEntity>>any());
        assertEquals(1, resultado.size());
        assertEquals("Apartamento filtrado", resultado.getFirst().getTitulo());
    }

    @Test
    void deveBuscarPorIdConvertendoEntidadeParaDominio() {
        when(jpaImovelRepository.findById(42L)).thenReturn(Optional.of(criarEntity(42L, "Apartamento")));

        Imovel encontrado = repository.buscarPorId(42L);

        assertEquals(42L, encontrado.getId());
        assertEquals("Apartamento", encontrado.getTitulo());
    }

    @Test
    void deveRetornarNullQuandoIdNaoExistir() {
        when(jpaImovelRepository.findById(99L)).thenReturn(Optional.empty());

        assertNull(repository.buscarPorId(99L));
    }

    @Test
    void deveDelegarExclusaoPorId() {
        repository.deletarPorId(42L);

        verify(jpaImovelRepository).deleteById(42L);
    }

    @Test
    void deveAtualizarPreservandoIdEMapeandoRetorno() {
        Imovel imovel = criarImovel(42L, "Apartamento atualizado");
        when(jpaImovelRepository.save(any(ImovelEntity.class)))
                .thenReturn(criarEntity(42L, "Apartamento atualizado"));

        Imovel atualizado = repository.atualizar(imovel);

        ArgumentCaptor<ImovelEntity> captor = ArgumentCaptor.forClass(ImovelEntity.class);
        verify(jpaImovelRepository).save(captor.capture());
        assertEquals(42L, captor.getValue().getId());
        assertEquals(42L, atualizado.getId());
        assertEquals("Apartamento atualizado", atualizado.getTitulo());
    }

    private Imovel criarImovel(Long id, String titulo) {
        return new Imovel(
                id,
                titulo,
                "Descrição",
                "São Bernardo do Campo",
                "Centro",
                "Rua das Flores, 100",
                450000.0,
                "VENDA",
                "APARTAMENTO",
                2,
                1,
                1,
                65.5
        );
    }

    private ImovelEntity criarEntity(Long id, String titulo) {
        return ImovelEntityMapper.toEntity(criarImovel(id, titulo));
    }
}
