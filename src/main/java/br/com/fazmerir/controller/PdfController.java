package br.com.fazmerir.controller;


import br.com.fazmerir.services.CriarPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
@RequiredArgsConstructor
public class PdfController {


    private final CriarPdfService pdfService;


    @GetMapping(produces = "application/pdf")
    private ResponseEntity<byte[]> visualizarPdf() throws IOException {
        return pdfService.criandoPDf();
    }


}
