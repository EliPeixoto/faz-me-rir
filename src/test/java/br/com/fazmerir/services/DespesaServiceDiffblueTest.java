package br.com.fazmerir.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.entities.Despesa;
import br.com.fazmerir.enums.StatusDespesaEnum;
import br.com.fazmerir.mapper.DespesaMapper;
import br.com.fazmerir.repository.DespesaRepository;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DespesaService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DespesaServiceDiffblueTest {
    @MockBean
    private DespesaMapper despesaMapper;

    @MockBean
    private DespesaRepository despesaRepository;

    @Autowired
    private DespesaService despesaService;

    /**
     * Test {@link DespesaService#cadastrarDespesa(DespesaDto)}.
     * <ul>
     *   <li>Then return {@link Despesa#Despesa()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link DespesaService#cadastrarDespesa(DespesaDto)}
     */
    @Test
    @DisplayName("Test cadastrarDespesa(DespesaDto); then return Despesa()")
    void testCadastrarDespesa_thenReturnDespesa() {
        // Arrange
        Despesa despesa = new Despesa();
        despesa.setDescricaoDespesa("Descricao Despesa");
        despesa.setId(1L);
        despesa.setStatusDespesa(StatusDespesaEnum.PAGO);
        despesa.setValorDespesa(new BigDecimal("2.3"));
        when(despesaRepository.save(Mockito.<Despesa>any())).thenReturn(despesa);

        Despesa despesa2 = new Despesa();
        despesa2.setDescricaoDespesa("Descricao Despesa");
        despesa2.setId(1L);
        despesa2.setStatusDespesa(StatusDespesaEnum.PAGO);
        despesa2.setValorDespesa(new BigDecimal("2.3"));
        when(despesaMapper.toEntity(Mockito.<DespesaDto>any())).thenReturn(despesa2);

        DespesaDto dto = new DespesaDto();
        dto.setDescricaoDespesa("Descricao Despesa");
        dto.setStatusDespesa(StatusDespesaEnum.PAGO);
        dto.setValorDespesa(new BigDecimal("2.3"));

        // Act
        Despesa actualCadastrarDespesaResult = despesaService.cadastrarDespesa(dto);

        // Assert
        verify(despesaMapper).toEntity(isA(DespesaDto.class));
        verify(despesaRepository).save(isA(Despesa.class));
        assertSame(despesa, actualCadastrarDespesaResult);
    }

    /**
     * Test {@link DespesaService#cadastrarDespesa(DespesaDto)}.
     * <ul>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link DespesaService#cadastrarDespesa(DespesaDto)}
     */
    @Test
    @DisplayName("Test cadastrarDespesa(DespesaDto); then throw RuntimeException")
    void testCadastrarDespesa_thenThrowRuntimeException() {
        // Arrange
        when(despesaMapper.toEntity(Mockito.<DespesaDto>any())).thenThrow(new RuntimeException("foo"));

        DespesaDto dto = new DespesaDto();
        dto.setDescricaoDespesa("Descricao Despesa");
        dto.setStatusDespesa(StatusDespesaEnum.PAGO);
        dto.setValorDespesa(new BigDecimal("2.3"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> despesaService.cadastrarDespesa(dto));
        verify(despesaMapper).toEntity(isA(DespesaDto.class));
    }

    /**
     * Test {@link DespesaService#alteraStatusDespesa(Long)}.
     * <ul>
     *   <li>Then return {@link DespesaDto} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link DespesaService#alteraStatusDespesa(Long)}
     */
    @Test
    @DisplayName("Test alteraStatusDespesa(Long); then return DespesaDto (default constructor)")
    void testAlteraStatusDespesa_thenReturnDespesaDto() {
        // Arrange
        Despesa despesa = new Despesa();
        despesa.setDescricaoDespesa("Descricao Despesa");
        despesa.setId(1L);
        despesa.setStatusDespesa(StatusDespesaEnum.PAGO);
        despesa.setValorDespesa(new BigDecimal("2.3"));
        Optional<Despesa> ofResult = Optional.of(despesa);

        Despesa despesa2 = new Despesa();
        despesa2.setDescricaoDespesa("Descricao Despesa");
        despesa2.setId(1L);
        despesa2.setStatusDespesa(StatusDespesaEnum.PAGO);
        despesa2.setValorDespesa(new BigDecimal("2.3"));
        when(despesaRepository.save(Mockito.<Despesa>any())).thenReturn(despesa2);
        when(despesaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        DespesaDto despesaDto = new DespesaDto();
        despesaDto.setDescricaoDespesa("Descricao Despesa");
        despesaDto.setStatusDespesa(StatusDespesaEnum.PAGO);
        despesaDto.setValorDespesa(new BigDecimal("2.3"));
        when(despesaMapper.toDto(Mockito.<Despesa>any())).thenReturn(despesaDto);

        // Act
        DespesaDto actualAlteraStatusDespesaResult = despesaService.alteraStatusDespesa(1L);

        // Assert
        verify(despesaMapper).toDto(isA(Despesa.class));
        verify(despesaRepository).findById(eq(1L));
        verify(despesaRepository).save(isA(Despesa.class));
        assertSame(despesaDto, actualAlteraStatusDespesaResult);
    }

    /**
     * Test {@link DespesaService#alteraStatusDespesa(Long)}.
     * <ul>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link DespesaService#alteraStatusDespesa(Long)}
     */
    @Test
    @DisplayName("Test alteraStatusDespesa(Long); then throw RuntimeException")
    void testAlteraStatusDespesa_thenThrowRuntimeException() {
        // Arrange
        Despesa despesa = new Despesa();
        despesa.setDescricaoDespesa("Descricao Despesa");
        despesa.setId(1L);
        despesa.setStatusDespesa(StatusDespesaEnum.PAGO);
        despesa.setValorDespesa(new BigDecimal("2.3"));
        Optional<Despesa> ofResult = Optional.of(despesa);

        Despesa despesa2 = new Despesa();
        despesa2.setDescricaoDespesa("Descricao Despesa");
        despesa2.setId(1L);
        despesa2.setStatusDespesa(StatusDespesaEnum.PAGO);
        despesa2.setValorDespesa(new BigDecimal("2.3"));
        when(despesaRepository.save(Mockito.<Despesa>any())).thenReturn(despesa2);
        when(despesaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(despesaMapper.toDto(Mockito.<Despesa>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> despesaService.alteraStatusDespesa(1L));
        verify(despesaMapper).toDto(isA(Despesa.class));
        verify(despesaRepository).findById(eq(1L));
        verify(despesaRepository).save(isA(Despesa.class));
    }
}
