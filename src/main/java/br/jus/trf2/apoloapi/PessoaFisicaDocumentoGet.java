package br.jus.trf2.apoloapi;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IPessoaFisicaDocumentoGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class PessoaFisicaDocumentoGet implements IPessoaFisicaDocumentoGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter documento de pessoa física";
	}
}
