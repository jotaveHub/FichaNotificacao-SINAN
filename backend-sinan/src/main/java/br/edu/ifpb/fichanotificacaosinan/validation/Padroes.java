package br.edu.ifpb.fichanotificacaosinan.validation;

public final class Padroes {

    private Padroes() {
    }

    public static final String UF =
            "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$";
    public static final String CID10 = "^[A-Za-z]\\d{2}(\\.\\d{1,2})?$";
    public static final String SEXO = "^[MFI]$";
    public static final String CARTAO_SUS = "^\\d{15}$";
    public static final String CEP = "^\\d{5}-?\\d{3}$";
    public static final String CODIGO_IBGE = "^\\d{7}$";
}