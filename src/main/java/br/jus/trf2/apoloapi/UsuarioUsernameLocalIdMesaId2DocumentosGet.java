package br.jus.trf2.apoloapi;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameLocalIdMesaId2DocumentosGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameLocalIdMesaId2DocumentosGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameLocalIdMesaId2DocumentosGetResponse;

public class UsuarioUsernameLocalIdMesaId2DocumentosGet implements IUsuarioUsernameLocalIdMesaId2DocumentosGet {

	@Override
	public void run(UsuarioUsernameLocalIdMesaId2DocumentosGetRequest req,
			UsuarioUsernameLocalIdMesaId2DocumentosGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "listar petições intercorrentes";
	}
}
