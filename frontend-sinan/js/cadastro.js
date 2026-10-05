const UFS = ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG',
    'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'];
const OPCOES_UF = UFS.map(uf => [uf, uf]);

const SECOES = [
    {
        titulo: 'Dados gerais', campos: [
            {nome: 'numeroNotificacao', rotulo: 'N.º da notificação *', tipo: 'texto'},
            {nome: 'agravo', rotulo: '2 - Agravo/doença *', tipo: 'texto'},
            {nome: 'codigoCid10', rotulo: '2 - Código (CID10)', tipo: 'texto'},
            {nome: 'dataNotificacao', rotulo: '3 - Data da notificação *', tipo: 'data'},
            {nome: 'ufNotificacao', rotulo: '4 - UF *', tipo: 'select', opcoes: OPCOES_UF},
            {nome: 'municipioNotificacao', rotulo: '5 - Município de notificação *', tipo: 'texto'},
            {nome: 'unidadeSaude', rotulo: '6 - Unidade de saúde *', tipo: 'texto'},
            {nome: 'codigoUnidadeSaude', rotulo: '6 - Código da unidade', tipo: 'texto'},
            {nome: 'dataPrimeirosSintomas', rotulo: '7 - Data dos primeiros sintomas *', tipo: 'data'}
        ]
    },
    {
        titulo: 'Dados do paciente', campos: [
            {nome: 'nomePaciente', rotulo: '8 - Nome do paciente *', tipo: 'texto'},
            {nome: 'dataNascimento', rotulo: '9 - Data de nascimento', tipo: 'data'},
            {nome: 'idadeValor', rotulo: '10 - Idade (obrigatória sem data de nascimento)', tipo: 'numero'},
            {
                nome: 'idadeUnidade', rotulo: '10 - Unidade da idade', tipo: 'select', numerico: true,
                opcoes: [[1, '1 - Hora'], [2, '2 - Dia'], [3, '3 - Mês'], [4, '4 - Ano']]
            },
            {
                nome: 'sexo', rotulo: '11 - Sexo *', tipo: 'select',
                opcoes: [['M', 'M - Masculino'], ['F', 'F - Feminino'], ['I', 'I - Ignorado']]
            },
            {
                nome: 'gestante', rotulo: '12 - Gestante (obrigatório se sexo = F)', tipo: 'select', numerico: true,
                opcoes: [[1, '1 - 1º trimestre'], [2, '2 - 2º trimestre'], [3, '3 - 3º trimestre'],
                    [4, '4 - Idade gestacional ignorada'], [5, '5 - Não'], [6, '6 - Não se aplica'], [9, '9 - Ignorado']]
            },
            {
                nome: 'racaCor', rotulo: '13 - Raça/Cor', tipo: 'select', numerico: true,
                opcoes: [[1, '1 - Branca'], [2, '2 - Preta'], [3, '3 - Amarela'], [4, '4 - Parda'],
                    [5, '5 - Indígena'], [9, '9 - Ignorado']]
            },
            {
                nome: 'escolaridade', rotulo: '14 - Escolaridade', tipo: 'select', numerico: true,
                opcoes: [[0, '0 - Analfabeto'], [1, '1 - 1ª a 4ª série incompleta do EF'],
                    [2, '2 - 4ª série completa do EF'], [3, '3 - 5ª à 8ª série incompleta do EF'],
                    [4, '4 - Ensino fundamental completo'], [5, '5 - Ensino médio incompleto'],
                    [6, '6 - Ensino médio completo'], [7, '7 - Educação superior incompleta'],
                    [8, '8 - Educação superior completa'], [9, '9 - Ignorado'], [10, '10 - Não se aplica']]
            },
            {nome: 'cartaoSus', rotulo: '15 - Cartão SUS (15 dígitos)', tipo: 'texto'},
            {nome: 'nomeMae', rotulo: '16 - Nome da mãe', tipo: 'texto'}
        ]
    },
    {
        titulo: 'Dados de residência', campos: [
            {
                nome: 'dadosResidencia.uf',
                rotulo: '17 - UF (obrigatória se reside no Brasil)',
                tipo: 'select',
                opcoes: OPCOES_UF
            },
            {nome: 'dadosResidencia.municipio', rotulo: '18 - Município (obrigatório se há UF)', tipo: 'texto'},
            {nome: 'dadosResidencia.codigoIbge', rotulo: '18 - Código (IBGE)', tipo: 'texto'},
            {nome: 'dadosResidencia.distrito', rotulo: '19 - Distrito', tipo: 'texto'},
            {nome: 'dadosResidencia.bairro', rotulo: '20 - Bairro', tipo: 'texto'},
            {nome: 'dadosResidencia.logradouro', rotulo: '21 - Logradouro', tipo: 'texto'},
            {nome: 'dadosResidencia.numero', rotulo: '22 - Número', tipo: 'texto'},
            {nome: 'dadosResidencia.complemento', rotulo: '23 - Complemento', tipo: 'texto'},
            {nome: 'dadosResidencia.geoCampo1', rotulo: '24 - Geo campo 1', tipo: 'texto'},
            {nome: 'dadosResidencia.geoCampo2', rotulo: '25 - Geo campo 2', tipo: 'texto'},
            {nome: 'dadosResidencia.pontoReferencia', rotulo: '26 - Ponto de referência', tipo: 'texto'},
            {nome: 'dadosResidencia.cep', rotulo: '27 - CEP', tipo: 'texto'},
            {nome: 'dadosResidencia.telefone', rotulo: '28 - (DDD) Telefone', tipo: 'texto'},
            {
                nome: 'dadosResidencia.zona', rotulo: '29 - Zona', tipo: 'select', numerico: true,
                opcoes: [[1, '1 - Urbana'], [2, '2 - Rural'], [3, '3 - Periurbana'], [9, '9 - Ignorado']]
            },
            {nome: 'dadosResidencia.pais', rotulo: '30 - País (se reside em outro país)', tipo: 'texto'}
        ]
    },
    {
        titulo: 'Conclusão', campos: [
            {nome: 'dataInvestigacao', rotulo: '31 - Data da investigação *', tipo: 'data'},
            {
                nome: 'classificacaoFinal', rotulo: '32 - Classificação final', tipo: 'select', numerico: true,
                opcoes: [[1, '1 - Confirmado'], [2, '2 - Descartado']]
            },
            {
                nome: 'criterioConfirmacao',
                rotulo: '33 - Critério de confirmação/descarte',
                tipo: 'select',
                numerico: true,
                opcoes: [[1, '1 - Laboratorial'], [2, '2 - Clínico-epidemiológico']]
            },
            {
                nome: 'doencaRelacionadaTrabalho',
                rotulo: '40 - Doença relacionada ao trabalho',
                tipo: 'select',
                numerico: true,
                opcoes: [[1, '1 - Sim'], [2, '2 - Não'], [9, '9 - Ignorado']]
            },
            {
                nome: 'evolucaoCaso', rotulo: '41 - Evolução do caso', tipo: 'select', numerico: true,
                opcoes: [[1, '1 - Cura'], [2, '2 - Óbito pelo agravo notificado'],
                    [3, '3 - Óbito por outras causas'], [9, '9 - Ignorado']]
            },
            {nome: 'dataObito', rotulo: '42 - Data do óbito', tipo: 'data'},
            {nome: 'dataEncerramento', rotulo: '43 - Data do encerramento', tipo: 'data'}
        ]
    },
    {
        titulo: 'Local provável da fonte de infecção', campos: [
            {
                nome: 'autoctone', rotulo: '34 - Autóctone do município de residência?', tipo: 'select', numerico: true,
                opcoes: [[1, '1 - Sim'], [2, '2 - Não'], [3, '3 - Indeterminado']]
            },
            {nome: 'ufInfeccao', rotulo: '35 - UF', tipo: 'select', opcoes: OPCOES_UF},
            {nome: 'paisInfeccao', rotulo: '36 - País', tipo: 'texto'},
            {nome: 'municipioInfeccao', rotulo: '37 - Município', tipo: 'texto'},
            {nome: 'codigoIbgeMunicipioInfeccao', rotulo: '37 - Código (IBGE)', tipo: 'texto'},
            {nome: 'distritoInfeccao', rotulo: '38 - Distrito', tipo: 'texto'},
            {nome: 'bairroInfeccao', rotulo: '39 - Bairro', tipo: 'texto'}
        ]
    },
    {
        titulo: 'Investigador e observações', campos: [
            {nome: 'investigadorMunicipioUnidade', rotulo: 'Município/Unidade de saúde', tipo: 'texto'},
            {nome: 'investigadorCodigoUnidade', rotulo: 'Código da unidade de saúde', tipo: 'texto'},
            {nome: 'investigadorNome', rotulo: 'Nome do investigador', tipo: 'texto'},
            {nome: 'investigadorFuncao', rotulo: 'Função', tipo: 'texto'},
            {nome: 'observacoes', rotulo: 'Informações complementares e observações', tipo: 'area'}
        ]
    }
];

