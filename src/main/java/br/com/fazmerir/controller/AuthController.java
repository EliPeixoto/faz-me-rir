package br.com.fazmerir.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200") // Libera só para o Angular
public class AuthController {



    @GetMapping("/ping")
    public String ping() { return "pong"; }

}
