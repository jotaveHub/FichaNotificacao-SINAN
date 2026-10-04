const CLASSIFICACAO = { 1: 'Confirmado', 2: 'Descartado' };

const formulario = document.getElementById('filtros');
const corpoTabela = document.getElementById('corpo-tabela');
const mensagem = document.getElementById('mensagem');

function montarUrl() {
    const parametros = new URLSearchParams();

    for (const campo of formulario.elements) {
        if (campo.name && campo.value.trim() !== '') {
            parametros.set(campo.name, campo.value.trim());
        }
    }
    if (document.getElementById('duplicadas').checked) {
        parametros.set('duplicadas', 'true');
    }

    const consulta = parametros.toString();
    return consulta ? `${API_URL}?${consulta}` : API_URL;
}

function mostrarErro(erro) {
    mensagem.innerHTML = `<div class="alerta erro">${escapar(mensagemDeErro(erro))}</div>`;
}

function renderizar(notificacoes) {
    if (notificacoes.length === 0) {
        corpoTabela.innerHTML = '<tr><td colspan="10">Nenhuma notificação encontrada.</td></tr>';
        return;
    }

    corpoTabela.innerHTML = notificacoes.map(n => `
        <tr>
            <td>${escapar(n.numeroNotificacao)}</td>
            <td>${escapar(n.agravo)}</td>
            <td>${formatarData(n.dataNotificacao)}</td>
            <td>${escapar(n.nomePaciente)}</td>
            <td>${formatarData(n.dataNascimento)}</td>
            <td>${escapar(n.nomeMae)}</td>
            <td>${escapar(n.dadosResidencia?.municipio)}</td>
            <td>${escapar(CLASSIFICACAO[n.classificacaoFinal])}</td>
            <td><a class="botao" href="cadastro.html?id=${n.id}">Alterar</a></td>
            <td><button type="button" class="perigo" data-excluir="${n.id}">Excluir</button></td>
        </tr>
    `).join('');
}

async function carregar() {
    mensagem.innerHTML = '';
    try {
        const notificacoes = await requisitar(montarUrl());
        renderizar(notificacoes);
    } catch (erro) {
        corpoTabela.innerHTML = '';
        mostrarErro(erro);
    }
}

formulario.addEventListener('submit', evento => {
    evento.preventDefault();
    carregar();
});

document.getElementById('limpar').addEventListener('click', () => {
    formulario.reset();
    carregar();
});

corpoTabela.addEventListener('click', async evento => {
    const id = evento.target.dataset.excluir;
    if (!id || !confirm(`Excluir a notificação ${id}?`)) {
        return;
    }
    try {
        await requisitar(`${API_URL}/${id}`, { method: 'DELETE' });
        carregar();
    } catch (erro) {
        mostrarErro(erro);
    }
});

carregar();