const formulario = document.getElementById('formulario');
const mensagem = document.getElementById('mensagem');
const id = new URLSearchParams(window.location.search).get('id');

function criarControle(campo) {
    if (campo.tipo === 'select') {
        const opcoes = campo.opcoes
            .map(([valor, texto]) => `<option value="${valor}">${escapar(texto)}</option>`)
            .join('');
        return `<select name="${campo.nome}"><option value="">—</option>${opcoes}</select>`;
    }
    if (campo.tipo === 'area') {
        return `<textarea name="${campo.nome}" rows="3"></textarea>`;
    }
    const tipos = {texto: 'text', data: 'date', numero: 'number'};
    const extra = campo.tipo === 'numero' ? ' min="0" step="1"' : '';
    return `<input type="${tipos[campo.tipo]}" name="${campo.nome}"${extra}>`;
}

function montarFormulario() {
    formulario.innerHTML = SECOES.map(secao => `
        <fieldset>
            <legend>${secao.titulo}</legend>
            <div class="grade">
                ${secao.campos.map(campo => `
                    <label>${escapar(campo.rotulo)}
                        ${criarControle(campo)}
                        <span class="msg-campo" data-erro="${campo.nome}"></span>
                    </label>
                `).join('')}
            </div>
        </fieldset>
    `).join('') + `
        <div class="acoes">
                        <button type="submit">${id ? 'Salvar alterações' : 'Cadastrar'}</button>
            <a class="botao secundario" href="index.html">Cancelar</a>
        </div>
    `;
}

