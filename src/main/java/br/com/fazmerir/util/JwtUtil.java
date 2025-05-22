package br.com.fazmerir.util;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;

public class JwtUtil {

    public static Jwt getJwtToken(){
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AbstractAuthenticationToken authToken
                && authToken.getCredentials() instanceof Jwt jwt) {
            return jwt;
        }
        throw new IllegalStateException("Token JWT nao encontrato no contexto");
    }

    public static  String getUsername(){
        return getJwtToken().getClaimAsString("preferred_username");
    }
    public static String getEmail() {
        return getJwtToken().getClaimAsString("email");
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRoles() {
        Map<String, Object> realmAccess = getJwtToken().getClaim("realm_access");
        if (realmAccess == null || !(realmAccess.get("roles") instanceof List<?>)) {
            throw new IllegalStateException("Roles não encontradas no token");
        }
        return (List<String>) realmAccess.get("roles");
    }
}
