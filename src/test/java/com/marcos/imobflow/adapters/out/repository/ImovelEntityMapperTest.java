package com.marcos.imobflow.adapters.out.repository;

import com.marcos.imobflow.domain.model.Imovel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImovelEntityMapperTest {

    @Test
    void deveConverterDominioParaEntidadePreservandoTodosOsAtributos() {
        Imovel imovel = criarImovel();

        ImovelEntity entity = ImovelEntityMapper.toEntity(imovel);

        assertNotNull(entity);
        assertAll(
                () -> assertEquals(42L, entity.getId()),
                () -> assertEquals("Apartamento no Centro", entity.getTitulo()),
                () -> assertEquals("Apartamento bem localizado", entity.getDescricao()),
                () -> assertEquals("São Bernardo do Campo", entity.getCidade()),
                () -> assertEquals("Centro", entity.getBairro()),
                () -> assertEquals("Rua das Flores, 100", entity.getEndereco()),
                () -> assertEquals(450000.0, entity.getValor()),
                () -> assertEquals("VENDA", entity.getTipoNegociacao()),
                () -> assertEquals("APARTAMENTO", entity.getTipoImovel()),
                () -> assertEquals(2, entity.getQuartos()),
                () -> assertEquals(1, entity.getBanheiros()),
                () -> assertEquals(1, entity.getVagasGaragem()),
                () -> assertEquals(65.5, entity.getAreaM2())
        );
    }

    @Test
    void deveConverterEntidadeParaDominioPreservandoTodosOsAtributos() {
        ImovelEntity entity = criarEntity();

        Imovel imovel = ImovelEntityMapper.toDomain(entity);

        assertNotNull(imovel);
        assertAll(
                () -> assertEquals(42L, imovel.getId()),
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

    @Test
    void deveRetornarNullAoConverterEntradaNull() {
        assertAll(
                () -> assertNull(ImovelEntityMapper.toEntity(null)),
                () -> assertNull(ImovelEntityMapper.toDomain(null))
        );
    }

    @Test
    void devePreservarMetadadosJpaDoMapeamentoAtual() throws NoSuchFieldException {
        Entity entity = ImovelEntity.class.getAnnotation(Entity.class);
        Table table = ImovelEntity.class.getAnnotation(Table.class);
        Field id = ImovelEntity.class.getDeclaredField("id");
        GeneratedValue generatedValue = id.getAnnotation(GeneratedValue.class);

        assertAll(
                () -> assertNotNull(entity),
                () -> assertNotNull(table),
                () -> assertEquals("imoveis", table.name()),
                () -> assertTrue(id.isAnnotationPresent(Id.class)),
                () -> assertNotNull(generatedValue),
                () -> assertEquals(GenerationType.IDENTITY, generatedValue.strategy()),
                () -> assertColumn(id, "id", Long.class, false),
                () -> assertColumn("titulo", "titulo", String.class, true),
                () -> assertColumn("descricao", "descricao", String.class, true),
                () -> assertColumn("cidade", "cidade", String.class, true),
                () -> assertColumn("bairro", "bairro", String.class, true),
                () -> assertColumn("endereco", "endereco", String.class, true),
                () -> assertColumn("valor", "valor", Double.class, true),
                () -> assertColumn("tipoNegociacao", "tipo_negociacao", String.class, true),
                () -> assertColumn("tipoImovel", "tipo_imovel", String.class, true),
                () -> assertColumn("quartos", "quartos", Integer.class, true),
                () -> assertColumn("banheiros", "banheiros", Integer.class, true),
                () -> assertColumn("vagasGaragem", "vagas_garagem", Integer.class, true),
                () -> assertColumn("areaM2", "area_m2", Double.class, true)
        );
    }

    private void assertColumn(String fieldName, String columnName, Class<?> type, boolean nullable)
            throws NoSuchFieldException {
        assertColumn(ImovelEntity.class.getDeclaredField(fieldName), columnName, type, nullable);
    }

    private void assertColumn(Field field, String columnName, Class<?> type, boolean nullable)
            throws NoSuchFieldException {
        Column column = field.getAnnotation(Column.class);
        Field currentField = Imovel.class.getDeclaredField(field.getName());

        assertNotNull(column, () -> "@Column ausente em " + field.getName());
        assertAll(
                () -> assertEquals(columnName, column.name()),
                () -> assertEquals(type, field.getType()),
                () -> assertEquals(currentField.getType(), field.getType()),
                () -> assertEquals(nullable, column.nullable())
        );

        if (String.class.equals(type)) {
            assertEquals(255, column.length());
        }
    }

    private Imovel criarImovel() {
        return new Imovel(
                42L,
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

    private ImovelEntity criarEntity() {
        return new ImovelEntity(
                42L,
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
}
