package br.jus.trf2.apoloapi;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameLocalIdMesaId2DocumentosGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class UsuarioUsernameLocalIdMesaId2DocumentosGet implements IUsuarioUsernameLocalIdMesaId2DocumentosGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "listar petições intercorrentes";
	}
}
