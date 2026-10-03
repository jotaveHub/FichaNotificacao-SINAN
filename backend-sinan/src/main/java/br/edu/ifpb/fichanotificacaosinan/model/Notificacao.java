package br.edu.ifpb.fichanotificacaosinan.model;

import java.time.LocalDate;
import java.time.Period;

import br.edu.ifpb.fichanotificacaosinan.validation.IdadeEGestanteValidos;
import br.edu.ifpb.fichanotificacaosinan.validation.ValoresPermitidos;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifpb.fichanotificacaosinan.validation.Padroes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@IdadeEGestanteValidos
public class Notificacao {

    private Long id;

    // Dados gerais
    @NotBlank(message = "O número da notificação é obrigatório")
    private String numeroNotificacao;

    @NotBlank(message = "O agravo/doença é obrigatório")
    private String agravo;

    @Pattern(regexp = Padroes.CID10, message = "O código CID10 deve seguir o formato A90 ou B15.9")
    private String codigoCid10;

    @Pattern(regexp = Padroes.UF, message = "UF inválida; use a sigla em maiúsculas, como PB")
    private String ufNotificacao;   // com o @NotBlank acima, como já está

    @Pattern(regexp = Padroes.SEXO, message = "O sexo deve ser M, F ou I")
    private String sexo;            // com o @NotBlank acima

    @Pattern(regexp = Padroes.CARTAO_SUS, message = "O cartão SUS deve ter 15 dígitos")
    private String cartaoSus;

    @Pattern(regexp = Padroes.UF, message = "UF inválida; use a sigla em maiúsculas, como PB")
    private String ufInfeccao;

    @Pattern(regexp = Padroes.CODIGO_IBGE, message = "O código IBGE deve ter 7 dígitos")
    private String codigoIbgeMunicipioInfeccao;

    @NotNull(message = "A data da notificação é obrigatória")
    @PastOrPresent(message = "A data da notificação não pode ser futura")
    private LocalDate dataNotificacao;


    @NotBlank(message = "O município de notificação é obrigatório")
    private String municipioNotificacao;

    @NotBlank(message = "A unidade de saúde é obrigatória")
    private String unidadeSaude;

    private String codigoUnidadeSaude;

    @NotNull(message = "A data dos primeiros sintomas é obrigatória")
    @PastOrPresent(message = "A data dos primeiros sintomas não pode ser futura")
    private LocalDate dataPrimeirosSintomas;

    // Dados do paciente
    @NotBlank(message = "O nome do paciente é obrigatório")
    private String nomePaciente;

    @PastOrPresent(message = "A data de nascimento não pode ser futura")
    private LocalDate dataNascimento;

    @Min(value = 0, message = "A idade não pode ser negativa")
    private Integer idadeValor;

    @Min(value = 1, message = "A unidade da idade deve ser de 1 a 4 (hora, dia, mês, ano)")
    @Max(value = 4, message = "A unidade da idade deve ser de 1 a 4 (hora, dia, mês, ano)")
    private Integer idadeUnidade;

    @ValoresPermitidos(valores = {1, 2, 3, 4, 5, 6, 9},
            message = "Gestante inválido; use 1 a 6 ou 9")
    private Integer gestante;

    @ValoresPermitidos(valores = {1, 2, 3, 4, 5, 9},
            message = "Raça/cor inválida; use 1 a 5 ou 9")
    private Integer racaCor;

    @Min(value = 0, message = "A escolaridade deve ser de 0 a 10")
    @Max(value = 10, message = "A escolaridade deve ser de 0 a 10")
    private Integer escolaridade;

    private String nomeMae;

    // Dados de residência
    @NotNull(message = "Os dados de residência são obrigatórios")
    @Valid
    private DadosResidencia dadosResidencia;

    // Conclusão
    @NotNull(message = "A data da investigação é obrigatória")
    @PastOrPresent(message = "A data da investigação não pode ser futura")
    private LocalDate dataInvestigacao;

    @Min(value = 1, message = "A classificação final deve ser 1 (confirmado) ou 2 (descartado)")
    @Max(value = 2, message = "A classificação final deve ser 1 (confirmado) ou 2 (descartado)")
    private Integer classificacaoFinal;

    @Min(value = 1, message = "O critério deve ser 1 (laboratorial) ou 2 (clínico-epidemiológico)")
    @Max(value = 2, message = "O critério deve ser 1 (laboratorial) ou 2 (clínico-epidemiológico)")
    private Integer criterioConfirmacao;

