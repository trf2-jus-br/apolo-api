package br.jus.trf2.sistemaprocessual;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameMesasGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class UsuarioUsernameMesasGet implements IUsuarioUsernameMesasGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter a mesa";
	}
}
