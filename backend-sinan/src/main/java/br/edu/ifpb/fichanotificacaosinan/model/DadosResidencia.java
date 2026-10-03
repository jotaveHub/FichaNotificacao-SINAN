package br.edu.ifpb.fichanotificacaosinan.model;

import br.edu.ifpb.fichanotificacaosinan.validation.Padroes;
import br.edu.ifpb.fichanotificacaosinan.validation.ResidenciaValida;
import br.edu.ifpb.fichanotificacaosinan.validation.ValoresPermitidos;
import jakarta.validation.constraints.Pattern;

@ResidenciaValida
public class DadosResidencia {

    @Pattern(regexp = Padroes.UF, message = "UF inválida; use a sigla em maiúsculas, como PB")
    private String uf;
    private String municipio;
    @Pattern(regexp = Padroes.CODIGO_IBGE, message = "O código IBGE deve ter 7 dígitos")
    private String codigoIbge;
    private String distrito;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String geoCampo1;
    private String geoCampo2;
    private String pontoReferencia;
    @Pattern(regexp = Padroes.CEP, message = "CEP inválido; use o formato 58900-000 ou 58900000")
    private String cep;
    private String telefone;
    @ValoresPermitidos(valores = {1, 2, 3, 9},
            message = "Zona inválida; use 1 (urbana), 2 (rural), 3 (periurbana) ou 9 (ignorado)")
    private Integer zona;
    private String pais;

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getGeoCampo1() {
        return geoCampo1;
    }

    public void setGeoCampo1(String geoCampo1) {
        this.geoCampo1 = geoCampo1;
    }

    public String getGeoCampo2() {
        return geoCampo2;
    }

    public void setGeoCampo2(String geoCampo2) {
        this.geoCampo2 = geoCampo2;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getZona() {
        return zona;
    }

    public void setZona(Integer zona) {
        this.zona = zona;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