    @Min(value = 1, message = "Autóctone deve ser 1 (sim), 2 (não) ou 3 (indeterminado)")
    @Max(value = 3, message = "Autóctone deve ser 1 (sim), 2 (não) ou 3 (indeterminado)")
    private Integer autoctone;


    private String paisInfeccao;
    private String municipioInfeccao;


    private String distritoInfeccao;
    private String bairroInfeccao;

    @ValoresPermitidos(valores = {1, 2, 9},
            message = "Doença relacionada ao trabalho inválida; use 1, 2 ou 9")
    private Integer doencaRelacionadaTrabalho;

    @ValoresPermitidos(valores = {1, 2, 3, 9},
            message = "Evolução do caso inválida; use 1, 2, 3 ou 9")
    private Integer evolucaoCaso;

    @PastOrPresent(message = "A data do óbito não pode ser futura")
    private LocalDate dataObito;

    @PastOrPresent(message = "A data de encerramento não pode ser futura")
    private LocalDate dataEncerramento;

    // Investigador
    private String investigadorMunicipioUnidade;
    private String investigadorCodigoUnidade;
    private String investigadorNome;
    private String investigadorFuncao;

    // Informações complementares
    private String observacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroNotificacao() {
        return numeroNotificacao;
    }

    public void setNumeroNotificacao(String numeroNotificacao) {
        this.numeroNotificacao = numeroNotificacao;
    }

    public String getAgravo() {
        return agravo;
    }

    public void setAgravo(String agravo) {
        this.agravo = agravo;
    }

    public String getCodigoCid10() {
        return codigoCid10;
    }

    public void setCodigoCid10(String codigoCid10) {
        this.codigoCid10 = codigoCid10;
    }

