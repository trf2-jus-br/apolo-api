package br.jus.trf2.apoloapi;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameMesasGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameMesasGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameMesasGetResponse;

public class UsuarioUsernameMesasGet implements IUsuarioUsernameMesasGet {

	@Override
	public void run(UsuarioUsernameMesasGetRequest req, UsuarioUsernameMesasGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter a mesa";
	}
}