function definirValor(objeto, caminho, valor) {
    const partes = caminho.split('.');
    let atual = objeto;
    for (let i = 0; i < partes.length - 1; i++) {
        atual[partes[i]] = atual[partes[i]] ?? {};
        atual = atual[partes[i]];
    }
    atual[partes[partes.length - 1]] = valor;
}

function lerFormulario() {
    const notificacao = {};
    for (const secao of SECOES) {
        for (const campo of secao.campos) {
            const texto = formulario.elements[campo.nome].value.trim();
            let valor = texto === '' ? null : texto;
            if (valor !== null && (campo.numerico || campo.tipo === 'numero')) {
                valor = Number(valor);
            }
            definirValor(notificacao, campo.nome, valor);
        }
    }
    return notificacao;
}

function limparErros() {
    mensagem.innerHTML = '';
    formulario.querySelectorAll('.invalido').forEach(el => el.classList.remove('invalido'));
    formulario.querySelectorAll('[data-erro]').forEach(el => {
        el.textContent = '';
    });
}

function mostrarErros(erro) {
    mensagem.innerHTML = `<div class="alerta erro">${escapar(mensagemDeErro(erro))}</div>`;

    const lista = erro.problema?.erros ?? [];
    for (const item of lista) {
        const controle = formulario.elements[item.campo];
        const aviso = formulario.querySelector(`[data-erro="${item.campo}"]`);
        if (controle) {
            controle.classList.add('invalido');
        }
        if (aviso) {
            aviso.textContent = item.mensagem;
        }
    }
    window.scrollTo({top: 0, behavior: 'smooth'});
}

formulario.addEventListener('submit', async evento => {
    evento.preventDefault();
    limparErros();
    try {
        await requisitar(id ? `${API_URL}/${id}` : API_URL, {
            method: id ? 'PUT' : 'POST',
            body: JSON.stringify(lerFormulario())
        });
        window.location.href = 'index.html';
    } catch (erro) {
        mostrarErros(erro);
    }
});

function obterValor(objeto, caminho) {
    return caminho.split('.').reduce((atual, parte) => atual?.[parte], objeto);
}

async function carregarParaEdicao() {
    document.getElementById('titulo').textContent = `Alterar notificação nº ${id}`;
    try {
        const notificacao = await requisitar(`${API_URL}/${id}`);
        for (const secao of SECOES) {
            for (const campo of secao.campos) {
                formulario.elements[campo.nome].value = obterValor(notificacao, campo.nome) ?? '';
            }
        }
    } catch (erro) {
        mostrarErros(erro);
    }
}

montarFormulario();
if (id) {
    carregarParaEdicao();
}