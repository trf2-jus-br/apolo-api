package br.jus.trf2.sistemaprocessual;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.ILocalidadeGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class LocalidadeGet implements ILocalidadeGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter localidade";
	}
}
