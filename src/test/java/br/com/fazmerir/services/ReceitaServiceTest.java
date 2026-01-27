package br.com.fazmerir.services;

import br.com.fazmerir.entities.Receita;
import br.com.fazmerir.enums.StatusReceitaEnum;
import br.com.fazmerir.mapper.ReceitaMapper;
import br.com.fazmerir.repository.ReceitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ReceitaServiceTest {


    private ReceitaRepository receitaRepository;
    private ReceitaService receitaService;
    private ReceitaMapper receitaMapper;

    @BeforeEach
    void setUp() {
        receitaRepository = mock(ReceitaRepository.class);
        receitaService = new ReceitaService(receitaMapper, receitaRepository); // ainda não existe -> TDD vai te forçar a criar
    }

    @Test
    void deve_retornar_somente_receitas_pendentes() {
        var pendente1 = receitaComStatus(StatusReceitaEnum.PENDENTE);
        var recebido = receitaComStatus(StatusReceitaEnum.RECEBIDO);
        var pendente2 = receitaComStatus(StatusReceitaEnum.PENDENTE);

        when(receitaRepository.findAll()).thenReturn(List.of(pendente1, recebido, pendente2));

        var resultadoFiltrado = receitaService.listarReceitasPendentes();

        assertEquals(2, resultadoFiltrado.size());
        assertTrue(resultadoFiltrado.stream().allMatch(r -> r.getStatusReceita() == StatusReceitaEnum.PENDENTE));

        verify(receitaRepository).findAll();
        verifyNoMoreInteractions(receitaRepository);
    }


    private Receita receitaComStatus(StatusReceitaEnum status) {
        Receita r = new Receita();
        r.setStatusReceita(status);
        return r;
    }
}
