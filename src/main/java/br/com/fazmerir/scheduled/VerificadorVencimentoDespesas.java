package br.com.fazmerir.scheduled;

import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.services.DespesaService;
import br.com.fazmerir.services.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Component
@RequiredArgsConstructor
public class VerificadorVencimentoDespesas {

    private final DespesaService despesaService;
    private final NotificacaoService notificacaoService;
    DateTimeFormatter formatadorPtBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");

   // @Scheduled(cron = "*/30 * * * * *")
    public void verificarDespesasVencidas(){
        System.out.println("Verificando despesas vencidas...");


        List<DespesaDto> despesasVencidas = despesaService.listaTodasDespesasVencidas();
        for(DespesaDto despesa : despesasVencidas){

            LocalDate dataDespesa = despesa.getDataVencimento();
            String dataFormadata ;
            dataFormadata = dataDespesa.format(formatadorPtBr);
            notificacaoService.enviarAlertaVencimento(despesa);

            System.out.println("Despesa Vencida encontrada: " + despesa.getDescricaoDespesa() + ", Valor: " + despesa.getValorDespesa()
                    + ", Vencido em: " + dataFormadata);

        }
    }
}
