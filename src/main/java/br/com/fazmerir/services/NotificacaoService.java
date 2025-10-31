package br.com.fazmerir.services;

import br.com.fazmerir.dto.DespesaDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class NotificacaoService {
    private final WebClient webClient;
    private final String API_URL;
    private final String INSTANCE_ID;
    private final String API_TOKEN;

    public NotificacaoService(
            @Value("${whatsapp.api.url}") String apiUrl,
            @Value("${whatsapp.instance.id}") String instanceId,
            @Value("${whatsapp.token}") String apiToken,
            WebClient.Builder webClientBuilder) {

        this.API_URL = apiUrl;
        this.INSTANCE_ID = instanceId;
        this.API_TOKEN = apiToken;

        this.webClient = webClientBuilder.baseUrl(API_URL).build();
        log.info("NotificacaoService inicializado com API: {}", API_URL);
    }

    public void enviarAlertaVencimento(DespesaDto despesa) {

        String numeroDestino = "5511976615592"; // Ex: 5511987654321

        // 1. Cria a mensagem formatada
        String mensagem = String.format(
                "⚠️ ALERTA DE VENCIMENTO! ⚠️\nA despesa '%s' no valor de R$ %.2f venceu em %s.",
                despesa.getDescricaoDespesa(),
                despesa.getValorDespesa(),
                despesa.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );

        // 2. Monta o corpo da requisição com os parâmetros exigidos pela API
        // (Isso é o que muda para cada provedor)
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("token", API_TOKEN);
        // O número precisa do código do país (55) e DDD, sem o '+' e sem 'whatsapp:'
        bodyMap.put("to", numeroDestino);
        bodyMap.put("body", mensagem);

        // 3. Faz a chamada POST para o endpoint da instância
        // Endpoint: https://api.ultramsg.com/instance12345/messages/chat
        Mono<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/{instanceId}/messages/chat")
                        .build(INSTANCE_ID))
                .bodyValue(bodyMap)
                .retrieve()
                .bodyToMono(String.class); // Espera um JSON de resposta

        // 4. Trata a resposta (bloqueia o Mono apenas para fins de demonstração/log)
        try {
            String resultado = response.block(); // Bloqueia para esperar a resposta
            log.info("✅ Mensagem WhatsApp enviada com sucesso para: {} | Resposta API: {}", numeroDestino, resultado);

        } catch (Exception e) {
            log.error("❌ Erro ao enviar WhatsApp para {}: {}", numeroDestino, e.getMessage());
        }
    }
}
