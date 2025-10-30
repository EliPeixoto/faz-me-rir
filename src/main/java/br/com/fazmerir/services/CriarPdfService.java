package br.com.fazmerir.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CriarPdfService {

    public ResponseEntity<byte[]> criandoPDf() throws IOException {
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage();
        documento.addPage(pagina);

        PDPageContentStream stream = new PDPageContentStream(documento, pagina);

        PDFont fonte = PDType1Font.HELVETICA;
        float tamanhoFonte = 10f;
        float larguraMax = 270f; // ajuste conforme a margem desejada
        String texto = "ESSE É UM TESTE PARA DEFINIR O QUE FICA EM CIMA E O QUE FICA EMBAIXO, PENSANDO TAMBEM QUE A FRASE PODE ULTRAPASSAR DUAS LINHAS";

        stream.beginText();

        stream.setFont(fonte, tamanhoFonte);

        List<String> linhas = quebraTexto(texto, fonte, tamanhoFonte, larguraMax);

        stream.setLeading(14.5f);
        stream.newLineAtOffset(200,700);

        float recuoExtra = -100f;

        boolean primeiraLinha = true;
        for (String linha : linhas) {
            if (primeiraLinha) {
                stream.showText(linha);
                primeiraLinha = false;
            } else {
                stream.newLine();
                stream.newLineAtOffset(recuoExtra, 0);
                stream.showText(linha);
            }
        }

        stream.endText();


        stream.close();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();



        documento.save(outputStream);
        documento.close();

        outputStream.toByteArray();

        return ResponseEntity.ok(outputStream.toByteArray()) ;

    }


    public List<String> quebraTexto(String texto, PDFont fonte, float tamanhoFonte, float larguraMaxima) throws IOException {


        List<String> linhas = new ArrayList<>();


        String[] palavras = texto.split(" " );

        String linhaAtual = "";

        for(String palavra : palavras){
        String testeLinha = linhaAtual.isEmpty() ? palavra : linhaAtual + " " + palavra;
        float largura = fonte.getStringWidth(testeLinha) / 1000 * tamanhoFonte;

        if(largura > larguraMaxima){
            linhas.add(linhaAtual);
            linhaAtual = palavra;
        }else{
            linhaAtual = testeLinha;
        }

        }
        linhas.add(linhaAtual);

        return linhas;
    }

}
