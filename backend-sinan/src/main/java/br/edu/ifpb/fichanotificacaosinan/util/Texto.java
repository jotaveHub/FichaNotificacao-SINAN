package br.edu.ifpb.fichanotificacaosinan.util;

import java.text.Normalizer;
import java.util.Locale;

public final class Texto {

    private Texto() {
    }

    public static boolean vazio(String texto) {
        return texto == null || texto.isBlank();
    }

    public static String normalizar(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }

    public static String semAcentos(String texto) {
        String decomposto = Normalizer.normalize(normalizar(texto), Normalizer.Form.NFD);
        return decomposto.replaceAll("\\p{M}", "");
    }

    public static boolean contem(String campo, String busca) {
        if (vazio(busca)) {
            return true;
        }
        return campo != null && semAcentos(campo).contains(semAcentos(busca));
    }
}