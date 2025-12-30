package br.com.fazmerir.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200") // Libera só para o Angular
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha) {
        // Simulando autenticação
        if ("admin@admin.com".equals(email) && "123456".equals(senha)) {
            return ResponseEntity.ok("fake-token-123"); // Simula token
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @GetMapping("/ping")
    public String ping() { return "pong"; }

}
