package br.com.fazmerir.services;

import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.dto.DespesaFiltroDto;
import br.com.fazmerir.entities.Despesa;
import br.com.fazmerir.enums.StatusDespesaEnum;
import br.com.fazmerir.filter.DespesaFilter;
import br.com.fazmerir.mapper.DespesaMapper;
import br.com.fazmerir.repository.DespesaRepository;
import br.com.fazmerir.response.DespesaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository repository;
    private final DespesaMapper mapper;


    public Despesa cadastrarDespesa(DespesaDto dto) {
        LocalDate dataVencimento = dto.getDataVencimento();
        StatusDespesaEnum status = dto.getStatusDespesa();
        if(status == null) {
            if (dataVencimento != null) {
                //valida se a data de vencimento já venceu
                status = dataVencimento.isBefore(LocalDate.now()) ? StatusDespesaEnum.VENCIDO : StatusDespesaEnum.A_VENCER;
                dto.setStatusDespesa(status);
            }
        }
        Despesa despesa = mapper.toEntity(dto);
        return repository.save(despesa);
    }


    //Soma despesas com mesmo status, para verificar despesas ja pagas
    public BigDecimal somarDespesaPorStatus() {
        List<Despesa> despesaExistente = repository.findAll();

        return despesaExistente.stream()
                .filter(d -> StatusDespesaEnum.PAGO.equals(d.getStatusDespesa()))
                .map(Despesa::getValorDespesa)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public DespesaDto alteraStatusDespesa(Long id) {
        Despesa despesaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa Não encontrada"));

        StatusDespesaEnum status = despesaExistente.getStatusDespesa() == StatusDespesaEnum.PAGO
                ? StatusDespesaEnum.PENDENTE
                : StatusDespesaEnum.PAGO;

        despesaExistente.setStatusDespesa(status);
        repository.save(despesaExistente);
        return mapper.toDto(despesaExistente);
    }

    public List<DespesaDto> listarDespesasComFiltro(BigDecimal valorDespesa, String descricaoDespesa, StatusDespesaEnum statusDespesa) {
        DespesaFiltroDto filtro = new DespesaFiltroDto();
        filtro.setValorDespesa(valorDespesa);
        filtro.setDescricaoDespesa(descricaoDespesa);
        filtro.setStatusDespesa(statusDespesa);

        List<Despesa> despesas = repository.findAll(DespesaFilter.despesaComFiltros(filtro));
        return despesas.stream()
                .map(mapper::toDto)
                .toList();

    }

    public List<DespesaDto> listaTodasDespesasVencidas() {
        List<Despesa> despesas = repository.findAll();
        LocalDate hoje = LocalDate.now();

        List<Despesa> despesasVencidas = despesas.stream()
                .filter(d -> d.getDataVencimento() != null && d.getDataVencimento().isBefore(hoje.plusDays(1)))
                .map(d -> {
                    if (!d.getStatusDespesa().equals(StatusDespesaEnum.PAGO)) {
                        d.setStatusDespesa(StatusDespesaEnum.VENCIDO);
                        repository.save(d);
                    }
                    return d;
                })
                .collect(Collectors.toList());
        return mapper.toListDto(despesasVencidas);
    }


    public DespesaResponse somarDespesasComFiltro(BigDecimal valorDespesa, String descricaoDespesa, StatusDespesaEnum statusDespesa) {
        DespesaFiltroDto filtro = new DespesaFiltroDto();
        filtro.setValorDespesa(valorDespesa);
        filtro.setDescricaoDespesa(descricaoDespesa);
        filtro.setStatusDespesa(statusDespesa);

        List<Despesa> despesas = repository.findAll(DespesaFilter.despesaComFiltros(filtro));

        BigDecimal total = despesas.stream()
                .map(Despesa::getValorDespesa)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DespesaResponse response = new DespesaResponse();
        response.descricao = "Total das despesas somadas:";
        response.status = statusDespesa;
        response.valor = total;

       return response;
    }


    public void deletaDespesa(Long id) {
        repository.deleteById(id);
    }
}
