package br.jus.trf2.apoloapi;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameLocalIdMesaId2ExpedientesGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameLocalIdMesaId2ExpedientesGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameLocalIdMesaId2ExpedientesGetResponse;

public class UsuarioUsernameLocalIdMesaId2ExpedientesGet implements IUsuarioUsernameLocalIdMesaId2ExpedientesGet {

	@Override
	public void run(UsuarioUsernameLocalIdMesaId2ExpedientesGetRequest req,
			UsuarioUsernameLocalIdMesaId2ExpedientesGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "listar expedientes";
	}
}
