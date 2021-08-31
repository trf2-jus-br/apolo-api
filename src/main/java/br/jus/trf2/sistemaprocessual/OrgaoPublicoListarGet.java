package br.jus.trf2.sistemaprocessual;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IOrgaoPublicoListarGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class OrgaoPublicoListarGet implements IOrgaoPublicoListarGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter lista de órgãos públicos";
	}
}
