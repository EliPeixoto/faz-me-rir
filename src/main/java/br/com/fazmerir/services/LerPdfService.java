package br.com.fazmerir.services;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LerPdfService extends PDFTextStripper {


    private final String tagProcurar;
    private final List<TagPosition> posicoes = new ArrayList<>();

    public LerPdfService(String tagProcurar) throws IOException {
        this.tagProcurar = tagProcurar;
    }

   @Override
    protected void writeString(String string, List<TextPosition> posicaoTexto) throws IOException{
        if(string.contains(tagProcurar)){
            int index = string.indexOf(tagProcurar);

            if(index >=0 && index < posicaoTexto.size()){
                TextPosition posicao = posicaoTexto.get(index);
                float x = posicao.getX();
                float y = posicao.getY();
                posicoes.add(new TagPosition(getCurrentPageNo(), x, y));
            }
        }
        super.writeString(string, posicaoTexto);
    }

    public List<TagPosition> getPosicoes(){
        return posicoes;
    }

    public static class TagPosition{
        public int pagina;
        public float x,y;

        public TagPosition(int pagina, float x, float y){
            this.pagina = pagina;
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString() {
            return "Página: " + pagina + ", x: " + x + ", y: " + y;
        }
    }
}
