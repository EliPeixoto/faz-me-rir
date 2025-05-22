package br.com.fazmerir.services;

import br.com.fazmerir.dto.UsuarioDto;
import br.com.fazmerir.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {


    public String getNomeUsuarioLogado() {
        return JwtUtil.getUsername();
    }

    public String getEmailUsuarioLogado() {
        return JwtUtil.getEmail();
    }

    public List<String> getRolesUsuarioLogado() {
        return JwtUtil.getRoles();
    }

}
