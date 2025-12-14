package br.com.fazmerir.util;

import br.com.fazmerir.entities.AuditLog;
import br.com.fazmerir.repository.AuditLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@Log4j2
public class AuditListener  {

    @PrePersist
    @PreUpdate
    @PreRemove
    public void onAnyChange(Object entity){
        String username = "UNKNOWN";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwt) {
            Map<String, Object> claims = jwt.getTokenAttributes();
            username = (String) claims.get("preferred_username");
        } else if (auth != null && auth.getName() != null) {
            username = auth.getName(); // fallback
        }

        String action = "UNKNOWN";
        if (entity instanceof AuditableEntity auditableEntity) {
            if (auditableEntity.isNew()) {
                action = "CREATE";
            } else {
                action = "UPDATE";
            }
        }


        AuditLog log = new AuditLog();
        log.setUsername(username);
        log.setAction(action);
        log.setEntity(entity.getClass().getSimpleName());
        log.setEntityId(getEntityId(entity));
        log.setCreatedAt(LocalDateTime.now());

        try {
            ObjectMapper mapper = new ObjectMapper();
            log.setDetails(mapper.writeValueAsString(entity));
        } catch (Exception e) {
            log.setDetails("Erro ao serializar entidade: " + e.getMessage());
        }

        SpringContext.getBean(AuditLogRepository.class).save(log);
    }

    private Long getEntityId(Object entity) {
        try {
            Field field = entity.getClass().getDeclaredField("id");
            field.setAccessible(true);
            return (Long) field.get(entity);
        } catch (Exception e) {
            return null;
        }
    }



}
