const API_URL = 'http://localhost:8080/notificacao';

async function requisitar(url, opcoes = {}) {
    const resposta = await fetch(url, {
        headers: { 'Content-Type': 'application/json' },
        ...opcoes
    });

    if (resposta.status === 204) {
        return null;
    }

    const corpo = await resposta.json().catch(() => null);

    if (!resposta.ok) {
        throw { status: resposta.status, problema: corpo };
    }
    return corpo;
}

function mensagemDeErro(erro) {
    const problema = erro && erro.problema;
    if (!problema) {
        return 'Não foi possível comunicar com a API. Ela está em execução?';
    }

    let texto = problema.detail || problema.title || 'Erro desconhecido';
    if (Array.isArray(problema.erros)) {
        texto += ' ' + problema.erros.map(e => `${e.campo}: ${e.mensagem}`).join('; ');
    }
    return texto;
}

function escapar(valor) {
    return String(valor ?? '').replace(/[&<>"']/g, caractere => ({
        '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;'
    }[caractere]));
}

function formatarData(iso) {
    if (!iso) {
        return '';
    }
    const [ano, mes, dia] = iso.split('-');
    return `${dia}/${mes}/${ano}`;
}