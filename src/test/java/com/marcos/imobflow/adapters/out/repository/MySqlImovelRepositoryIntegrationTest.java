package com.marcos.imobflow.adapters.out.repository;

import com.marcos.imobflow.domain.model.Imovel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@Import(MySqlImovelRepository.class)
class MySqlImovelRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer("mysql:8.0");

    @Autowired
    private MySqlImovelRepository mySqlImovelRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("Deve preservar os 13 atributos ao salvar e buscar imóvel no MySQL")
    void deveSalvarEBuscarImovelPreservandoTodosOsAtributos() {
        Imovel salvo = mySqlImovelRepository.salvar(criarImovelCompleto(null));

        assertNotNull(salvo.getId(), "O ID deve ser gerado pelo banco");

        Imovel encontrado = mySqlImovelRepository.buscarPorId(salvo.getId());

        assertImovelCompleto(encontrado, salvo.getId());
    }

    @Test
    @DisplayName("Deve atualizar imóvel preservando o ID sem criar outro registro")
    void deveAtualizarImovelPreservandoIdSemCriarRegistroAdicional() {
        Imovel salvo = mySqlImovelRepository.salvar(
                criarImovelPadrao("Apartamento Antigo", "São Bernardo do Campo", 400000.0)
        );
        int quantidadeAntes = mySqlImovelRepository.listar().size();

        Imovel atualizadoParaSalvar = new Imovel(
                salvo.getId(),
                "Apartamento Reformado",
                "Descrição atualizada",
                "São Bernardo do Campo",
                "Centro",
                "Rua Nova, 200",
                520000.0,
                "VENDA",
                "APARTAMENTO",
                3,
                2,
                2,
                80.0
        );

        Imovel retorno = mySqlImovelRepository.atualizar(atualizadoParaSalvar);
        List<Imovel> registros = mySqlImovelRepository.listar();

        assertAll(
                () -> assertEquals(salvo.getId(), retorno.getId()),
                () -> assertEquals(quantidadeAntes, registros.size()),
                () -> assertEquals("Apartamento Reformado", retorno.getTitulo()),
                () -> assertEquals(520000.0, retorno.getValor()),
                () -> assertEquals(3, retorno.getQuartos())
        );
    }

    @Test
    @DisplayName("Deve deletar imóvel por ID")
    void deveDeletarImovelPorId() {
        Imovel salvo = mySqlImovelRepository.salvar(
                criarImovelPadrao("Casa para Deletar", "Santo André", 300000.0)
        );

        mySqlImovelRepository.deletarPorId(salvo.getId());

        assertNull(mySqlImovelRepository.buscarPorId(salvo.getId()));
    }

    @Test
    @DisplayName("Deve listar todos os imóveis salvos")
    void deveListarTodosOsImoveis() {
        mySqlImovelRepository.salvar(criarImovelPadrao("Apartamento 1", "São Bernardo do Campo", 300000.0));
        mySqlImovelRepository.salvar(criarImovelPadrao("Casa 2", "Santo André", 600000.0));

        List<Imovel> lista = mySqlImovelRepository.listar();

        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Deve preservar filtros individuais, combinação AND e limites inclusivos")
    void deveFiltrarComMesmaSemanticaELimitesInclusivos() {
        mySqlImovelRepository.salvar(criarImovelPadrao("Limite mínimo SBC", "São Bernardo do Campo", 300000.0));
        mySqlImovelRepository.salvar(criarImovelPadrao("Dentro da faixa SBC", "São Bernardo do Campo", 350000.0));
        mySqlImovelRepository.salvar(criarImovelPadrao("Limite máximo SBC", "São Bernardo do Campo", 400000.0));
        mySqlImovelRepository.salvar(criarImovelPadrao("Fora da faixa SBC", "São Bernardo do Campo", 500000.0));
        mySqlImovelRepository.salvar(criarImovelPadrao("Mesma faixa outra cidade", "Santo André", 350000.0));

        List<Imovel> porCidade = mySqlImovelRepository.listarPorFiltros(null, null, "Santo André");
        List<Imovel> porValorMinimo = mySqlImovelRepository.listarPorFiltros(400000.0, null, null);
        List<Imovel> porValorMaximo = mySqlImovelRepository.listarPorFiltros(null, 300000.0, null);
        List<Imovel> combinados = mySqlImovelRepository.listarPorFiltros(
                300000.0,
                400000.0,
                "São Bernardo do Campo"
        );

        assertAll(
                () -> assertEquals(Set.of("Mesma faixa outra cidade"), Set.copyOf(titulos(porCidade))),
                () -> assertTrue(titulos(porValorMinimo).contains("Limite máximo SBC")),
                () -> assertTrue(titulos(porValorMaximo).contains("Limite mínimo SBC")),
                () -> assertEquals(
                        Set.of("Limite mínimo SBC", "Dentro da faixa SBC", "Limite máximo SBC"),
                        Set.copyOf(titulos(combinados))
                ),
                () -> assertFalse(titulos(combinados).contains("Mesma faixa outra cidade")),
                () -> assertFalse(titulos(combinados).contains("Fora da faixa SBC"))
        );
    }

    @Test
    @DisplayName("Deve retornar null ao buscar ID inexistente")
    void deveRetornarNullQuandoBuscarImovelInexistente() {
        assertNull(mySqlImovelRepository.buscarPorId(999999L));
    }

    @Test
    @DisplayName("Deve preservar os metadados reais da tabela imoveis no MySQL")
    void devePreservarMetadadosReaisDaTabela() throws Exception {
        Map<String, ExpectedColumn> expected = expectedColumns();
        Map<String, ActualColumn> actual = new HashMap<>();

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metadata = connection.getMetaData();
            String catalog = connection.getCatalog();

            try (ResultSet columns = metadata.getColumns(catalog, null, "imoveis", null)) {
                while (columns.next()) {
                    actual.put(
                            columns.getString("COLUMN_NAME"),
                            new ActualColumn(
                                    columns.getInt("DATA_TYPE"),
                                    columns.getInt("NULLABLE"),
                                    columns.getInt("COLUMN_SIZE"),
                                    columns.getString("IS_AUTOINCREMENT")
                            )
                    );
                }
            }

            assertEquals(expected.keySet(), actual.keySet());
            expected.forEach((name, expectedColumn) -> assertColumn(name, expectedColumn, actual.get(name)));

            try (ResultSet primaryKeys = metadata.getPrimaryKeys(catalog, null, "imoveis")) {
                assertTrue(primaryKeys.next());
                assertEquals("id", primaryKeys.getString("COLUMN_NAME"));
                assertFalse(primaryKeys.next());
            }
        }
    }

    private void assertColumn(String name, ExpectedColumn expected, ActualColumn actual) {
        assertNotNull(actual, () -> "Coluna ausente: " + name);
        assertAll(
                () -> assertEquals(expected.jdbcType(), actual.jdbcType(), "Tipo divergente em " + name),
                () -> assertEquals(expected.nullable(), actual.nullable(), "Nulabilidade divergente em " + name),
                () -> assertEquals(expected.autoIncrement(), actual.autoIncrement(), "AUTO_INCREMENT divergente em " + name)
        );

        if (expected.length() != null) {
            assertEquals(expected.length(), actual.length(), "Tamanho divergente em " + name);
        }
    }

    private Map<String, ExpectedColumn> expectedColumns() {
        ExpectedColumn varchar = new ExpectedColumn(Types.VARCHAR, DatabaseMetaData.columnNullable, 255, "NO");
        ExpectedColumn integer = new ExpectedColumn(Types.INTEGER, DatabaseMetaData.columnNullable, null, "NO");
        ExpectedColumn decimal = new ExpectedColumn(Types.DOUBLE, DatabaseMetaData.columnNullable, null, "NO");

        return Map.ofEntries(
                Map.entry("id", new ExpectedColumn(Types.BIGINT, DatabaseMetaData.columnNoNulls, null, "YES")),
                Map.entry("titulo", varchar),
                Map.entry("descricao", varchar),
                Map.entry("cidade", varchar),
                Map.entry("bairro", varchar),
                Map.entry("endereco", varchar),
                Map.entry("valor", decimal),
                Map.entry("tipo_negociacao", varchar),
                Map.entry("tipo_imovel", varchar),
                Map.entry("quartos", integer),
                Map.entry("banheiros", integer),
                Map.entry("vagas_garagem", integer),
                Map.entry("area_m2", decimal)
        );
    }

    private void assertImovelCompleto(Imovel imovel, Long id) {
        assertNotNull(imovel);
        assertAll(
                () -> assertEquals(id, imovel.getId()),
                () -> assertEquals("Apartamento no Centro", imovel.getTitulo()),
                () -> assertEquals("Apartamento bem localizado", imovel.getDescricao()),
                () -> assertEquals("São Bernardo do Campo", imovel.getCidade()),
                () -> assertEquals("Centro", imovel.getBairro()),
                () -> assertEquals("Rua das Flores, 100", imovel.getEndereco()),
                () -> assertEquals(450000.0, imovel.getValor()),
                () -> assertEquals("VENDA", imovel.getTipoNegociacao()),
                () -> assertEquals("APARTAMENTO", imovel.getTipoImovel()),
                () -> assertEquals(2, imovel.getQuartos()),
                () -> assertEquals(1, imovel.getBanheiros()),
                () -> assertEquals(1, imovel.getVagasGaragem()),
                () -> assertEquals(65.5, imovel.getAreaM2())
        );
    }

    private List<String> titulos(List<Imovel> imoveis) {
        return imoveis.stream().map(Imovel::getTitulo).toList();
    }

    private Imovel criarImovelCompleto(Long id) {
        return new Imovel(
                id,
                "Apartamento no Centro",
                "Apartamento bem localizado",
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

    private Imovel criarImovelPadrao(String titulo, String cidade, Double valor) {
        return new Imovel(
                null,
                titulo,
                "Descrição de teste",
                cidade,
                "Bairro Teste",
                "Rua Teste, 123",
                valor,
                "VENDA",
                "APARTAMENTO",
                2,
                1,
                1,
                60.0
        );
    }

    private record ExpectedColumn(int jdbcType, int nullable, Integer length, String autoIncrement) {
    }

    private record ActualColumn(int jdbcType, int nullable, int length, String autoIncrement) {
    }
}