    public LocalDate getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(LocalDate dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public String getUfNotificacao() {
        return ufNotificacao;
    }

    public void setUfNotificacao(String ufNotificacao) {
        this.ufNotificacao = ufNotificacao;
    }

    public String getMunicipioNotificacao() {
        return municipioNotificacao;
    }

    public void setMunicipioNotificacao(String municipioNotificacao) {
        this.municipioNotificacao = municipioNotificacao;
    }

    public String getUnidadeSaude() {
        return unidadeSaude;
    }

    public void setUnidadeSaude(String unidadeSaude) {
        this.unidadeSaude = unidadeSaude;
    }

    public String getCodigoUnidadeSaude() {
        return codigoUnidadeSaude;
    }

    public void setCodigoUnidadeSaude(String codigoUnidadeSaude) {
        this.codigoUnidadeSaude = codigoUnidadeSaude;
    }

    public LocalDate getDataPrimeirosSintomas() {
        return dataPrimeirosSintomas;
    }

    public void setDataPrimeirosSintomas(LocalDate dataPrimeirosSintomas) {
        this.dataPrimeirosSintomas = dataPrimeirosSintomas;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdadeValor() {
        return idadeValor;
    }

    public void setIdadeValor(Integer idadeValor) {
        this.idadeValor = idadeValor;
    }

    public Integer getIdadeUnidade() {
        return idadeUnidade;
    }

    public void setIdadeUnidade(Integer idadeUnidade) {
        this.idadeUnidade = idadeUnidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getGestante() {
        return gestante;
    }

    public void setGestante(Integer gestante) {
        this.gestante = gestante;
    }

    public Integer getRacaCor() {
        return racaCor;
    }

    public void setRacaCor(Integer racaCor) {
        this.racaCor = racaCor;
    }

    public Integer getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Integer escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getCartaoSus() {
        return cartaoSus;
    }

    public void setCartaoSus(String cartaoSus) {
        this.cartaoSus = cartaoSus;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public DadosResidencia getDadosResidencia() {
        return dadosResidencia;
    }

    public void setDadosResidencia(DadosResidencia dadosResidencia) {
        this.dadosResidencia = dadosResidencia;
    }

    public LocalDate getDataInvestigacao() {
        return dataInvestigacao;
    }

    public void setDataInvestigacao(LocalDate dataInvestigacao) {
        this.dataInvestigacao = dataInvestigacao;
    }

    public Integer getClassificacaoFinal() {
        return classificacaoFinal;
    }

    public void setClassificacaoFinal(Integer classificacaoFinal) {
        this.classificacaoFinal = classificacaoFinal;
    }

    public Integer getCriterioConfirmacao() {
        return criterioConfirmacao;
    }

    public void setCriterioConfirmacao(Integer criterioConfirmacao) {
        this.criterioConfirmacao = criterioConfirmacao;
    }

    public Integer getAutoctone() {
        return autoctone;
    }

    public void setAutoctone(Integer autoctone) {
        this.autoctone = autoctone;
    }

    public String getUfInfeccao() {
        return ufInfeccao;
    }

    public void setUfInfeccao(String ufInfeccao) {
        this.ufInfeccao = ufInfeccao;
    }

    public String getPaisInfeccao() {
        return paisInfeccao;
    }

    public void setPaisInfeccao(String paisInfeccao) {
        this.paisInfeccao = paisInfeccao;
    }

    public String getMunicipioInfeccao() {
        return municipioInfeccao;
    }

    public void setMunicipioInfeccao(String municipioInfeccao) {
        this.municipioInfeccao = municipioInfeccao;
    }

    public String getCodigoIbgeMunicipioInfeccao() {
        return codigoIbgeMunicipioInfeccao;
    }

    public void setCodigoIbgeMunicipioInfeccao(String codigoIbgeMunicipioInfeccao) {
        this.codigoIbgeMunicipioInfeccao = codigoIbgeMunicipioInfeccao;
    }

    public String getDistritoInfeccao() {
        return distritoInfeccao;
    }

    public void setDistritoInfeccao(String distritoInfeccao) {
        this.distritoInfeccao = distritoInfeccao;
    }

    public String getBairroInfeccao() {
        return bairroInfeccao;
    }

    public void setBairroInfeccao(String bairroInfeccao) {
        this.bairroInfeccao = bairroInfeccao;
    }

    public Integer getDoencaRelacionadaTrabalho() {
        return doencaRelacionadaTrabalho;
    }

    public void setDoencaRelacionadaTrabalho(Integer doencaRelacionadaTrabalho) {
        this.doencaRelacionadaTrabalho = doencaRelacionadaTrabalho;
    }

    public Integer getEvolucaoCaso() {
        return evolucaoCaso;
    }

    public void setEvolucaoCaso(Integer evolucaoCaso) {
        this.evolucaoCaso = evolucaoCaso;
    }

    public LocalDate getDataObito() {
        return dataObito;
    }

    public void setDataObito(LocalDate dataObito) {
        this.dataObito = dataObito;
    }

    public LocalDate getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDate dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getInvestigadorMunicipioUnidade() {
        return investigadorMunicipioUnidade;
    }

    public void setInvestigadorMunicipioUnidade(String investigadorMunicipioUnidade) {
        this.investigadorMunicipioUnidade = investigadorMunicipioUnidade;
    }

    public String getInvestigadorCodigoUnidade() {
        return investigadorCodigoUnidade;
    }

    public void setInvestigadorCodigoUnidade(String investigadorCodigoUnidade) {
        this.investigadorCodigoUnidade = investigadorCodigoUnidade;
    }

    public String getInvestigadorNome() {
        return investigadorNome;
    }

    public void setInvestigadorNome(String investigadorNome) {
        this.investigadorNome = investigadorNome;
    }

    public String getInvestigadorFuncao() {
        return investigadorFuncao;
    }

    public void setInvestigadorFuncao(String investigadorFuncao) {
        this.investigadorFuncao = investigadorFuncao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @JsonIgnore
    @AssertTrue(message = "A data de nascimento não pode ser posterior à data da notificação")
    public boolean isNascimentoValido() {
        return dataNascimento == null || dataNotificacao == null
                || !dataNascimento.isAfter(dataNotificacao);
    }

    @JsonIgnore
    @AssertTrue(message = "A data dos primeiros sintomas não pode ser posterior à data da notificação")
    public boolean isSintomasValidos() {
        return dataPrimeirosSintomas == null || dataNotificacao == null
                || !dataPrimeirosSintomas.isAfter(dataNotificacao);
    }

    @JsonIgnore
    @AssertTrue(message = "A data de encerramento não pode ser anterior à data da notificação")
    public boolean isEncerramentoValido() {
        return dataEncerramento == null || dataNotificacao == null
                || !dataEncerramento.isBefore(dataNotificacao);
    }

    public Integer idadeEmAnos() {
        if (dataNascimento != null) {
            LocalDate referencia = dataNotificacao != null ? dataNotificacao : LocalDate.now();
            if (dataNascimento.isAfter(referencia)) {
                return null;
            }
            return Period.between(dataNascimento, referencia).getYears();
        }
        if (idadeValor != null && idadeUnidade != null) {
            return idadeUnidade == 4 ? idadeValor : 0;
        }
        return null;
    }
}